/**
 * Paquete que contiene la logica de negocio de la aplicación
 */
package co.edu.usbbog.paoe.controller.logic;

/**
 * Estas son los import necesarios para clase AsesoriaBusiness para su correcto
 * funcionamiento
 */
import co.edu.usbbog.paoe.controller.jpa.PacienteJpaController;
import co.edu.usbbog.paoe.model.Paciente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author Juan Nicolas, Juan Sebastían, Santiago Echeverri
 * @version 15/09/2020/1.0
 * @serial JPA
 * @since 15/09/2020
 */
/**
 * Esta clase contiene los metodos para crear, editar, buscar y listar los
 * objetos de tipo Paciente
 */
public class PacienteBusiness {

    /**
     * Campos o atributos de clase
     */
    /**
     * Declaracion de una constante tipo PacienteJpaController, con la cual
     * podremos instaciar la clase PacienteJpaController y llamar a los metodos
     * para de crear, editar, buscar y listar instacias de esa clase
     */
    private final PacienteJpaController pacienteJpaController;

    /**
     * Variable que se utilizara para dar un mensaje informativo sobre los
     * resultados de las operaciones
     */
    private String mensaje;

    /**
     * Constructor de la clase donde instaciamos las clases
     * PacienteJpaController, para hacer uso de los metodos de cada clase
     */
    public PacienteBusiness() {
        this.pacienteJpaController = new PacienteJpaController();
    }

    /**
     * Método que devuleve un mensaje confirmando si se pudo guardar y crear
     * correctamente un Paciente con sus respectivos atributos o por el
     * contrario se presento un error y no fue posible guardar la informacion
     * correctamente. Para eso necesitamos los siguintes parámetros
     *
     * @param documento El parámetro documento define el número de documento que
     * identifica a cada paciente
     * @param tipo_consultante El parámetro tipoConsultante define el tipo de
     * consultante que es un paciente es decir si es un estudiante, un profesor
     * ect, y como esta catalogado dentro de una asesoría
     * @param nombres El parámetro nombres define los nombre de un paciente
     * @param apellidos El parámetro apellidos define los apellidos de un
     * paciente
     * @param telefono El parámetro telefono define el número de telefono para
     * contactar a un paciente
     * @param localidad El parámetro localidad define el nombre de la localidad
     * donde recide un paciente
     * @param edad El parámetro edad define los años que tiene un paciente al
     * momento de realizar la asesoría
     * @param genero El parámetro genero define el genero del paciente
     * @param financiacion El parámetro financiacion define el tipo de
     * financiacion que tiene un estudiante
     * @param codigo El parámetro codigo define el codigo que identifica a un
     * estudiante
     * @param semestre El parámetro semestre define el semestre en el que se
     * encuentra un estudiante
     * @return un mensaje informando si los datos de un Paciente se guardaron
     * correctamente en la base de datos o si se presento un error y no fue
     * posible guardar la información en la base de datos
     */
    public String createPaciente(int documento, String tipo_consultante, String nombres, String apellidos, int telefono, String localidad,
            int edad, String genero, String financiacion, String codigo, String semestre) {
        try {
            if (financiacion != null && codigo != null && semestre != null) {
                Paciente p = new Paciente(documento, tipo_consultante, nombres, apellidos, telefono, localidad, edad,
                        genero, financiacion, codigo, semestre);
                pacienteJpaController.create(p);
                mensaje = "Se ha guardado al paciente";
            } else {
                Paciente p = new Paciente(documento, tipo_consultante, nombres, apellidos, telefono, localidad, edad,
                        genero);
                pacienteJpaController.create(p);
                mensaje = "Se ha guardado al paciente";
            }
        } catch (Exception ex) {
            System.err.println("error: " + ex.getMessage());
            mensaje = "No se ha guardado al paciente";
        }
        return mensaje;
    }

    /**
     * Método que devuleve un mensaje confirmando si se pudo editar
     * correctamente los datos de un Paciente o por el contrario se presento un
     * error y no fue posible editar la informacion correctamente. Para eso
     * necesitamos los siguintes parámetros
     *
     * @param documento El parámetro documento define el número de documento que
     * identifica a cada paciente
     * @param tipo_consultante El parámetro tipoConsultante define el tipo de
     * consultante que es un paciente es decir si es un estudiante, un profesor
     * ect, y como esta catalogado dentro de una asesoría
     * @param nombres El parámetro nombres define los nombre de un paciente
     * @param apellidos El parámetro apellidos define los apellidos de un
     * paciente
     * @param telefono El parámetro telefono define el número de telefono para
     * contactar a un paciente
     * @param localidad El parámetro localidad define el nombre de la localidad
     * donde recide un paciente
     * @param edad El parámetro edad define los años que tiene un paciente al
     * momento de realizar la asesoría
     * @param genero El parámetro genero define el genero del paciente
     * @param financiacion El parámetro financiacion define el tipo de
     * financiacion que tiene un estudiante
     * @param codigo El parámetro codigo define el codigo que identifica a un
     * estudiante
     * @param semestre El parámetro semestre define el semestre en el que se
     * encuentra un estudiante
     * @return un mensaje informando si los datos de un paciente se editaron
     * correctamente en la base de datos o si se presento un error y no fue
     * posible editar la información en la base de datos
     */
    public String editPaciente(int documento, String tipo_consultante, String nombres, String apellidos, int telefono, String localidad,
            int edad, String genero, String financiacion, String codigo, String semestre) {
        try {
            Paciente p = pacienteJpaController.findPaciente(documento);
            p.setTipoConsultante(tipo_consultante);
            p.setTelefono(telefono);
            p.setLocalidad(localidad);
            p.setEdad(edad);
            p.setFinanciacion(financiacion);
            p.setSemestre(semestre);
            pacienteJpaController.edit(p);
            mensaje = "Se ha editado al paciente";
        } catch (Exception ex) {
            System.err.println("error: " + ex.getMessage());
            mensaje = "No se ha editado al paciente";
        }
        return mensaje;
    }

    /**
     * Método que devuleve un mensaje confirmando si se pudo encontrar el
     * registro de un Paciente en especifico correctamente en la base de datos,
     * mediante su atributo identificador documento o por el contrario no fue
     * posible encontrar el registro de ese Paciente por diferentes razones
     *
     * @param documento El parámetro documento define el número de documento que
     * identifica a cada paciente
     * @return un mensaje informando si los datos de un paciente fueron
     * encontrados correctamente en la base de datos o si se presento un error y
     * no fue posible encontrar la información de ese paciente en especifico en
     * la base de datos
     */
    public Paciente findPaciente(int documento) {
        Paciente p = null;
        try {
            p = pacienteJpaController.findPaciente(documento);
            System.out.println("Se ha encontrado correctamente la información");
        } catch (Exception ex) {
            System.out.println("No se pudo encontrar la información. \n" + ex.getMessage());
        }
        return p;
    }

    /**
     * Método que devuleve un mensaje confirmando si fue posible encontrar todos
     * los registros de los pacientes registrados en la base de datos o por el
     * contrario no se pudieron encontrar todos los registros de los pacientes
     * por diferentes razones
     *
     * @return un mensaje informando si se pudo encontrar la lista de los
     * pacientes registradas en la base de datos o si se presento un error y no
     * fue posible encontrar la lsita de los pacientes registradas en la base de
     * datos
     */
    public String findPacienteEntities() {
        try {
            pacienteJpaController.findPacienteEntities();
            mensaje = "Se ha encontrado la lista de pacientes";
        } catch (Exception ex) {
            System.err.println("error: " + ex.getMessage());
            mensaje = "No se ha encontrado la lista de pacientes";
        }
        return mensaje;
    }

/**
     *
     * @param tabla
     * @param dato
     * @param filtro
     */
    public void filtrarTabla(JTable tabla, String dato, String filtro) {
        DefaultTableModel modelo;
        String[] titulo = {"Documento", "Tipo de Consultante", "Nombres", "Apellidos", "Teléfono", "Localidad",
            "Edad", "Genero", "Financiación","Código","Semestre" };
        modelo = new DefaultTableModel(null, titulo);

        List<Paciente> datos = filtrarPacinete(dato, filtro);

        String[] datosPaciente = new String[11];
        try {
            for (Paciente tbp : datos) {
            datosPaciente[0] = tbp.getDocumento() + "";
            datosPaciente[1] = tbp.getTipoConsultante() + "";
            datosPaciente[2] = tbp.getNombres() + "";
            datosPaciente[3] = tbp.getApellidos() + "";
            datosPaciente[4] = tbp.getTelefono() + "";
            datosPaciente[5] = tbp.getLocalidad() + "";
            datosPaciente[6] = tbp.getEdad() + "";
            datosPaciente[7] = tbp.getGenero() + "";
            datosPaciente[8] = tbp.getFinanciacion()+"";
            datosPaciente[9] = tbp.getCodigo()+"";
            datosPaciente[10] = tbp.getSemestre()+"";
            modelo.addRow(datosPaciente);
        }
        } catch (NullPointerException e) {
        }

        tabla.setModel(modelo);
    }

    /**
     *
     * @param dato
     * @param filtro
     * @return
     */
    private List<Paciente> filtrarPacinete(String dato, String filtro) {
        Query query;
        List<Paciente> lista = null;
        Paciente paciente;
        EntityManager em = pacienteJpaController.getEntityManager();
        if (filtro == null) {
            mensaje = "No se envio nada del comboBox hay nada";
        } else {
            switch (filtro) {
                case "Documento":
                    try {
                        query = em.createQuery("SELECT p FROM Paciente p WHERE p.documento = :documento");
                        query.setParameter("documento", Integer.parseInt(dato));
                        lista = query.getResultList();
                    } catch (NumberFormatException e) {
                        break;
                    }
                    break;
                case "Tipo de Consultante":
                    query = em.createQuery("SELECT p FROM Paciente p WHERE p.tipoConsultante LIKE :tipoConsultante");
                    query.setParameter("tipoConsultante", dato + "%");
                    lista = query.getResultList();
                    break;
                case "Nombre":
                    query = em.createQuery("SELECT p FROM Paciente p WHERE p.nombres LIKE :nombres");
                    query.setParameter("nombres", dato + "%");
                    lista = query.getResultList();
                    break;
                case "Localidad":
                    query = em.createQuery("SELECT p FROM Paciente p WHERE p.localidad LIKE :localidad");
                    query.setParameter("localidad", dato + "%");
                    lista = query.getResultList();
                    break;
                case "Edad":
                    try {
                        query = em.createQuery("SELECT p FROM Paciente p WHERE p.edad = :edad");
                        query.setParameter("edad", Integer.parseInt(dato));
                        lista = query.getResultList();
                    } catch (NumberFormatException e) {
                        break;
                    }
                    break;
                case "Genero":
                    query = em.createQuery("SELECT p FROM Paciente p WHERE p.genero LIKE :genero");
                    query.setParameter("genero", dato + "%");
                    lista = query.getResultList();
                    break;
                case "Financiacion":
                    query = em.createQuery("SELECT p FROM Paciente p WHERE p.financiacion LIKE :financiacion");
                    query.setParameter("financiacion", dato + "%");
                    lista = query.getResultList();
                    break;
                case "Codigo":
                    query = em.createQuery("SELECT p FROM Paciente p WHERE p.codigo LIKE :codigo");
                    query.setParameter("codigo", dato + "%");
                    lista = query.getResultList();
                    break;
                case "Semestre":
                    query = em.createQuery("SELECT p FROM Paciente p WHERE p.semestre LIKE :semestre");
                    query.setParameter("semestre", dato + "%");
                    lista = query.getResultList();
                    break;
                default:
                    System.out.println("Error");
            }
        }

        return lista;
    }
}
