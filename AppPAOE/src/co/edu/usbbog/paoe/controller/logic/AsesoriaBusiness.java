/**
 * Paquete que contiene la logica de negocio de la aplicación
 */
package co.edu.usbbog.paoe.controller.logic;

/**
 * Estas son los import necesarios para clase AsesoriaBusiness para su correcto
 * funcionamiento
 */
import co.edu.usbbog.paoe.controller.jpa.AsesoriaJpaController;
import co.edu.usbbog.paoe.controller.jpa.PacienteJpaController;
import co.edu.usbbog.paoe.controller.jpa.PsicologoJpaController;
import co.edu.usbbog.paoe.model.Asesoria;
import java.util.Date;

/**
 * @author Juan Nicolas, Juan Sebastían, Santiago Echeverri
 * @version 15/09/2020/1.0
 * @serial JPA
 * @since 15/09/2020
 */
/**
 *
 * Esta clase contiene los metodos para crear, editar, buscar y listar los
 * objetos de tipo Asesoria
 */
public class AsesoriaBusiness {

    /**
     * Campos o atributos de la clase
     */
    /**
     * Declaracion de una constante tipo AsesoriaJpaController, con la cual
     * podremos instaciar la clase AsesoriaJpaController y llamar a los metodos
     * para de crear, editar, buscar y listar instacias de esa clase
     */
    private final AsesoriaJpaController asesoriaController;

    /**
     * Declaración de una constante tipo PacienteJpaController, con la cual
     * podremos instanciar la clase PacienteJpaController y llamar a los metodos
     * para de crear, editar, buscar y listar instacias de esa clase
     */
    private final PacienteJpaController pacineteController;

    /**
     * Declaración de una constante tipo PsicologoJpaController con la cual
     * podremos instanciar la clase PsicologoJpaController y llamar a los
     * metodos para de crear, editar, buscar y listar instacias de esa clase
     */
    private final PsicologoJpaController psicologoController;

    /**
     * Variable que se utilizara para dar un mensaje informativo sobre los
     * resultados de las operaciones
     */
    private String mensaje = "";

    /**
     * Constructor de la clase donde instaciamos las clases
     * AsesoriaJpaController, PacienteJpaController, PsicologoJpaController para
     * hacer uso de los metodos de cada clase
     */
    public AsesoriaBusiness() {
        this.asesoriaController = new AsesoriaJpaController();
        this.pacineteController = new PacienteJpaController();
        this.psicologoController = new PsicologoJpaController();
    }

    /**
     * Método que devuleve un mensaje confirmando si se pudo guardar y crear
     * correctamente una Asesoria con sus respectivos atributos o por el
     * contrario se presento un error y no fue posible guardar la informacion
     * correctamente. Para eso necesitamos los siguintes parámetros
     *
     * @param numAsesoria El parámetro numAsesoria define un número especifco
     * para una asesoria que se esta llevando acabo con un paciente
     * @param fechaHora El parámetro fechaHora define la hora , el dia, el mes y
     * el año en que se realizo una asesoria
     * @param tipoAcompanamiento El parámetro tipoAcompanamiento define cual va
     * a hacer el acompañamiento que va a recibir un paciente
     * @param accesoServicio El parámetro accesoServicio define la forma en que
     * un paciente accedio a una asesoria
     * @param remitidoPor El parámetro remitidoPor define el nombre de la
     * categoria de la persona que remitio a un paciente al servicio de asesoría
     * @param problematicaRefTrab El parámetro problematicaRefTrab define la
     * problematica de un paciente, que sera trabajada en una asesoria por un
     * psicologo
     * @param accionesRealizadas El parámetro accionesRealizadas define los
     * acuerdos o los compromisos que se hicieron entre un psicologo y un
     * paciente en una asesoria
     * @param asistencia El parámetro asistencia define el tipo de asistenica
     * que tuvo un paciente respecto a la cita de una asesoría
     * @param culminacion El parámetro culminacion define el estado en que
     * termina una asesoría
     * @param remitidoA El parámetro remitidoA define la persona o entidad a la
     * cual le sera remitido un paciente
     * @param observaciones El parámetro observaciones define los comentarios
     * extras que puede realizar un psicologo en una asesoría
     * @param DocumentoPaciente El parámetro DocumentoPaciente define el número
     * de documento que identifica a un paciente en especifico
     * @param CedulaPsicologo El parámetro CedulaPsicologo define el número de
     * cedula que reconoce a un psicologo en especifico
     * @return un mensaje informando si los datos de una asesoria se guardaron
     * correctamente en la base de datos o si se presento un error y no fue
     * posible guardar la información en la base de datos
     */
    public String createAsesoria(Integer numAsesoria, Date fechaHora, String tipoAcompanamiento,
            String accesoServicio, String remitidoPor, String problematicaRefTrab, String accionesRealizadas,
            String asistencia, String culminacion, String remitidoA, String observaciones,
            Integer DocumentoPaciente, Integer CedulaPsicologo) {

        try {
            Asesoria asesoria = new Asesoria(numAsesoria, fechaHora, tipoAcompanamiento, accesoServicio,
                    remitidoPor, problematicaRefTrab, accionesRealizadas, asistencia, culminacion, remitidoA, observaciones,
                    pacineteController.findPaciente(DocumentoPaciente), psicologoController.findPsicologo(CedulaPsicologo));

            asesoriaController.create(asesoria);
            mensaje = "Guardado Correctamente";
        } catch (Exception e) {
            mensaje = "No se pudo guardar la información. \n" + e.getMessage();
        }
        return mensaje;
    }

    /**
     * Método que devuleve un mensaje confirmando si se pudo editar
     * correctamente los datos de una Asesoria o por el contrario se presento un
     * error y no fue posible actualizar la informacion correctamente. Para eso
     * necesitamos los siguintes parámetros
     *
     * @param numAsesoria El parámetro numAsesoria define un número especifco
     * para una asesoria que se esta llevando acabo con un paciente
     * @param fechaHora El parámetro fechaHora define la hora , el dia, el mes y
     * el año en que se realizo una asesoria
     * @param tipoAcompanamiento El parámetro tipoAcompanamiento define cual va
     * a hacer el acompañamiento que va a recibir un paciente
     * @param accesoServicio El parámetro accesoServicio define la forma en que
     * un paciente accedio a una asesoria
     * @param remitidoPor El parámetro remitidoPor define el nombre de la
     * categoria de la persona que remitio a un paciente al servicio de asesoría
     * @param problematicaRefTrab El parámetro problematicaRefTrab define la
     * problematica de un paciente, que sera trabajada en una asesoria por un
     * psicologo
     * @param accionesRealizadas El parámetro accionesRealizadas define los
     * acuerdos o los compromisos que se hicieron entre un psicologo y un
     * paciente en una asesoria
     * @param asistencia El parámetro asistencia define el tipo de asistenica
     * que tuvo un paciente respecto a la cita de una asesoría
     * @param culminacion El parámetro culminacion define el estado en que
     * termina una asesoría
     * @param remitidoA El parámetro remitidoA define la persona o entidad a la
     * cual le sera remitido un paciente
     * @param observaciones El parámetro observaciones define los comentarios
     * extras que puede realizar un psicologo en una asesoría
     * @param DocumentoPaciente El parámetro DocumentoPaciente define el número
     * de documento que identifica a un paciente en especifico
     * @param CedulaPsicologo El parámetro CedulaPsicologo define el número de
     * cedula que reconoce a un psicologo en especifico
     * @return un mensaje informando si los datos de una asesoria se editaron
     * correctamente en la base de datos o si se presento un error y no fue
     * posible editar la información en la base de datos
     */
    public String editAsesoria(Integer numAsesoria, Date fechaHora, String tipoAcompanamiento,
            String accesoServicio, String remitidoPor, String problematicaRefTrab, String accionesRealizadas,
            String asistencia, String culminacion, String remitidoA, String observaciones,
            Integer DocumentoPaciente, Integer CedulaPsicologo) {

        try {
            Asesoria asesoria = asesoriaController.findAsesoria(numAsesoria);
            asesoria.setFechaHora(fechaHora);
            asesoria.setTipoAcompanamiento(tipoAcompanamiento);
            asesoria.setAccesoServicio(accesoServicio);
            asesoria.setRemitidoPor(remitidoPor);
            asesoria.setProblematicaRefTrab(problematicaRefTrab);
            asesoria.setAccionesRealizadas(accionesRealizadas);
            asesoria.setAsistencia(asistencia);
            asesoria.setCulminacion(culminacion);
            asesoria.setRemitidoA(remitidoA);
            asesoria.setObservaciones(observaciones);
            asesoriaController.edit(asesoria);
            mensaje = "Se ha editado correctamente";
        } catch (Exception e) {
            mensaje = "No se pudo editar la información. \n" + e.getMessage();
        }
        return mensaje;
    }

    /**
     * Método que devuleve un mensaje confirmando si se pudo encontrar el
     * registro de una asesoria correctamente en la base de datos, mediante su
     * atributo identificador numAsesoria o por el contrario no fue posible
     * encontrar el registro de esa asesoria por diferentes razones
     *
     * @param numAsesoria El parámetro numAsesoria define un número especifco
     * para una asesoria que se esta llevando acabo con un paciente
     * @return un mensaje informando si los datos de una asesoria fueron
     * encontrados correctamente en la base de datos o si se presento un error y
     * no fue posible encontrar la información de esa asesoria en especifico en
     * la base de datos
     */
    public String findAsesoria(Integer numAsesoria) {

        try {
            asesoriaController.findAsesoria(numAsesoria);
            mensaje = "Se ha encontrado correctamente la información";
        } catch (Exception e) {
            mensaje = "No se pudo encontrar la información. \n" + e.getMessage();
        }
        return mensaje;
    }

    /**
     * Método que devuleve un mensaje confirmando si fue posible encontrar todos
     * los registros de las asesorias correctamente en la base de datos o por el
     * contrario no se pudieron encontrar todos los registros de las asesoria
     * por diferentes razones
     *
     * @return un mensaje informando si se pudo encontrar la lista de las
     * asesoria registradas en la base de datos o si se presento un error y no
     * fue posible encontrar la lsita de las asesoria registradas en la base de
     * datos
     */
    public String findAsesoriaEntities() {

        try {
            asesoriaController.findAsesoriaEntities();
            mensaje = "Se ha encontrado la lista de asesorías";
        } catch (Exception e) {
            mensaje = "No se pudo encontrar la lista de asesorías. \n" + e.getMessage();
        }
        return mensaje;
    }

//    /**
//     *
//     * @param args
//     */
//    public static void main(String[] args) {
//        AsesoriaBusiness ab = new AsesoriaBusiness();
//
//        System.out.println(ab.createAsesoria(1, new Date(120, 5, 3), "hola", "hola",
//                "hola", "hola", "hola", "hola", "hola", "hola", "hola", 1546545, 76538921));
//    }

}
