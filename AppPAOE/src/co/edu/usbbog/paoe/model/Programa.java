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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * Etiqueta que indica que esta clase representa la entidad Programa en el
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
    @NamedQuery(name = "Programa.findAll", query = "SELECT p FROM Programa p")
    , @NamedQuery(name = "Programa.findByNomPrograma", query = "SELECT p FROM Programa p WHERE p.nomPrograma = :nomPrograma")
    , @NamedQuery(name = "Programa.findByModalidad", query = "SELECT p FROM Programa p WHERE p.modalidad = :modalidad")
    , @NamedQuery(name = "Programa.findByTelefono", query = "SELECT p FROM Programa p WHERE p.telefono = :telefono")
    , @NamedQuery(name = "Programa.findByCorreo", query = "SELECT p FROM Programa p WHERE p.correo = :correo")
    , @NamedQuery(name = "Programa.findByNomDirector", query = "SELECT p FROM Programa p WHERE p.nomDirector = :nomDirector")})

/**
 * Esta clase define los atributos necesarios, con sus respectivos métodos;
 * getters/settes, hashcode, equals y toString
 */
public class Programa implements Serializable {

    /**
     * Campos o atributos de la clase
     */
    /**
     * Declaración e inicialización de la constante serialVersionUID, para
     * asignarle un numero que identifique a la clase Programa
     */
    private static final long serialVersionUID = 1L;

    /**
     * Atributo que represnta el atributo identificador de la clase Programa y
     * define el nombre especifico de cada programa el cual es unico e
     * irrepetible
     */
    @Id
    @Basic(optional = false)
    @Column(name = "nom_programa", nullable = false, length = 45)
    private String nomPrograma;

    /**
     * Atributo que define la modalidad en que se toma el programa; si es
     * virtual o presencial
     */
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String modalidad;

    /**
     * Atributo que define el número de telefono que posee una programa
     */
    @Basic(optional = false)
    @Column(nullable = false)
    private int telefono;

    /**
     * Atributo que define el correo electronico que posee una facultad
     */
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String correo;

    /**
     * Atributo que define el nombre del director, que esta encarcado de un
     * programa en especifico
     */
    @Basic(optional = false)
    @Column(name = "nom_director", nullable = false, length = 45)
    private String nomDirector;

    /**
     * Atributo que define una variable de tipo Facultad, para luego poder
     * instaciar la clase Psicologo
     */
    @JoinColumn(name = "facultad", referencedColumnName = "nom_facultad", nullable = false)
    @ManyToOne(optional = false)
    private Facultad facultad;

    /**
     * Atributo que representa la lista de estudiantes que tiene registrado cada
     * programa, que pertenece a una facultad en especifico
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programa1")
    private List<ProgramasEstudiante> programasEstudianteList;

    /**
     * Constructor vacio de la clase para instanciar un onjeto sin establecer
     * los campos de la clase.
     */
    public Programa() {
    }

    /**
     * Constructor de la clase para instanciar un objeto estableciendo el campo
     * de clase nomPrograma
     *
     * @param nomPrograma El parámetro nomPrograma define el nombre especifico
     * de cada programa el cual es unico e irrepetible
     */
    public Programa(String nomPrograma) {
        this.nomPrograma = nomPrograma;
    }

    /**
     * Constructor de la clase para instanciar un objeto estableciendo los
     * campos de clase nomPrograma, modalidad, telefono, correo, nomDirector
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
     */
    public Programa(String nomPrograma, String modalidad, int telefono, String correo, String nomDirector) {
        this.nomPrograma = nomPrograma;
        this.modalidad = modalidad;
        this.telefono = telefono;
        this.correo = correo;
        this.nomDirector = nomDirector;
    }

    /**
     * Constructor de la clase para instanciar un objeto estableciendo los
     * campos de clase nomPrograma, modalidad, telefono, correo, nomDirector
     *
     * @param nomPrograma El parámetro nomPrograma define el nombre especifico
     * de cada programa el cual es unico e irrepetible
     * @param modalidad El parámetro modalidad define la modalidad en que se
     * toma el programa; si es virtual o presencial
     * @param telefono El parámetro telefono define el número de telefono que
     * posee cada programa
     * @param correo El parámetro correo define el correo electronico que posee
     * cada programa
     * @param nomDirector El parámetro nomDirector define el nombre del
     * director, que esta encarcado de un programa en especifico
     * @param facultad El parámetro facultad define una instancia de tipo
     * Facultad para obtener los métodos y atributos de la Clase Facultad
     */
    public Programa(String nomPrograma, String modalidad, int telefono, String correo, String nomDirector, Facultad facultad) {
        this.nomPrograma = nomPrograma;
        this.modalidad = modalidad;
        this.telefono = telefono;
        this.correo = correo;
        this.nomDirector = nomDirector;
        this.facultad = facultad;
    }

    /**
     * Método que devuelve el nombre del programa que se haya solicitado
     *
     * @return nomPrograma es el atributo que especifica el nombre de cada
     * programa
     */
    public String getNomPrograma() {
        return nomPrograma;
    }

    /**
     * Método que se utiliza para establecer el atributo nomPrograma de una
     * instancia de clase
     *
     * @param nomPrograma El parámetro nomPrograma define el nombre especifico
     * de cada programa el cual es unico e irrepetible
     */
    public void setNomPrograma(String nomPrograma) {
        this.nomPrograma = nomPrograma;
    }

    /**
     * Método que devuelve la modalidad en que se toma el programa; si es
     * virtual o presencial
     *
     * @return modalidad es el atributo que representa la modalidad en que se
     * toma el programa.
     */
    public String getModalidad() {
        return modalidad;
    }

    /**
     * Método que se utiliza para establecer el atributo modalidad de una
     * instancia de clase
     *
     * @param modalidad es el atributo que representa la modalidad en que se
     * toma el programa.
     */
    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    /**
     * Método que devuelve el número del telefono que posee cada programa
     *
     * @return telefono es el atributo que representa el número de telefono de
     * un programa en especifico
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * Método que se utiliza para establecer el atributo de telefono de una
     * instancia de clase
     *
     * @param telefono El parámetro telefono define el número de telefono que
     * posee cada programa
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     * Método que devuelve el correo electronico que posee cada programa
     *
     * @return correo es el atributo que representa el correo electronico de un
     * programa un especifico
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Método que se utiliza para establecer el atributo correo de una instacia
     * de clase
     *
     * @param correo El parámetro correo define el correo electronico que posee
     * un programa en especifico
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Método que devuelve el nombre del director de cada programa
     *
     * @return nomDirector es el atributo que representa el nombre del directo
     * que esta encargado de un prograna en concreto
     */
    public String getNomDirector() {
        return nomDirector;
    }

    /**
     * Método que se utiliza para establecer el atributo de nomDirector de una
     * instancia de clase
     *
     * @param nomDirector El parámetro nomDirector define el nombre del
     * director, que esta encarcado de un programa en especifico
     */
    public void setNomDirector(String nomDirector) {
        this.nomDirector = nomDirector;
    }

    /**
     * Método que devuelve una instancia de tipo Facultad y así obtener metodos
     * y atributos de la Facultad
     *
     * @return devuelve una instacia de tipo Facultad, es decir devuelve un
     * objeto Facultad en especifo
     */
    public Facultad getFacultad() {
        return facultad;
    }

    /**
     * Método que se utiliza para establecer el atributo facultad de una
     * instacia de clase
     *
     * @param facultad El parámetro facultad define una instancia de tipo
     * Facultad para obtener los métodos y atributos de la Clase Facultad
     */
    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    /**
     * Método que devuelve la lista de los estudiantes que se encuentran
     * registrados en un programa en especifico
     *
     * @return programasEstudianteList es el atributo que representa la lista de
     * estudiantes que tiene un programa en especifico
     */
    @XmlTransient
    public List<ProgramasEstudiante> getProgramasEstudianteList() {
        return programasEstudianteList;
    }

    /**
     * Método que se utiliza para establecer el atributo de
     * programasEstudianteList de una instacia de clase
     *
     * @param programasEstudianteList El parámetro programasEstudianteList
     * define la lista de estudiantes que tiene registrado un programa en
     * especifico
     */
    public void setProgramasEstudianteList(List<ProgramasEstudiante> programasEstudianteList) {
        this.programasEstudianteList = programasEstudianteList;
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
        hash += (nomPrograma != null ? nomPrograma.hashCode() : 0);
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
        if (!(object instanceof Programa)) {
            return false;
        }
        Programa other = (Programa) object;
        if ((this.nomPrograma == null && other.nomPrograma != null) || (this.nomPrograma != null && !this.nomPrograma.equals(other.nomPrograma))) {
            return false;
        }
        return true;
    }

    /**
     * Método que devuelve una cadena de caracteres especificando el nommbre del
     * programa
     *
     * @return devuelve un mensaje mostrando el nombre de un ptograma en
     * especifico
     */
    @Override
    public String toString() {
        return "co.edu.usbbog.paoe.model.Programa[ nomPrograma=" + nomPrograma + " ]";
    }

}
