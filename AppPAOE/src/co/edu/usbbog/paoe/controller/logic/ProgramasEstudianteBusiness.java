/**
 * Paquete que contiene la logica de negocio de la aplicación
 */
package co.edu.usbbog.paoe.controller.logic;

/**
 * Estas son los import necesarios para clase AsesoriaBusiness para su correcto
 * funcionamiento
 */
import co.edu.usbbog.paoe.controller.jpa.ProgramasEstudianteJpaController;
import co.edu.usbbog.paoe.model.ProgramasEstudiante;
import co.edu.usbbog.paoe.model.ProgramasEstudiantePK;
import java.util.Date;

/**
 * @author Juan Nicolas, Juan Sebastían, Santiago Echeverri
 * @version 15/09/2020/1.0
 * @serial JPA
 * @since 15/09/2020
 */
/**
 * Esta clase contiene los metodos para crear, editar, buscar y listar los
 * objetos de tipo ProgramasEstudiante
 */
public class ProgramasEstudianteBusiness {

    /**
     * Campos o atributos de la clase
     */
    /**
     * Declaracion de una constante tipo ProgramasEstudianteJpaController, con
     * la cual podremos instaciar la clase ProgramasEstudianteJpaController y
     * llamar a los metodos para de crear, editar, buscar y listar instacias de
     * esa clase
     */
    private final ProgramasEstudianteJpaController programaEstudianteJpaController;

    /**
     * Declaracion de una constante tipo ProgramasEstudiantePK, con la cual
     * podremos instaciar la clase ProgramasEstudiantePK y llamar sus metodos y
     * atributos
     */
    private final ProgramasEstudiantePK programasEstudiantePK;

    /**
     * Variable que se utilizara para dar un mensaje informativo sobre los
     * resultados de las operaciones
     */
    private String mensaje = "";

    /**
     * Constructor de la clase donde instaciamos las clases
     * ProgramasEstudianteJpaController, y ProgramasEstudiantePK para hacer uso
     * de sus mienbros clase
     */
    public ProgramasEstudianteBusiness() {
        this.programaEstudianteJpaController = new ProgramasEstudianteJpaController();
        this.programasEstudiantePK = new ProgramasEstudiantePK();
    }

    /**
     * Método que devuleve un mensaje confirmando si se pudo guardar y crear
     * correctamente un ProgramasEstudiante con sus respectivos atributos o por
     * el contrario se presento un error y no fue posible guardar la informacion
     * correctamente. Para eso necesitamos los siguintes parámetros
     *
     * @param paciente El parámetro paciente define el número del documento del
     * paciente
     * @param programa El parámetro programa define el nombre del programa al
     * cual pertence este paciente que es un estudiante
     * @param ingreso El parámetro ingreso define la fecha de cuando un
     * estudainte inicio un programa
     * @param estado El parámetro estado define como se encuentra el estado de
     * un programa en curso por un estduiante en especifico
     * @param egreso El parámetro egreso define la fecha en que el estudiante
     * termino el programa
     * @return un mensaje informando si los datos se guardaron correctamente en
     * la base de datos o si se presento un error y no fue posible guardar la
     * información en la base de datos
     */
    public String createProgramasEstudiantes(int paciente, String programa, Date ingreso, String estado, Date egreso) {
        try {
            if (egreso != null) {
                programasEstudiantePK.setPaciente(paciente);
                programasEstudiantePK.setPrograma(programa);
                ProgramasEstudiante programasEstudiante = new ProgramasEstudiante(programasEstudiantePK, ingreso, estado, egreso);
                programaEstudianteJpaController.create(programasEstudiante);
                mensaje = "Guardado correctamente";
            } else {
                programasEstudiantePK.setPaciente(paciente);
                programasEstudiantePK.setPrograma(programa);
                ProgramasEstudiante programasEstudiante = new ProgramasEstudiante(programasEstudiantePK, ingreso, estado);
                programaEstudianteJpaController.create(programasEstudiante);
                mensaje = "Guardado correctamente";
            }
        } catch (Exception ex) {
            mensaje = "No se pudo guardar la información. \n" + ex.getMessage();
        }
        return mensaje;
    }

    /**
     * Método que devuleve un mensaje confirmando si se pudo editar
     * correctamente los datos de un ProgramasEstudiante o por el contrario se
     * presento un error y no fue posible guardar la informacion correctamente.
     * Para eso necesitamos los siguintes parámetros
     *
     * @param paciente El parámetro paciente define el número del documento del
     * paciente
     * @param programa El parámetro programa define el nombre del programa al
     * cual pertence este paciente que es un estudiante
     * @param ingreso El parámetro ingreso define la fecha de cuando un
     * estudainte inicio un programa
     * @param estado El parámetro estado define como se encuentra el estado de
     * un programa en curso por un estduiante en especifico
     * @param egreso El parámetro egreso define la fecha en que el estudiante
     * termino el programa
     * @return un mensaje informando si los datos se editaron correctamente en
     * la base de datos o si se presento un error y no fue posible editar la
     * información en la base de datos
     */
    public String editProgramaEstudiantes(int paciente, String programa, Date ingreso, String estado, Date egreso) {
        try {
            programasEstudiantePK.setPaciente(paciente);
            programasEstudiantePK.setPrograma(programa);
            ProgramasEstudiante pe = programaEstudianteJpaController.findProgramasEstudiante(programasEstudiantePK);
            pe.setIngreso(ingreso);
            pe.setEstado(estado);
            pe.setEgreso(egreso);
            programaEstudianteJpaController.edit(pe);
            mensaje = "Se ha editado la información correctamente";
        } catch (Exception ex) {
            System.err.println("error: " + ex.toString());
            mensaje = "No se ha editado la información";
        }
        return mensaje;
    }

    /**
     * Método que devuleve un mensaje confirmando si se pudo encontrar el
     * registro de un ProgramasEstudiante en especifico correctamente en la base
     * de datos, mediante su atributo identificador paciente y programa o por el
     * contrario no fue posible encontrar el registro por diferentes razones
     *
     * @param paciente El parámetro paciente define el número del documento del
     * paciente
     * @param programa El parámetro programa define el nombre del programa al
     * cual pertence este paciente que es un estudiante
     * @return un mensaje informando si los datos fueron encontrados
     * correctamente en la base de datos o si se presento un error y no fue
     * posible encontrar la información de un ProgramasEstudiante en especifico
     * en la base de datos
     */
    public String findProgramasEstudiante(int paciente, String programa) {

        try {
            programasEstudiantePK.setPaciente(paciente);
            programasEstudiantePK.setPrograma(programa);
            programaEstudianteJpaController.findProgramasEstudiante(programasEstudiantePK);

            mensaje = "Se ha encontrado La información";
        } catch (Exception ex) {
            System.err.println("error: " + ex.getMessage());
            mensaje = "No se ha encontrado la información";
        }
        return mensaje;
    }

    /**
     * Método que devuleve un mensaje confirmando si fue posible encontrar todos
     * los registros de los ProgramasEstudiante registrados en la base de datos
     * o por el contrario no se pudieron encontrar los registros por diferentes
     * razones
     *
     * @return un mensaje informando si se pudo encontrar la lista de los
     * ProgramasEstudiante registradas en la base de datos o si se presento un
     * error y no fue posible encontrar la lsita de los ProgramasEstudiante
     * registradas en la base de datos
     */
    public String findProgramasEstudiantesEntities() {

        try {
            programaEstudianteJpaController.findProgramasEstudianteEntities();
            mensaje = "Se ha encontrado la lista de programasEstudiantes";
        } catch (Exception ex) {
            mensaje = "No se pudo encontrar la lista de programasEstudiantes. \n" + ex.getMessage();
        }
        return mensaje;
    }

//    public static void main(String[] args) {
//
//        ProgramasEstudianteBusiness pb = new ProgramasEstudianteBusiness();
////        System.out.println(pb.createProgramasEstudiantes(1546545, "Ingeniería de Sistemas", new Date(120, 7, 10), "Activo", null));
//        System.out.println(pb.editProgramaEstudiantes(1546545, "Ingeniería de Sistemas", new Date(120, 7, 10), "Finalizado", new Date(126, 8, 30)));
//    }
}
