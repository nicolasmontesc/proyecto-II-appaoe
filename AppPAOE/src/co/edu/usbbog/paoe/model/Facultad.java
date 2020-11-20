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
 * Etiqueta que indica que esta clase representa la entidad Facultad en el
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
    @NamedQuery(name = "Facultad.findAll", query = "SELECT f FROM Facultad f")
    , @NamedQuery(name = "Facultad.findByNomFacultad", query = "SELECT f FROM Facultad f WHERE f.nomFacultad = :nomFacultad")
    , @NamedQuery(name = "Facultad.findByTelefono", query = "SELECT f FROM Facultad f WHERE f.telefono = :telefono")
    , @NamedQuery(name = "Facultad.findByCorreo", query = "SELECT f FROM Facultad f WHERE f.correo = :correo")
    , @NamedQuery(name = "Facultad.findByNomDecano", query = "SELECT f FROM Facultad f WHERE f.nomDecano = :nomDecano")})

/**
 * Esta clase define los atributos necesarios, con sus respectivos métodos;
 * getters/settes, hashcode, equals y toString
 */
public class Facultad implements Serializable {

    /**
     * Campos o atributos de la clase
     */
    /**
     * Declaración e inicialización de la constante serialVersionUID, para
     * asignarle un numero que identifique a la clase Facultad
     */
    private static final long serialVersionUID = 1L;

    /**
     * Atributo que represnta el atributo identificador de la clase Facultad y
     * define el nombre especifico de una facultad el cual es unico e
     * irrepetible
     */
    @Id
    @Basic(optional = false)
    @Column(name = "nom_facultad", nullable = false, length = 45)
    private String nomFacultad;

    /**
     * Atributo que define el número de telefono que tiene cada facultad
     */
    @Basic(optional = false)
    @Column(nullable = false)
    private int telefono;

    /**
     * Atributo que define el correo que tiene cada facultad
     */
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String correo;

    /**
     * Atributo que define el nombre del decano que tiene cada facultad
     */
    @Basic(optional = false)
    @Column(name = "nom_decano", nullable = false, length = 45)
    private String nomDecano;

    /**
     * Atributo que define la lista de programas que tiene cada facultad
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facultad")
    private List<Programa> programaList;

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
    public Facultad() {
    }

    /**
     * Constructor de la clase para instanciar un objeto estableciendo el campo
     * de clase nomFacultad
     *
     * @param nomFacultad El parámetro nomFacultad define el nombre especifico
     * de la facultad el cual es unico y no se puede repetir
     */
    public Facultad(String nomFacultad) {
        this.nomFacultad = nomFacultad;
    }

    /**
     * Constructor de la clase para instanciar un objeto estableciendo los
     * campos de clase nomFacultad, telefono, correo, nomDecano
     *
     * @param nomFacultad El parámetro nomFacultad define el nombre especifico
     * de la facultad el cual es unico y no se puede repetir
     * @param telefono El parámetro telefono define el número de telefono que
     * tiene cada facultad
     * @param correo El parámetro correo define el correo que tiene cada
     * facultad
     * @param nomDecano El parámetro nomDecano define el nombre del decano que
     * tiene cada facultad
     */
    public Facultad(String nomFacultad, int telefono, String correo, String nomDecano) {
        this.nomFacultad = nomFacultad;
        this.telefono = telefono;
        this.correo = correo;
        this.nomDecano = nomDecano;
    }

    /**
     * Constructor de la clase para instanciar un objeto estableciendo los
     * campos de clase nomFacultad, telefono, correo, nomDecano y
     *
     * @param nomFacultad El parámetro nomFacultad define el nombre especifico
     * de la facultad el cual es unico y no se puede repetir
     * @param telefono El parámetro telefono define el número de telefono que
     * tiene cada facultad
     * @param correo El parámetro correo define el correo que tiene cada
     * facultad
     * @param nomDecano El parámetro nomDecano define el nombre del decano que
     * tiene cada facultad
     * @param psicologo El parámetro psicologo define una instancia de tipo
     * Psicologo para obtener los métodos y atributos de la Clase Psicologo
     */
    public Facultad(String nomFacultad, int telefono, String correo, String nomDecano, Psicologo psicologo) {
        this.nomFacultad = nomFacultad;
        this.telefono = telefono;
        this.correo = correo;
        this.nomDecano = nomDecano;
        this.psicologo = psicologo;
    }

    /**
     * Método que devuelve el nombre de la facultad
     *
     * @return nomFacultad es el atributo que representa el nombre de cada una
     * de las facultades
     */
    public String getNomFacultad() {
        return nomFacultad;
    }

    /**
     * Método que se utiliza para establecer el atributo nomFacultad de una
     * instacia de clase
     *
     * @param nomFacultad El parámetro nomFacultad define el nombre especifico
     * de cada facultad el cual es unico y no se puede repetir
     */
    public void setNomFacultad(String nomFacultad) {
        this.nomFacultad = nomFacultad;
    }

    /**
     * Método que devuelve el número de telefono que tiene que facultad
     *
     * @return telefono que es atributo que representa el número de telefono que
     * posee cada facultad
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * Método que se utiliza para establecer el atributo telefono de una
     * instacia de clase
     *
     * @param telefono El parámetro telefono define el número de telefono que
     * tiene cada facultad
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     * Método que devuelve el correo que le pertence a cada facultad
     *
     * @return correo es el atributo que representa el correo electronico que
     * posee cada facultad
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Método que se utiliza para establecer el atributo correo de una instacia
     * de clase
     *
     * @param correo es el atributo que representa el correo electronico que
     * posee cada facultad
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Método que devuelve el nombre del decano, que es la persona encargada de
     * una facultad en especifico
     *
     * @return nomDecano, es el atributo que representa el nombre del decano de
     * cada facultad
     */
    public String getNomDecano() {
        return nomDecano;
    }

    /**
     * Método que se utiliza para establecer el atributo nomDecanode una
     * instacia de clase
     *
     * @param nomDecano El parámetro nomDecano define el nombre del decano que
     * tiene cada facultad
     */
    public void setNomDecano(String nomDecano) {
        this.nomDecano = nomDecano;
    }

    /**
     * Método que devuelve la lista de programas que posee cada facultad
     *
     * @return programaList, es la lista de programas la cual pertenece a una
     * facultad en especifico
     */
    @XmlTransient
    public List<Programa> getProgramaList() {
        return programaList;
    }

    /**
     * Método que se utiliza para establecer el atributo programaList, para eso
     * primero se obtiene un programa especifico de una facultad en concreto y
     * desspues se puede modificar el programa obtenido de la lista
     *
     * @param programaList El parámetro programaList define la lista de
     * programas que tiene cada facultad
     */
    public void setProgramaList(List<Programa> programaList) {
        this.programaList = programaList;
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
        hash += (nomFacultad != null ? nomFacultad.hashCode() : 0);
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
        if (!(object instanceof Facultad)) {
            return false;
        }
        Facultad other = (Facultad) object;
        if ((this.nomFacultad == null && other.nomFacultad != null) || (this.nomFacultad != null && !this.nomFacultad.equals(other.nomFacultad))) {
            return false;
        }
        return true;
    }

    /**
     * Método que devuelve una cadena de caracteres especificando el nombre de
     * la facultad
     *
     * @return devuelve un mensaje mostrando el nombre de una facultad en
     * especifico
     */
    @Override
    public String toString() {
        return "co.edu.usbbog.paoe.model.Facultad[ nomFacultad=" + nomFacultad + " ]";
    }

}
