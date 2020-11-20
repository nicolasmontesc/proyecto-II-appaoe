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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * Etiqueta que indica que esta clase representa la entidad Facultad en el
 * modelo de datos
 */
@Entity
/**
 * Etiqueta que hace eferenica a la base de datos a la cual pertenece la clase
 */
@Table(name = "programas_estudiante", catalog = "paoe_db", schema = "")
@XmlRootElement

/**
 *
 */
@NamedQueries({
    @NamedQuery(name = "ProgramasEstudiante.findAll", query = "SELECT p FROM ProgramasEstudiante p")
    , @NamedQuery(name = "ProgramasEstudiante.findByPaciente", query = "SELECT p FROM ProgramasEstudiante p WHERE p.programasEstudiantePK.paciente = :paciente")
    , @NamedQuery(name = "ProgramasEstudiante.findByPrograma", query = "SELECT p FROM ProgramasEstudiante p WHERE p.programasEstudiantePK.programa = :programa")
    , @NamedQuery(name = "ProgramasEstudiante.findByIngreso", query = "SELECT p FROM ProgramasEstudiante p WHERE p.ingreso = :ingreso")
    , @NamedQuery(name = "ProgramasEstudiante.findByEstado", query = "SELECT p FROM ProgramasEstudiante p WHERE p.estado = :estado")
    , @NamedQuery(name = "ProgramasEstudiante.findByEgreso", query = "SELECT p FROM ProgramasEstudiante p WHERE p.egreso = :egreso")})

/**
 * Esta clase define los atributos necesarios, con sus respectivos métodos;
 * getters/settes, hashcode, equals y toString
 */
public class ProgramasEstudiante implements Serializable {

    /**
     * Campos o atributos de clase
     */
    /**
     * Declaración e inicialización de la constante serialVersionUID, para
     * asignarle un numero que identifique a la clase Asesoria
     */
    private static final long serialVersionUID = 1L;

    /**
     * Atributo que represnta el atributo identificador de la clase
     * ProgramasEstudiante, define el número del documento del paciente y el
     * nombre del programa al cual pertence este paciente que es un estudiante.
     */
    @EmbeddedId
    protected ProgramasEstudiantePK programasEstudiantePK;

    /**
     * Atributo que define la fecha de ingreso del estudiante al programa
     */
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ingreso;

    /**
     * Atributo que define como se encuentra el estado de un programa en curso
     * por un estduiante en especifico
     */
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String estado;

    /**
     * Atributo que define la fecha en que el estudiante termino el programa
     */
    @Temporal(TemporalType.DATE)
    private Date egreso;

    /**
     * Atributo que define una variable de tipo Paciente, para luego poder
     * instaciar la clase Paciente
     */
    @JoinColumn(name = "paciente", referencedColumnName = "documento", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Paciente paciente1;

    /**
     * Atributo que define una variable de tipo Programa, para luego poder
     * instaciar la clase Programa
     */
    @JoinColumn(name = "programa", referencedColumnName = "nom_programa", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Programa programa1;

    /**
     * Constructor vacio de la clase para instanciar un onjeto sin establecer
     * los campos de la clase.
     */
    public ProgramasEstudiante() {
    }

    /**
     * Constructor de la clase para instanciar un objeto estableciendo el campo
     * de clase programasEstudiantePK
     *
     * @param programasEstudiantePK El parámetro programasEstudiantePK define el
     * número del documento del paciente y el nombre del programa al cual
     * pertence este paciente que es un estudiante.
     */
    public ProgramasEstudiante(ProgramasEstudiantePK programasEstudiantePK) {
        this.programasEstudiantePK = programasEstudiantePK;
    }

    /**
     * Constructor de la clase para instanciar un objeto estableciendo los
     * campos de clase programasEstudiantePK, ingreso, estado
     *
     * @param programasEstudiantePK El parámetro programasEstudiantePK define el
     * número del documento del paciente y el nombre del programa al cual
     * pertence este paciente que es un estudiante. Que es el atributo
     * identificador de la clase ProgramasEstudiante
     * @param ingreso El parámetro ingreso define la fecha de cuando un
     * estudainte inicio un programa
     * @param estado El parámetro estado define como se encuentra el estado de
     * un programa en curso por un estduiante en especifico
     */
    public ProgramasEstudiante(ProgramasEstudiantePK programasEstudiantePK, Date ingreso, String estado) {
        this.programasEstudiantePK = programasEstudiantePK;
        this.ingreso = ingreso;
        this.estado = estado;
    }

    /**
     * Constructor de la clase para instanciar un objeto estableciendo los
     * campos de clase programasEstudiantePK, ingreso, estado
     *
     * @param programasEstudiantePK El parámetro programasEstudiantePK define el
     * número del documento del paciente y el nombre del programa al cual
     * pertence este paciente que es un estudiante. Que es el atributo
     * identificador de la clase ProgramasEstudiante
     * @param ingreso El parámetro ingreso define la fecha de cuando un
     * estudainte inicio un programa
     * @param estado El parámetro estado define como se encuentra el estado de
     * un programa en curso por un estduiante en especifico
     * @param egreso El parámetro egreso define la fecha en que el estudiante
     * termino el programa
     */
    public ProgramasEstudiante(ProgramasEstudiantePK programasEstudiantePK, Date ingreso, String estado, Date egreso) {
        this.programasEstudiantePK = programasEstudiantePK;
        this.ingreso = ingreso;
        this.estado = estado;
        this.egreso = egreso;
    }

    /**
     * Constructor de la clase para instanciar un objeto estableciendo los
     * campos de clase paciente y programa, Este constructor es para formar el
     * atributo identificador de la clase ProgramasEstudiante
     *
     * @param paciente El parámetro paciente define el número de documento que
     * identifica a un paciente en especifico
     * @param programa El parámetro programa define el nombre especifico de un
     * programa el cual es unico e irrepetible
     */
    public ProgramasEstudiante(int paciente, String programa) {
        this.programasEstudiantePK = new ProgramasEstudiantePK(paciente, programa);
    }

    /**
     * Método que devuelve el atributo identificador que es el número de
     * documento del paciente y el nombre del programa al cual pertence este
     * paciente que es un estudiante
     *
     * @return programasEstudiantePK, es el atributo que representa el número de
     * documento del paciente y el nombre del programa
     */
    public ProgramasEstudiantePK getProgramasEstudiantePK() {
        return programasEstudiantePK;
    }

    /**
     * Método que se utiliza para establecer el atributo programasEstudiantePK
     * de una instacia de clase
     *
     * @param programasEstudiantePK El parámetro programasEstudiantePK define el
     * número de documento del paciente y el nombre del programa al cual
     * pertence este paciente que es un estudiante. Que es el atributo
     * identificador de la clase ProgramasEstudiante
     */
    public void setProgramasEstudiantePK(ProgramasEstudiantePK programasEstudiantePK) {
        this.programasEstudiantePK = programasEstudiantePK;
    }

    /**
     * Método que devuelve la fecha de ingreso del estudiante al programa
     *
     * @return ingreso, es el atributo que representa la fecha de cuando un
     * estudainte inicio un programa
     */
    public Date getIngreso() {
        return ingreso;
    }

    /**
     * Método que se utiliza para establecer el atributo ingreso de una instacia
     * de clase
     *
     * @param ingreso El parámetro ingreso define la fecha de cuando el
     * estudainte inicio el programa
     */
    public void setIngreso(Date ingreso) {
        this.ingreso = ingreso;
    }

    /**
     * Método que devuelve el estado de un programa en curso por un estduiante
     * en especifico
     *
     * @return estado, es el atribuo que representa como se encuentra el estado
     * de un programa en curso por un estduiant
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Método que se utiliza para establecer el atributo estado de una instacia
     * de clase
     *
     * @param estado El parámetro estado define como se encuentra el estado de
     * un programa en curso por un estduiante en especifico
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Método que devuelve la fecha de cuando un estudiante termino un programa
     *
     * @return estado, es el atribuo que representa la fecha de cuando un
     * estudiante termino un programa
     */
    public Date getEgreso() {
        return egreso;
    }

    /**
     * Método que se utiliza para establecer el atributo egreso de una instancia
     * de clase
     *
     * @param egreso El parámetro egreso define la fecha de cuando un estudiante
     * termino un programa
     */
    public void setEgreso(Date egreso) {
        this.egreso = egreso;
    }

    /**
     * Método que devuelve una instacia de tipo Paciente y asi poder hacer uso
     * de sus métodos y atributos
     *
     * @return devuelve una instacia de tipo paciente, es decir devuelve un
     * objeto Paciente en especifico
     */
    public Paciente getPaciente1() {
        return paciente1;
    }

    /**
     * Método que se utiliza para establecer el atributo paciente1 de una
     * instacia de clase
     *
     * @param paciente1 El parámetro paciente1 define una instancia de tipo
     * Paciente para obtener los métodos y atributos de la Clase Paciente
     */
    public void setPaciente1(Paciente paciente1) {
        this.paciente1 = paciente1;
    }

    /**
     * Método que devuelve una instacia de tipo Programa y asi poder hacer uso
     * de sus métodos y atributos
     *
     * @return devuelve una instacia de tipo Programa, es decir devuelve un
     * objeto Programa en especifico
     */
    public Programa getPrograma1() {
        return programa1;
    }

    /**
     * Método que se utiliza para establecer el atributo programa1 de una
     * instacia de clase
     *
     * @param programa1 El parámetro programa1 define instancia de tipo Programa
     * para obtener los métodos y atributos de la Clase Programa
     */
    public void setPrograma1(Programa programa1) {
        this.programa1 = programa1;
    }

    /**
     * Método que devuelve el codigo hash que se le fue asignado a cada objeto
     * instanciado de la clase
     *
     * @return un int que es el codígo hash de una de las instacias de clase
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (programasEstudiantePK != null ? programasEstudiantePK.hashCode() : 0);
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
        if (!(object instanceof ProgramasEstudiante)) {
            return false;
        }
        ProgramasEstudiante other = (ProgramasEstudiante) object;
        if ((this.programasEstudiantePK == null && other.programasEstudiantePK != null) || (this.programasEstudiantePK != null && !this.programasEstudiantePK.equals(other.programasEstudiantePK))) {
            return false;
        }
        return true;
    }

    /**
     * Método que devuelve una cadena de caracteres especificando el atributo
     * identificador de la clase, que es la unión entre el documento del
     * paciente y el nommbre del programa al cual pertence el paciente
     *
     * @return devuelve un mensaje mostrando atributo identificador de la clase,
     * la unión entre el documento del paciente y el nommbre del programa al
     * cual pertence el paciente
     */
    @Override
    public String toString() {
        return "co.edu.usbbog.paoe.model.ProgramasEstudiante[ programasEstudiantePK=" + programasEstudiantePK + " ]";
    }

}
