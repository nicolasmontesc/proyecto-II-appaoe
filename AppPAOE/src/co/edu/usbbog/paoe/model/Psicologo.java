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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Juan Nicolas, Juan Sebastían, Santiago Echeverri
 * @version 15/09/2020/1.0
 * @serial JPA
 * @since 15/09/2020
 */
/**
 * Etiqueta que indica que esta clase representa la entidad Psicologo en el
 * modelo de datos
 */
@Entity
/**
 * Etiqueta que hace eferenica a la base de datos a la cual pertenece la clase e
 * indica que el atributo correo debe ser unico
 */
@Table(catalog = "paoe_db", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"correo"})})
@XmlRootElement
/**
 *
 */
@NamedQueries({
    @NamedQuery(name = "Psicologo.findAll", query = "SELECT p FROM Psicologo p")
    , @NamedQuery(name = "Psicologo.findByCedula", query = "SELECT p FROM Psicologo p WHERE p.cedula = :cedula")
    , @NamedQuery(name = "Psicologo.findByNombres", query = "SELECT p FROM Psicologo p WHERE p.nombres = :nombres")
    , @NamedQuery(name = "Psicologo.findByApellidos", query = "SELECT p FROM Psicologo p WHERE p.apellidos = :apellidos")
    , @NamedQuery(name = "Psicologo.findByEdad", query = "SELECT p FROM Psicologo p WHERE p.edad = :edad")
    , @NamedQuery(name = "Psicologo.findByCorreo", query = "SELECT p FROM Psicologo p WHERE p.correo = :correo")
    , @NamedQuery(name = "Psicologo.findByContrasena", query = "SELECT p FROM Psicologo p WHERE p.contrasena = :contrasena")
    , @NamedQuery(name = "Psicologo.findByTelefono", query = "SELECT p FROM Psicologo p WHERE p.telefono = :telefono")})

/**
 * Esta clase define los atributos necesarios, con sus respectivos métodos;
 * getters/settes, hashcode, equals y toString
 */
public class Psicologo implements Serializable {

    /**
     * Campos o atributos de clase
     */
    /**
     * Declaración e inicialización de la constante serialVersionUID, para
     * asignarle un numero que identifique a la clase Psicologo
     */
    private static final long serialVersionUID = 1L;

    /**
     * Atributo que represnta el atributo identificador de la clase Psicologo y
     * representa el número de cedula que reconoce a un psicologo en especifico
     */
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer cedula;

    /**
     * Atributo que define los nombres de un psicologo
     */
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String nombres;

    /**
     * Atributo que define los apellidos de un psicologo
     */
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String apellidos;

    /**
     * Atributo que define los años que tiene un psicologo
     */
    @Basic(optional = false)
    @Column(nullable = false)
    private int edad;

    /**
     * Atributo que define el correo electronico de un psicologo con el cual
     * tiene la cuenta registrada en el sistema
     */
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String correo;

    /**
     * Atributo que define la contraseña de un psicologo para poder ingresar al
     * sistema
     */
    @Basic(optional = false)
    @Column(nullable = false, length = 128)
    private String contrasena;

    /**
     * Atributo que define el número de telefono de un psicologo para ponerse en
     * contacto con el o ella
     */
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String telefono;

    /**
     * Atributo que define la lista de asesorias que atendido un psicologo en
     * especifico
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "psicologo")
    private List<Asesoria> asesoriaList;

    /**
     * Atributo que define el nombre de las facultades que un psicologo esta
     * encargado de apoyar
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "psicologo")
    private List<Facultad> facultadList;

    /**
     * Constructor vacio de la clase para instanciar un onjeto sin establecer
     * los campos de la clase.
     */
    public Psicologo() {
    }

    /**
     * Constructor de la clase para instanciar un objeto estableciendo el campo
     * de clase cedula
     *
     * @param cedula El parámetro cedula define el número de cedula el cual
     * identica a un psicologo registrado en el sistema
     */
    public Psicologo(Integer cedula) {
        this.cedula = cedula;
    }

    /**
     * Constructor de la clase para instanciar un objeto estableciendo los
     * campos de clase cedula, nombres, apellidos, edad, correo, contrasena,
     * telefono
     *
     * @param cedula El parámetro cedula define el número de cedula que
     * identifica a un psicologo en especifico
     * @param nombres El parámetro nombres define los nombres de un psicologo en
     * especifico
     * @param apellidos El parámetro apellidos define los apelledidos de un
     * psicologo en especifico
     * @param edad El parámetro edad define los años que tiene un psicologo en
     * especifico
     * @param correo El parámetro correo define el correo electronico de un
     * psicologo con el cual tiene la cuenta registrada en el sistema
     * @param contrasena El parámetro contrasena define la contraseña de un
     * psicologo para poder ingresar o acceder al sistema
     * @param telefono El parámetro telefono define el número de telefono de un
     * psicologo para ponerse en contacto
     */
    public Psicologo(Integer cedula, String nombres, String apellidos, int edad, String correo, String contrasena, String telefono) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.correo = correo;
        this.contrasena = contrasena;
        this.telefono = telefono;
    }

    /**
     * Método que devuelve el numero de cedula de un psicologo en especifico
     *
     * @return cedula, es el atributo que representa el numero de cedula del
     * psicologo
     */
    public Integer getCedula() {
        return cedula;
    }

    /**
     * Método que se utiliza para establecer el atributo cedula de una instacio
     * de clase
     *
     * @param cedula el número de cedula que identifica a un psicologo en
     * especifico
     */
    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    /**
     * Método que devuelve los nombres de un psicologo en especifico
     *
     * @return nombres, es el atributo que representa los nombres de un
     * psicologo
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Método que se utiliza para establecer el atributo nombres de una
     * instancia de clase
     *
     * @param nombres El parámetro nombres define los nombres de un psicologo en
     * especifico
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Método que devuelve los apellidos de un psicologo en especifico
     *
     * @return apellidos, es el atributo que representa los apellidos de un
     * psicologo
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Método que se utiliza para establecer el atributo apellidos de una
     * instacia de clase
     *
     * @param apellidos El parámetro apellidos define los apelledidos de un
     * psicologo en especifico
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Método que devuelve los años que tiene un psicologo en especifico
     *
     * @return edad es el atributo que representa los años que tiene un
     * psicologo en especifico
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Método que se utiliza para establecer el atributo edad de una instacia de
     * clase
     *
     * @param edad El parámetro edad define los años que tiene un psicologo en
     * especifico
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Método que devuelve el correo electronico de un psicologo, con el cual
     * tiene la cuenta registrada en el sistema
     *
     * @return correo, es el atributo que representa el correo electronico de un
     * psicologo en especifico
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Método que se utiliza para establecer el atributo correo de una instacia
     * de clase
     *
     * @param correo El parámetro correo define el correo electronico de un
     * psicologo con el cual tiene la cuenta registrada en el sistema
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Método que devuelve la contraseña de un psicologo en especifico con la
     * cual puede ingresar o acceder al sistema
     *
     * @return contrasena, es el atributo que representa la contraseña de un
     * psicologo en especifico
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Método que se utiliza para establecer el atributo contrasena de una
     * instacia de clase
     *
     * @param contrasena El parámetro contrasena define la contraseña de un
     * psicologo la cual itiliza para ingresar o acceder al sistema
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Método que devuelve el número de telefono de un psicologo para ponerse en
     * contacto
     *
     * @return telefono, es el atributo que representa el número de telefono de
     * un psicologo en especifico
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Método que se utiliza para establecer el atributo telefono de una
     * instacia de clase
     *
     * @param telefono El parámetro telefono define el número de telefono de un
     * psicologo para ponerse en contacto
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Método que devuelve la lista de asesorias que hs atendido un psicologo en
     * especifico
     *
     * @return asesoriaList, es el atributo que repsenta la lista de asesoria
     * que atendido un psicologo
     */
    @XmlTransient
    public List<Asesoria> getAsesoriaList() {
        return asesoriaList;
    }

    /**
     * Método que se utiliza para establecer el atributo asesoriaList de una
     * instacia de clase
     *
     * @param asesoriaList El parámetro asesoriaList define la lista de
     * asesorias que ha atendido un psicologo en especifico
     */
    public void setAsesoriaList(List<Asesoria> asesoriaList) {
        this.asesoriaList = asesoriaList;
    }

    /**
     * Método que devuelve el nombre de las facultades que el psicologo esta
     * encargado de apoyar
     *
     * @return facultadList, es el atributo que representa el nombre de la
     * facultades de las cuales el psicologo esta encargado
     */
    @XmlTransient
    public List<Facultad> getFacultadList() {
        return facultadList;
    }

    /**
     * Método que se utiliza para establecer el atributo facultadList de una
     * instacia de clase
     *
     * @param facultadList El parámetro facultadList define el nombre de la
     * facultades de las cuales el psicologo esta encargado de apoyar
     */
    public void setFacultadList(List<Facultad> facultadList) {
        this.facultadList = facultadList;
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
        hash += (cedula != null ? cedula.hashCode() : 0);
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
        if (!(object instanceof Psicologo)) {
            return false;
        }
        Psicologo other = (Psicologo) object;
        if ((this.cedula == null && other.cedula != null) || (this.cedula != null && !this.cedula.equals(other.cedula))) {
            return false;
        }
        return true;
    }

    /**
     * Método que devuelve una cadena de caracteres especificando el No. de
     * cedula de un psicologo el cual lo identifica
     *
     * @return devuelve un mensaje mostranod el No. de cedula de un psicologo en
     * especifico
     */
    @Override
    public String toString() {
        return "co.edu.usbbog.paoe.model.Psicologo[ cedula=" + cedula + " ]";
    }

}
