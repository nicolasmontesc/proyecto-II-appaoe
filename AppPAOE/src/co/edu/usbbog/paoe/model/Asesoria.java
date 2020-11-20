/**
 * Paquete que se encargar de albergar las entidades del modelo de datos
 */
package co.edu.usbbog.paoe.model;

/**
 * Estos son los import necesarios para utilizar el api de Java JPA y para el
 * correcto manejo de los atributos
 */
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Juan Nicolas, Juan Sebastían, Santiago Echeverri
 * @version 15/09/2020/1.0
 * @serial JPA
 * @since 15/09/2020
 */
/**
 * Etiqueta que indica que esta clase representa la entidad Asesoria en el
 * modelo de datos
 */
@Entity
/**
 * Etiqueta que hace eferenica a la base de datos a la cual pertenece la clase
 */
@Table(catalog = "paoe_db", schema = "")
@XmlRootElement
/**
 *
 */
@NamedQueries({
    @NamedQuery(name = "Asesoria.findAll", query = "SELECT a FROM Asesoria a")
    , @NamedQuery(name = "Asesoria.findByNumAsesoria", query = "SELECT a FROM Asesoria a WHERE a.numAsesoria = :numAsesoria")
    , @NamedQuery(name = "Asesoria.findByFechaHora", query = "SELECT a FROM Asesoria a WHERE a.fechaHora = :fechaHora")
    , @NamedQuery(name = "Asesoria.findByTipoAcompanamiento", query = "SELECT a FROM Asesoria a WHERE a.tipoAcompanamiento = :tipoAcompanamiento")
    , @NamedQuery(name = "Asesoria.findByAccesoServicio", query = "SELECT a FROM Asesoria a WHERE a.accesoServicio = :accesoServicio")
    , @NamedQuery(name = "Asesoria.findByRemitidoPor", query = "SELECT a FROM Asesoria a WHERE a.remitidoPor = :remitidoPor")
    , @NamedQuery(name = "Asesoria.findByProblematicaRefTrab", query = "SELECT a FROM Asesoria a WHERE a.problematicaRefTrab = :problematicaRefTrab")
    , @NamedQuery(name = "Asesoria.findByAccionesRealizadas", query = "SELECT a FROM Asesoria a WHERE a.accionesRealizadas = :accionesRealizadas")
    , @NamedQuery(name = "Asesoria.findByAsistencia", query = "SELECT a FROM Asesoria a WHERE a.asistencia = :asistencia")
    , @NamedQuery(name = "Asesoria.findByCulminacion", query = "SELECT a FROM Asesoria a WHERE a.culminacion = :culminacion")
    , @NamedQuery(name = "Asesoria.findByRemitidoA", query = "SELECT a FROM Asesoria a WHERE a.remitidoA = :remitidoA")
    , @NamedQuery(name = "Asesoria.findByObservaciones", query = "SELECT a FROM Asesoria a WHERE a.observaciones = :observaciones")})

/**
 * Esta clase define los atributos necesarios, con sus respectivos métodos;
 * getters/settes, hashcode, equals y toString
 */
public class Asesoria implements Serializable {

    /**
     * Campos o atributos de la clase
     */
    /**
     * Declaración e inicialización de la constante serialVersionUID, para
     * asignarle un numero que identifique a la clase Asesoria
     */
    private static final long serialVersionUID = 1L;

    /**
     * Atributo que represnta el atributo identificador de la clase Asesoria, y
     * define un número para una asesoria que se esta llevando acabo con un
     * paciente en especifico
     */
    @Id
    @Basic(optional = false)
    @Column(name = "num_asesoria", nullable = false)
    private Integer numAsesoria;

    /**
     * Atributo que especifica la fecha y la hora en que se realizo una asesoria
     */
    @Basic(optional = false)
    @Column(name = "fecha_hora", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;

    /**
     * Atributo que espicifica el tipo de acompañamiento que va a recibir el
     * paciente
     */
    @Basic(optional = false)
    @Column(name = "tipo_acompanamiento", nullable = false, length = 45)
    private String tipoAcompanamiento;

    /**
     * Atributo que especifica la forma o la manera en que el paciente accedio
     * al servicio de asesoria
     */
    @Basic(optional = false)
    @Column(name = "acceso_servicio", nullable = false, length = 45)
    private String accesoServicio;

    /**
     * Esté Atributo define el nombre de la categoria de la persona(un docente,
     * familiar, decano ect.) que remitio a un paciente al servicio de asesoría
     */
    @Basic(optional = false)
    @Column(name = "remitido_por", nullable = false, length = 45)
    private String remitidoPor;

    /**
     * Atributo que describe la problematica del paciente idetificada por el
     * psicologo y que sera trabajada en una asesoria
     */
    @Basic(optional = false)
    @Column(name = "problematica_ref_trab", nullable = false, length = 45)
    private String problematicaRefTrab;

    /**
     * Atributo que describe o define los acuerdos o compromisos que se
     * realizaron entre un psicologo y un paciente para su mejoría
     */
    @Basic(optional = false)
    @Column(name = "acciones_realizadas", nullable = false, length = 45)
    private String accionesRealizadas;

    /**
     * Atributo que especifica o define el tipo de asistenica que tuvo un
     * paciente respecto a la cita de una asesoría
     */
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String asistencia;

    /**
     * Atributo que especifica el esatdo en como termina una asesoría con un
     * paciente
     */
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String culminacion;

    /**
     * Atributo que especifica a que persona o entidad fue remitido un paciente
     * para darle continuidad al proceso
     */
    @Basic(optional = false)
    @Column(name = "remitido_a", nullable = false, length = 45)
    private String remitidoA;

    /**
     * Atributo que describe los comentarios extras que puede realizar un
     * psicologo sobre un paciente
     */
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String observaciones;

    /**
     * Atributo que define una variable de tipo Paciente, para luego poder
     * instaciar la clase Paciente
     */
    @JoinColumn(name = "paciente", referencedColumnName = "documento", nullable = false)
    @ManyToOne(optional = false)
    private Paciente paciente;

    /**
     * Atributo que define una variable de tipo Psicologo, para luego poder
     * instaciar la clase Psicologo
     */
    @JoinColumn(name = "psicologo", referencedColumnName = "cedula", nullable = false)
    @ManyToOne(optional = false)
    private Psicologo psicologo;

    /**
     * Constructor vacio de la clase para instanciar un onjeto sin establecer
     * los campos de la clase.
     */
    public Asesoria() {
    }

    /**
     * Constructor de la clase para instanciar un objeto estableciendo el campo
     * de clase numAsesoria
     *
     * @param numAsesoria El parámetro numAsesoria define un número especifco
     * para una asesoria que se esta llevando acabo con un paciente
     */
    public Asesoria(Integer numAsesoria) {
        this.numAsesoria = numAsesoria;
    }

    /**
     * Constructor de la clase para instaciar un objeto estableciendo todos los
     * atributos
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
     * @param paciente El parámetro paciente define una instancia de tipo
     * Paciente para obtener los métodos y atributos de la Clase Paciente
     * @param psicologo El parámetro psicologo define una instancia de tipo
     * Psicologo para obtener los métodos y atributos de la Clase Psicologo
     */
    public Asesoria(Integer numAsesoria, Date fechaHora, String tipoAcompanamiento, String accesoServicio, String remitidoPor,
            String problematicaRefTrab, String accionesRealizadas, String asistencia, String culminacion, String remitidoA,
            String observaciones, Paciente paciente, Psicologo psicologo) {
        this.numAsesoria = numAsesoria;
        this.fechaHora = fechaHora;
        this.tipoAcompanamiento = tipoAcompanamiento;
        this.accesoServicio = accesoServicio;
        this.remitidoPor = remitidoPor;
        this.problematicaRefTrab = problematicaRefTrab;
        this.accionesRealizadas = accionesRealizadas;
        this.asistencia = asistencia;
        this.culminacion = culminacion;
        this.remitidoA = remitidoA;
        this.observaciones = observaciones;
        this.paciente = paciente;
        this.psicologo = psicologo;
    }

    /**
     * Método que devuelve el numero de una asesoría que se realizo con un
     * paciente
     *
     * @return numAsesoria, el atributo que representa el número de una asesoria
     * en especifico
     */
    public Integer getNumAsesoria() {
        return numAsesoria;
    }

    /**
     * Método que se utiliza para establecer el atributo numAsesoria, de una
     * instancia de clase
     *
     * @param numAsesoria El parámetro numAsesoria define un número especiifco
     * para una asesoria que se esta llevando acabo con un paciente
     */
    public void setNumAsesoria(Integer numAsesoria) {
        this.numAsesoria = numAsesoria;
    }

    /**
     * Método que devuelve la hora, el dia, el mes y el año en que se realizo
     * una asesría
     *
     * @return fechaHora, el atributo que representa la fecha completa(dia, mes
     * y año) con la hora en que se realizo una asesoria
     */
    public Date getFechaHora() {
        return fechaHora;
    }

    /**
     * Método que se utiliza para establecer el atributo fechaHora de una
     * instancia de clase
     *
     * @param fechaHora El parámetro fechaHora define la hora , el dia, el mes y
     * el año en que se realizo una asesoria
     */
    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Método que devuelve el tipo o clase de acompañamiento que esta recibiendo
     * un paciente
     *
     * @return tipoAcompanamiento, el atributo que representa la clase de
     * acompñamiento que recibe un paciente
     */
    public String getTipoAcompanamiento() {
        return tipoAcompanamiento;
    }

    /**
     * Método que se utiliza para establecer el atributo tipoAcompanamiento, de
     * una instancia de clase
     *
     * @param tipoAcompanamiento El parámetro tipoAcompanamiento define cual va
     * a hacer el acompañamiento que va a recibir un paciente
     */
    public void setTipoAcompanamiento(String tipoAcompanamiento) {
        this.tipoAcompanamiento = tipoAcompanamiento;
    }

    /**
     * Método que devuelve la forma en que un paciente accedio al servicio de
     * asesoria
     *
     * @return accesoServicio, el atributo que representa la manera de como un
     * paciente accedio a una asesoria
     */
    public String getAccesoServicio() {
        return accesoServicio;
    }

    /**
     * Método que se utiliza para establecer el atributo accesoServicio, de una
     * instancia de clase
     *
     * @param accesoServicio El parámetro accesoServicio define la forma en que
     * un paciente accedio al servicio de asesoria
     */
    public void setAccesoServicio(String accesoServicio) {
        this.accesoServicio = accesoServicio;
    }

    /**
     * Método que devuelve el nombre de la categoria de la persona que remitio a
     * un paciente al servicio de asesoría
     *
     * @return remitidoPor, es el atributo que representa el nombre de la
     * categoria de la persona que remitio a un paciente a una asesoría
     */
    public String getRemitidoPor() {
        return remitidoPor;
    }

    /**
     * Método que se utiliza para establecer el atributo remitidoPor, de una
     * instancia de clase
     *
     * @param remitidoPor El parámetro remitidoPor define el tipo de persona que
     * remitio a un paciente a una asesoría
     */
    public void setRemitidoPor(String remitidoPor) {
        this.remitidoPor = remitidoPor;
    }

    /**
     * Método que devuelve la problematica de un paciente, que sera trabajada
     * por un psicologo en una asesoria
     *
     * @return problematicaRefTrab, es el atributo que representa la
     * problematica de un paciente, identificada por un psicologo y que sera
     * trabajada en una asesoria
     */
    public String getProblematicaRefTrab() {
        return problematicaRefTrab;
    }

    /**
     * Método que se utiliza para establecer el atributo problematicaRefTrab, de
     * una instancia de clase
     *
     * @param problematicaRefTrab El parámetro problematicaRefTrab define la
     * problematica de un paciente indentificada por un psicologo y sera
     * trabajada en una asesoria
     */
    public void setProblematicaRefTrab(String problematicaRefTrab) {
        this.problematicaRefTrab = problematicaRefTrab;
    }

    /**
     * Método que devuelve los acuerdos o compromisos que se realizaron entre un
     * psicologo y un paciente
     *
     * @return accionesRealizadas, es el atributo que representa los acuerdos
     * definidos entre un psicologo y un paciente
     */
    public String getAccionesRealizadas() {
        return accionesRealizadas;
    }

    /**
     * Método que se utiliza para establecer el atributo accionesRealizadas, de
     * una instancia de clase
     *
     * @param accionesRealizadas El parámetro accionesRealizadas define los
     * acuerdos que se realicen entre un paciente y un paciente
     */
    public void setAccionesRealizadas(String accionesRealizadas) {
        this.accionesRealizadas = accionesRealizadas;
    }

    /**
     * Método que devuelve el tipo de asistenica que tuvo un paciente respecto a
     * una asesoría programada
     *
     * @return asistencia, es el atibuto que representa como fue la asistencia
     * de un paciente para una asesoria ya programada
     */
    public String getAsistencia() {
        return asistencia;
    }

    /**
     * Método que se utiliza para establecer el atributo asistencia, de una
     * instancia de clase
     *
     * @param asistencia El parámetro asistencia define el tipo de asistenica
     * que tuvo un paciente en una asesoria ya programada
     */
    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    /**
     * Método que devuelve el estado de como termina una asesoría con un
     * paciente
     *
     * @return culminacion, es el atributo que representa el esatdo de la
     * finalización de cada asesoria
     */
    public String getCulminacion() {
        return culminacion;
    }

    /**
     * Método que se utiliza para establecer el atributo culminacion, de una
     * instancia de clase
     *
     * @param culminacion El parámetro culminacion define el estado en que
     * termina una asesoría
     */
    public void setCulminacion(String culminacion) {
        this.culminacion = culminacion;
    }

    /**
     * Método que devuelve el tippo de persona o entidad a la cual será remitido
     * el paciente
     *
     * @return remitidoA, el atributo que representa el nombre de la categoria
     * de la persona o entidad a la cual sera remitido el paciente
     */
    public String getRemitidoA() {
        return remitidoA;
    }

    /**
     * Método que se utiliza para establecer el atributo remitidoA, de una
     * instancia de clase
     *
     * @param remitidoA El parámetro remitidoA define el nombre de la categoria
     * de la persona o entidad a la cual sera remitido el paciente
     */
    public void setRemitidoA(String remitidoA) {
        this.remitidoA = remitidoA;
    }

    /**
     * Método que devuelve los comentarios extras que realizo un psicolgo en una
     * asesoria en especifico
     *
     * @return observaciones, el atributo que representa los comentarios extras
     * que realiza un psicologo
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Método que se utiliza para establecer el atributo observaciones, de una
     * instancia de clase
     *
     * @param observaciones El parámetro observaciones define los comentarios
     * extras que realiza un psicologo en una asesoría
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Método que devuelve una instacia de tipo Paciente y asi poder hacer uso
     * de sus métodos y atributos
     *
     * @return devuelve una instacia de tipo paciente, es decir devuelve un
     * objeto Paciente en especifico
     */
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * Método que se utiliza para establecer el atributo paciente de una
     * isntaciia de clase
     *
     * @param paciente El parámetro paciente define una instancia de tipo
     * Paciente para obtener los métodos y atributos de la Clase Paciente
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /**
     * Método que devuelve una instacia de tipo Psicologo, para obtener los
     * métodos y atributos de la clase Psicologo
     *
     * @return devuelve una instacia de tipo Psicologo, es decir devuelve un
     * objeto Psicologo en especifo
     */
    public Psicologo getPsicologo() {
        return psicologo;
    }

    /**
     * Método que se utiliza para establecer el atributo psicologo que es una
     * variable de tipo Psicologo
     *
     * @param psicologo El parámetro psicologo define una instancia de tipo
     * Psicologo para obtener los métodos y atributos de la Clase Psicologo
     */
    public void setPsicologo(Psicologo psicologo) {
        this.psicologo = psicologo;
    }

    /**
     * Método que devuelve el codigo hash que se le fue asignado a cada objeto
     * instanciado de la clase
     *
     * @return un int que es el codígo hash de la instacia de clase
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numAsesoria != null ? numAsesoria.hashCode() : 0);
        return hash;
    }

    /**
     * Método que devuelve true o false que es resultado de una comparación
     * entre dos objetos para saber si su tipo y nombre coinciden
     *
     * @param object El parámetro object es cualquier objeto que se le envia al
     * metódo para ser comparado
     * @return true o false, dependidiendo del resultado de la comparación entre
     * los dos objetos
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asesoria)) {
            return false;
        }
        Asesoria other = (Asesoria) object;
        if ((this.numAsesoria == null && other.numAsesoria != null) || (this.numAsesoria != null && !this.numAsesoria.equals(other.numAsesoria))) {
            return false;
        }
        return true;
    }

    /**
     * Método que devuelve una cadena de caracteres especificando el numero de
     * una asesoría
     *
     * @return devuelve un mensaje mostrando el numero de una asesoria en
     * especifico
     */
    @Override
    public String toString() {
        return "co.edu.usbbog.paoe.model.Asesoria[ numAsesoria=" + numAsesoria + " ]";
    }

}
