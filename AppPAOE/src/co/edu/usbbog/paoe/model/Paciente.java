/**
 * Paquete que se encargar de albergar las entidades del modelo de datos
 */
package co.edu.usbbog.paoe.model;

/**
 * Estos son los import necesarios para utilizar el api de Java JPA y para el
 * correcto manejo de los atributos
 */
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Juan Nicolas, Juan Sebastían, Santiago Echeverri
 * @version 15/09/2020/1.0
 * @serial JPA
 * @since 15/09/2020
 */
/**
 * Etiqueta que indica que esta clase representa la entidad Paciente en el
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
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p")
    , @NamedQuery(name = "Paciente.findByDocumento", query = "SELECT p FROM Paciente p WHERE p.documento = :documento")
    , @NamedQuery(name = "Paciente.findByTipoConsultante", query = "SELECT p FROM Paciente p WHERE p.tipoConsultante = :tipoConsultante")
    , @NamedQuery(name = "Paciente.findByNombres", query = "SELECT p FROM Paciente p WHERE p.nombres = :nombres")
    , @NamedQuery(name = "Paciente.findByApellidos", query = "SELECT p FROM Paciente p WHERE p.apellidos = :apellidos")
    , @NamedQuery(name = "Paciente.findByTelefono", query = "SELECT p FROM Paciente p WHERE p.telefono = :telefono")
    , @NamedQuery(name = "Paciente.findByLocalidad", query = "SELECT p FROM Paciente p WHERE p.localidad = :localidad")
    , @NamedQuery(name = "Paciente.findByEdad", query = "SELECT p FROM Paciente p WHERE p.edad = :edad")
    , @NamedQuery(name = "Paciente.findByGenero", query = "SELECT p FROM Paciente p WHERE p.genero = :genero")
    , @NamedQuery(name = "Paciente.findByFinanciacion", query = "SELECT p FROM Paciente p WHERE p.financiacion = :financiacion")
    , @NamedQuery(name = "Paciente.findByCodigo", query = "SELECT p FROM Paciente p WHERE p.codigo = :codigo")
    , @NamedQuery(name = "Paciente.findBySemestre", query = "SELECT p FROM Paciente p WHERE p.semestre = :semestre")})

/**
 * Esta clase define los atributos necesarios, con sus respectivos métodos;
 * getters/settes, hashcode, equals y toString
 */
public class Paciente implements Serializable {

    /**
     * campos o atributos de la clase
     */
    /**
     * Declaración e inicialización de la constante serialVersionUID, para
     * asignarle un numero que identifique a la clase Paciente
     */
    private static final long serialVersionUID = 1L;

    /**
     * Atributo que represnta el atributo identificador de la clase Paciente y
     * representa el número de documento que reconce a un paciente en especifico
     */
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer documento;

    /**
     * Atributo que define el tipo de consultante que es un paciente es decir si
     * es un estudiante, un profesor ect, y como esta catalogado dentro de una
     * asesoría
     */
    @Basic(optional = false)
    @Column(name = "tipo_consultante", nullable = false, length = 45)
    private String tipoConsultante;

    /**
     * Atributo que representa los nombres un paciente
     */
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String nombres;

    /**
     * Atributo que representa los apellidos de un paciente
     */
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String apellidos;

    /**
     * Atributo que representa el número de telefono para contactar a un
     * paciente
     */
    @Basic(optional = false)
    @Column(nullable = false)
    private int telefono;

    /**
     * Atributo que define el nombre de la localidad donde recide un paciente
     */
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String localidad;

    /**
     * Atributo que representa los años que tiene un paciente al momento de
     * realizar la asesoría
     */
    @Basic(optional = false)
    @Column(nullable = false)
    private int edad;

    /**
     * Atributo que representa el genero de un paciente
     */
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String genero;

    /**
     * Atributo que representa el tipo de financiacion que tiene un estudiante,
     * que es uno de los tipos de consultantes
     */
    @Column(length = 45)
    private String financiacion;

    /**
     * Atributo que representa el codigo que identifica a cada estudiante,
     * estudiante es uno de los tipos de consultantes
     */
    @Column(length = 45)
    private String codigo;

    /**
     * Atributo que representa el semestre en el que se encuentra un estudiante,
     * estudiante es uno de los tipos de consultantes
     */
    @Column(length = 45)
    private String semestre;

    /**
     * Atributo que representa la lista de programas que esta cursando un
     * estudiante
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente1")
    private List<ProgramasEstudiante> programasEstudianteList;

    /**
     * Atributo que representa la lista de asesorías que tiene registradas un
     * paciente
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente")
    private List<Asesoria> asesoriaList;

    /**
     * Constructor vacio de la clase para instanciar un onjeto sin establecer
     * los campos de la clase.
     */
    public Paciente() {
    }

    /**
     * Constructor de la clase para instanciar un objeto estableciendo el
     * atributo documento
     *
     * @param documento El parámetro documento define el número de documento que
     * identifica a cada paciente
     */
    public Paciente(Integer documento) {
        this.documento = documento;
    }

    /**
     * Constructor de la clase para instanciar un objeto estableciendo los
     * campos de clase documento, tipoConsultante, nombres, apellidos, telefono,
     * localidad, edad, genero
     *
     * @param documento El parámetro documento define el número de documento que
     * identifica a cada paciente
     * @param tipoConsultante El parámetro tipoConsultante define el tipo de
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
     */
    public Paciente(Integer documento, String tipoConsultante, String nombres, String apellidos, int telefono, String localidad, int edad, String genero) {
        this.documento = documento;
        this.tipoConsultante = tipoConsultante;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.localidad = localidad;
        this.edad = edad;
        this.genero = genero;
    }

    /**
     * Constructor de la clase para instanciar un objeto estableciendo los
     * campos de clase documento, tipoConsultante, nombres, apellidos, telefono,
     * localidad, edad, genero, financiacion, codigo, semestre
     *
     * @param documento El parámetro documento define el número de documento que
     * identifica a cada paciente
     * @param tipoConsultante El parámetro tipoConsultante define el tipo de
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
     */
    public Paciente(Integer documento, String tipoConsultante, String nombres, String apellidos, int telefono, String localidad,
            int edad, String genero, String financiacion, String codigo, String semestre) {
        this.documento = documento;
        this.tipoConsultante = tipoConsultante;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.localidad = localidad;
        this.edad = edad;
        this.genero = genero;
        this.financiacion = financiacion;
        this.codigo = codigo;
        this.semestre = semestre;
    }

    /**
     * Método que devuelve el número de documento que identifica a un paciente
     *
     * @return documento, es el atributo que representa el número de documento
     * que identifica a un paciente
     */
    public Integer getDocumento() {
        return documento;
    }

    /**
     * Método que se utiliza para establecer el atributo documento de una
     * instancia de clase
     *
     * @param documento El parámetro documento define el número de documento que
     * identifica a un paciente
     */
    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    /**
     * Método que devuelve el tipo de consultante que es un paciente, es decir,
     * si es un estudiante, un profesor ect.
     *
     * @return tipoConsultante, es el atributo que representa el tipo de
     * consultante que puede ser un paciente
     */
    public String getTipoConsultante() {
        return tipoConsultante;
    }

    /**
     * Método que se utiliza para establecer el atributo tipoConsultante de una
     * instacia de clase
     *
     * @param tipoConsultante El parámetro tipoConsultante define el tipo de
     * consultante que puede ser es un paciente y como esta catalogado dentro de
     * una asesoría
     */
    public void setTipoConsultante(String tipoConsultante) {
        this.tipoConsultante = tipoConsultante;
    }

    /**
     * Método que devuelve los nombres de un paciente
     *
     * @return nombres, el atributo que representa los nombre de un paciente
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Método que se utiliza para establecer el atributo nombres de una instacia
     * de clase
     *
     * @param nombres El parámetro nombres define los nombre de un paciente
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Método que devuelve los apellidos de un paciente
     *
     * @return apellidos, es el atributo que representa los apeliidos de un
     * paciente
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Método que se utiliza para establecer el atributo apellidos de una
     * instacia de clase
     *
     * @param apellidos El parámetro apellidos define los apellidos del paciente
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Método que devuelve el número de telefono de un paciente para contactarlo
     *
     * @return telefono, es el atributo que representa el número de telefono de
     * un paciente
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * Método que se utiliza para establecer el atributo telefono de una
     * instacia de clase
     *
     * @param telefono El parámetro telefono define el número de telefono para
     * contactar a un paciente
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     * Método que devuelve el nombre de la localidad donde recide un paciente
     *
     * @return localidad, es el atributo que representa el nombre de la
     * localidad donde recide un paciente
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * Método que se utiliza para establecer el atributo localidad de una
     * instancia de clase
     *
     * @param localidad El parámetro localidad define el nombre de la localidad
     * donde recide un paciente
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     * Método que devuelve los años que tiene un paciente
     *
     * @return edad, es el atributo que representa los años que tiene un
     * paciente
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Método que se utiliza para establecer el atributo edad, de una instancia
     * de clase
     *
     * @param edad El parámetro edad define los años que tiene un paciente al
     * momento de realizar una asesoría
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Método que devuelve el genero de un paciente
     *
     * @return genero, es atributo que representa el genero de un paciente
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Método que se utiliza para establecer el atributo genero de una instancia
     * de clase
     *
     * @param genero El parámetro genero define el genero de un paciente;
     * masculino, femenino u otros
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Método que devuelve el tipo de financiacion que tiene un estudiante
     *
     * @return financiacion, es el atributo que representa el tipo de
     * financiación de un estudiante;
     */
    public String getFinanciacion() {
        return financiacion;
    }

    /**
     * Método que se utiliza para establecer el atributo de financiacion de una
     * instacia de clase
     *
     * @param financiacion El parámetro financiacion define el tipo de
     * financiacion que tiene un estudiante
     */
    public void setFinanciacion(String financiacion) {
        this.financiacion = financiacion;
    }

    /**
     * Método que devuelve el codigo que identifica a cada estudiante
     *
     * @return codigo, es el atributo que representa el codigo identificador de
     * un estudiante
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Método que se utiliza para establecer el atributo codigo de una instancia
     * de clase
     *
     * @param codigo El parámetro codigo define el codigo que identifica a un
     * estudiante
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Método que devuelve el semestre en el que se encuentra un estudiante
     *
     * @return semestre, es el atributo que representa el semestre de un
     * estudiante
     */
    public String getSemestre() {
        return semestre;
    }

    /**
     * Método que se utiliza para establecer el atributo semestre de una
     * instancia de clase
     *
     * @param semestre El parámetro semestre define el semestre en el que se
     * encuentra un estudiante
     */
    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    /**
     * Método que devuelve la lista de programas, los cuales esta cursando un
     * estudiante
     *
     * @return programasEstudianteList, es el atributo que representa la lista
     * de programas que esta cursando un estudiante
     */
    @XmlTransient
    public List<ProgramasEstudiante> getProgramasEstudianteList() {
        return programasEstudianteList;
    }

    /**
     * Método que se utiliza para establecer el atributo programasEstudianteList
     * en una instancia de clase
     *
     * @param programasEstudianteList El parámetro programasEstudianteList
     * define la lista de programas, los cuales esta cursando un estudiante
     */
    public void setProgramasEstudianteList(List<ProgramasEstudiante> programasEstudianteList) {
        this.programasEstudianteList = programasEstudianteList;
    }

    /**
     * Método que devuelve la lista de asesorías que tiene registradas un
     * paciente
     *
     * @return asesoriaList, es el atributo que representa la lista de asesorías
     * que tiene registradas un paciente a su nombre
     */
    @XmlTransient
    public List<Asesoria> getAsesoriaList() {
        return asesoriaList;
    }

    /**
     * Método que se utiliza para establecer el atributo asesoriaList en una
     * instancia de clase
     *
     * @param asesoriaList El parámetro asesoriaList define la lista de
     * asesorías que tiene registradas un paciente a su nombre
     */
    public void setAsesoriaList(List<Asesoria> asesoriaList) {
        this.asesoriaList = asesoriaList;
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
        hash += (documento != null ? documento.hashCode() : 0);
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
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.documento == null && other.documento != null) || (this.documento != null && !this.documento.equals(other.documento))) {
            return false;
        }
        return true;
    }

    /**
     * Método que devuelve una cadena de caracteres especificando el No. de
     * documento de un paciente en especifico
     *
     * @return devuelve un mensaje mostrando el No. de documento de un paciente
     * en especifico
     */
    @Override
    public String toString() {
        return "co.edu.usbbog.paoe.model.Paciente[ documento=" + documento + " ]";
    }

}
