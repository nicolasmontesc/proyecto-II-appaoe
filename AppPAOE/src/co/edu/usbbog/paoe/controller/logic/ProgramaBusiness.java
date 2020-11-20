/**
 * Paquete que contiene la logica de negocio de la aplicación
 */
package co.edu.usbbog.paoe.controller.logic;

/**
 * Estas son los import necesarios para clase AsesoriaBusiness para su correcto
 * funcionamiento
 */
import co.edu.usbbog.paoe.controller.jpa.FacultadJpaController;
import co.edu.usbbog.paoe.controller.jpa.ProgramaJpaController;
import co.edu.usbbog.paoe.model.Programa;

/**
 * @author Juan Nicolas, Juan Sebastían, Santiago Echeverri
 * @version 15/09/2020/1.0
 * @serial JPA
 * @since 15/09/2020
 */
/**
 * Esta clase contiene los metodos para crear, editar, buscar y listar los
 * objetos de tipo Asesoria
 */
public class ProgramaBusiness {

    /**
     * Campos o atributos de la clase
     */
    /**
     * Declaracion de una constante tipo ProgramaJpaController, con la cual
     * podremos instaciar la clase ProgramaJpaController y llamar a los metodos
     * para de crear, editar, buscar y listar instacias de esa clase
     */
    private final ProgramaJpaController programaJpaController;

    /**
     * Declaracion de una constante tipo FacultadJpaController, con la cual
     * podremos instaciar la clase FacultadJpaController y llamar a los metodos
     * para de crear, editar, buscar y listar instacias de esa clase
     */
    private final FacultadJpaController facultadJpaController;

    /**
     * Variable que se utilizara para dar un mensaje informativo sobre los
     * resultados de las operaciones
     */
    private String mensaje;

    /**
     * Constructor de la clase donde instaciamos las clases
     * ProgramaJpaController, FacultadJpaController para hacer uso de los
     * metodos de cada clase
     */
    public ProgramaBusiness() {
        this.programaJpaController = new ProgramaJpaController();
        this.facultadJpaController = new FacultadJpaController();
    }

    /**
     * Método que devuleve un mensaje confirmando si se pudo guardar y crear
     * correctamente un Programa con sus respectivos atributos o por el
     * contrario se presento un error y no fue posible guardar la informacion
     * correctamente. Para eso necesitamos los siguintes parámetros
     *
     * @param nomPrograma El parámetro nomPrograma define el nombre especifico
     * de cada programa el cual es unico e irrepetible
     * @param modalidad El parámetro modalidad define la modalidad en que se
     * toma el programa; si es virtual o presencial
     * @param telefono El parámetro telefono define el número de telefono que
     * posee cada programa
     * @param correo El parámetro correo define el correo electronico que posee
     * cada facultad
     * @param nomDirector El parámetro nomDirector define el nombre del
     * director, que esta encarcado de un programa en especifico
     * @param facultad El parámetro facultad define el nombre especifico de un
     * facultad, el cual es unico e irrepetible
     * @return un mensaje informando si los datos de un Programa se guardaron
     * correctamente en la base de datos o si se presento un error y no fue
     * posible guardar la información en la base de datos
     */
    public String createPrograma(String nomPrograma, String modalidad, int telefono, String correo, String nomDirector, String facultad) {
        try {
            Programa p = new Programa(nomPrograma, modalidad, telefono, correo, nomDirector, facultadJpaController.findFacultad(facultad));
            programaJpaController.create(p);
            mensaje = "Se ha guardado el programa";
        } catch (Exception ex) {
            System.err.println("error: " + ex.getMessage());
            mensaje = "No se ha guardado el programa";
        }
        return mensaje;
    }

    /**
     * Método que devuleve un mensaje confirmando si se pudo editar
     * correctamente los datos de un Programa o por el contrario se presento un
     * error y no fue posible editar la informacion correctamente. Para eso
     * necesitamos los siguintes parámetros
     *
     * @param nomPrograma El parámetro nomPrograma define el nombre especifico
     * de cada programa el cual es unico e irrepetible
     * @param modalidad El parámetro modalidad define la modalidad en que se
     * toma el programa; si es virtual o presencial
     * @param telefono El parámetro telefono define el número de telefono que
     * posee cada programa
     * @param correo El parámetro correo define el correo electronico que posee
     * cada facultad
     * @param nomDirector El parámetro nomDirector define el nombre del
     * director, que esta encarcado de un programa en especifico
     * @param facultad El parámetro facultad define el nombre especifico de un
     * facultad, el cual es unico e irrepetible
     * @return un mensaje informando si los datos de un Programa se editaron
     * correctamente en la base de datos o si se presento un error y no fue
     * posible editar la información en la base de datos
     */
    public String editPrograma(String nomPrograma, String modalidad, int telefono, String correo, String nomDirector, String facultad) {
        try {
            Programa p = programaJpaController.findPrograma(nomPrograma);
            p.setModalidad(modalidad);
            p.setTelefono(telefono);
            p.setCorreo(correo);
            p.setNomDirector(nomDirector);
            programaJpaController.edit(p);
            mensaje = "Se ha editado el programa";
        } catch (Exception ex) {
            System.err.println("error: " + ex.getMessage());
            mensaje = "No se ha editado el programa";
        }
        return mensaje;
    }

    /**
     * Método que devuleve un mensaje confirmando si se pudo encontrar el
     * registro de un Programa en especifico correctamente en la base de datos,
     * mediante su atributo identificador nomProgramma o por el contrario no fue
     * posible encontrar el registro de ese Programa por diferentes razones
     *
     * @param nomPrograma El parámetro nomPrograma define el nombre especifico
     * de cada programa el cual es unico e irrepetible
     * @return un mensaje informando si los datos de un Programa fueron
     * encontrados correctamente en la base de datos o si se presento un error y
     * no fue posible encontrar la información de ese Programa en especifico en
     * la base de datos
     */
    public String findPrograma(String nomPrograma) {
        try {
            programaJpaController.findPrograma(nomPrograma);
            mensaje = "Se ha encontrado el programa";
        } catch (Exception ex) {
            System.err.println("error: " + ex.getMessage());
            mensaje = "No se ha encontrado el programa";
        }
        return mensaje;
    }

    /**
     * Método que devuleve un mensaje confirmando si fue posible encontrar todos
     * los registros de los programas registrados en la base de datos o por el
     * contrario no se pudieron encontrar los registros de los Programas por
     * diferentes razones
     *
     * @return un mensaje informando si se pudo encontrar la lista de los
     * programas registradas en la base de datos o si se presento un error y no
     * fue posible encontrar la lsita de los programas registradas en la base de
     * datos
     */
    public String findProgramaEntities() {
        try {
            programaJpaController.findProgramaEntities();
            mensaje = "Se ha encontrado la lista de programas";
        } catch (Exception ex) {
            System.err.println("error: " + ex.getMessage());
            mensaje = "No se ha encontrado la lista de programas";
        }
        return mensaje;
    }

//    /**
//     *
//     * @param args
//     */
//    public static void main(String[] args) {
//
//        ProgramaBusiness programaB = new ProgramaBusiness();
//
//        programaB.createPrograma("Ingeniería de Sistemas", "Virtual", 764567, "sistemas@gmail.com", "Gloria", "Ingeniería");
//
//    }
}
