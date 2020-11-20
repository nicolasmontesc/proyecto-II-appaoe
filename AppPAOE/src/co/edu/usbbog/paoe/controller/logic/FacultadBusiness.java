/**
 * Paquete que contiene la logica de negocio de la aplicación
 */
package co.edu.usbbog.paoe.controller.logic;

/**
 * Estas son los import necesarios para clase AsesoriaBusiness para su correcto
 * funcionamiento
 */
import co.edu.usbbog.paoe.controller.jpa.FacultadJpaController;
import co.edu.usbbog.paoe.controller.jpa.PsicologoJpaController;
import co.edu.usbbog.paoe.model.Facultad;

/**
 * @author Juan Nicolas, Juan Sebastían, Santiago Echeverri
 * @version 15/09/2020/1.0
 * @serial JPA
 * @since 15/09/2020
 */
/**
 *
 * Esta clase contiene los metodos para crear, editar, buscar y listar los
 * objetos de tipo Facultad
 */
public class FacultadBusiness {

    /**
     * Campos o atributos de la clase
     */
    /**
     * Declaracion de una constante tipo FacultadJpaController, con la cual
     * podremos instaciar la clase FacultadJpaController y llamar a los metodos
     * para de crear, editar, buscar y listar instacias de esa clase
     */
    private final FacultadJpaController facultadJpaController;

    /**
     * Declaracion de una constante tipo PsicologoJpaController, con la cual
     * podremos instaciar la clase PsicologoJpaController y llamar a los metodos
     * para de crear, editar, buscar y listar instacias de esa clase
     */
    private final PsicologoJpaController psicologoJpaController;

    /**
     * Variable que se utilizara para dar un mensaje informativo sobre los
     * resultados de las operaciones
     */
    private String mensaje = "";

    /**
     * Constructor de la clase donde instaciamos las clases
     * FacultadJpaController, PsicologoJpaController para hacer uso de los
     * metodos de cada clase
     */
    public FacultadBusiness() {
        this.facultadJpaController = new FacultadJpaController();
        this.psicologoJpaController = new PsicologoJpaController();
    }

    /**
     * Método que devuleve un mensaje confirmando si se pudo guardar y crear
     * correctamente una Facultad con sus respectivos atributos o por el
     * contrario se presento un error y no fue posible guardar la informacion
     * correctamente. Para eso necesitamos los siguintes parámetros
     *
     * @param nomFacultad El parámetro nomFacultad define el nombre especifico
     * de la facultad el cual es unico y no se puede repetir
     * @param telefono El parámetro telefono define el número de telefono que
     * tiene cada facultad
     * @param correo El parámetro correo define el correo que tiene cada
     * facultad
     * @param nomDecano El parámetro nomDecano define el nombre del decano que
     * tiene cada facultad
     * @param cedula El parámetro cedula define el número de cedula que
     * identifica a un psicologo en especifico
     * @return un mensaje informando si los datos de una Facultad se guardaron
     * correctamente en la base de datos o si se presento un error y no fue
     * posible guardar la información en la base de datos
     */
    public String createFacultad(String nomFacultad, String correo, String nomDecano, int telefono, Integer cedula) {

        try {
            Facultad facultad = new Facultad(nomFacultad, telefono, correo, nomDecano,
                    psicologoJpaController.findPsicologo(cedula));
            facultadJpaController.create(facultad);
            mensaje = "Guardado Correctamente";
        } catch (Exception e) {
            mensaje = "No se pudo guardar la informacion \n" + e.getMessage();
        }
        return mensaje;
    }

    /**
     * Método que devuleve un mensaje confirmando si se pudo actualizar
     * correctamente los datos de una Facultad o por el contrario se presento un
     * error y no fue posible actualizar la informacion correctamente. Para eso
     * necesitamos los siguintes parámetros
     *
     * @param nomFacultad El parámetro nomFacultad define el nombre especifico
     * de la facultad el cual es unico y no se puede repetir
     * @param telefono El parámetro telefono define el número de telefono que
     * tiene cada facultad
     * @param correo El parámetro correo define el correo que tiene cada
     * facultad
     * @param nomDecano El parámetro nomDecano define el nombre del decano que
     * tiene cada facultad
     * @param cedula El parámetro cedula define el número de cedula que
     * identifica a un psicologo en especifico
     * @return un mensaje informando si los datos de una Facultad se editaron
     * correctamente en la base de datos o si se presento un error y no fue
     * posible editar la información en la base de datos
     */
    public String editFacultad(String nomFacultad, int telefono, String correo, String nomDecano, Integer cedula) {

        try {
            Facultad facultad = new FacultadJpaController().findFacultad(nomFacultad);
            facultad.setTelefono(telefono);
            facultad.setCorreo(correo);
            facultad.setNomDecano(nomDecano);
            facultadJpaController.edit(facultad);
            mensaje = "Se ha editado correctamente";
        } catch (Exception e) {
            mensaje = "No se pudo editar la información. \n" + e.getMessage();
        }
        return mensaje;
    }

    /**
     * Método que devuleve un mensaje confirmando si se pudo encontrar el
     * registro de una Facultad en especifico correctamente en la base de datos,
     * mediante su atributo identificador nomFacultad o por el contrario no fue
     * posible encontrar el registro de esa Facultad por diferentes razones
     *
     * @param nomFacultad El parámetro nomFacultad define el nombre especifico
     * de la facultad el cual es unico y no se puede repetir
     * @return un mensaje informando si los datos de una Facultad fueron
     * encontrados correctamente en la base de datos o si se presento un error y
     * no fue posible encontrar la información de esa Facultad en especifico en
     * la base de datos
     */
    public String findPsicologo(String nomFacultad) {
        try {
            facultadJpaController.findFacultad(nomFacultad);
            mensaje = "Se ha encontrado correctamente la información";
        } catch (Exception e) {
            mensaje = "No se pudo encontrar la información. \n" + e.getMessage();
        }
        return mensaje;
    }

    /**
     * Método que devuleve un mensaje confirmando si fue posible encontrar todos
     * los registros de las facultades registrados en la base de datos o por el
     * contrario no se pudieron encontrar los registros de las facultades por
     * diferentes razones
     *
     * @return un mensaje informando si se pudo encontrar la lista de las
     * facultades registradas en la base de datos o si se presento un error y no
     * fue posible encontrar la lsita de las facultades registradas en la base
     * de datos
     */
    public String findPacienteEntities() {
        try {
            facultadJpaController.findFacultadEntities();
            return "Se ha encontrado la lista de facultades";
        } catch (Exception ex) {
            System.err.println("error: " + ex.getMessage());
            return "No se ha encontrado la lista de facultades \n" + ex.getMessage();
        }
    }

//    /**
//     *
//     * @param args
//     */
//    public static void main(String[] args) {
//
//        FacultadBusiness facuB = new FacultadBusiness();
//
//        System.out.println(facuB.createFacultad("Ingeniería", "ingeneriaUsbbog@gmail.com", "Pipe", 3456765, 76538921));
//
//    }

}
