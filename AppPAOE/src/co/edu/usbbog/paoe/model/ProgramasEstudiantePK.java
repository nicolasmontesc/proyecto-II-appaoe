/**
 * Paquete que se encargar de albergar las entidades del modelo de datos
 */
package co.edu.usbbog.paoe.model;

/**
 * Estos son los import necesarios para utilizar el api de Java JPA y para el
 * correcto manejo de los atributos
 */
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Juan Nicolas, Juan Sebastían, Santiago Echeverri
 * @version 15/09/2020/1.0
 * @serial JPA
 * @since 15/09/2020
 */
@Embeddable

/**
 * Esta clase define los atributos necesarios, con sus respectivos métodos;
 * getters/settes, hashcode, equals y toString
 */
public class ProgramasEstudiantePK implements Serializable {

    /**
     * Campos o atributos de la clase
     */
    /**
     * Atributo que define el número de documento de un paciente en especifico
     * que en este caso es un estudiante
     */
    @Basic(optional = false)
    @Column(nullable = false)
    private int paciente;

    /**
     * Atributo que define el nombre del programa que esta cursando un
     * estudiante
     */
    @Basic(optional = false)
    @Column(nullable = false, length = 45)
    private String programa;

    /**
     * Constructor vacio de la clase para instanciar un onjeto sin establecer
     * los campos de la clase.
     */
    public ProgramasEstudiantePK() {
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
    public ProgramasEstudiantePK(int paciente, String programa) {
        this.paciente = paciente;
        this.programa = programa;
    }

    /**
     * Método que devuelve el atributo identificador de un objeto paciente que
     * es el número de ducomento
     *
     * @return paciente, es el atributo que representa el numero de cumento de
     * un paciente
     */
    public int getPaciente() {
        return paciente;
    }

    /**
     * Método que se utiliza para modificar el atributo paciente de una instacia
     * de clase
     *
     * @param paciente El parámetro paciente define el número de documento que
     * identifica a un paciente en especifico
     */
    public void setPaciente(int paciente) {
        this.paciente = paciente;
    }

    /**
     * Método que devuelve el nombre especifico de un programa el cual es unico
     * e irrepetible
     *
     * @return programa, es el atributo que representa el nombre de un programa
     * en especifico
     */
    public String getPrograma() {
        return programa;
    }

    /**
     * Método que se utiliza para modificar el atributo programa de una instacia
     * de clase
     *
     * @param programa El parámetro programa define el nombre especifico de un
     * programa el cual es unico e irrepetible
     */
    public void setPrograma(String programa) {
        this.programa = programa;
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
        hash += (int) paciente;
        hash += (programa != null ? programa.hashCode() : 0);
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
        if (!(object instanceof ProgramasEstudiantePK)) {
            return false;
        }
        ProgramasEstudiantePK other = (ProgramasEstudiantePK) object;
        if (this.paciente != other.paciente) {
            return false;
        }
        if ((this.programa == null && other.programa != null) || (this.programa != null && !this.programa.equals(other.programa))) {
            return false;
        }
        return true;
    }

    /**
     * Método que devuelve una cadena de caracteres especificando el número de
     * documento del paciente y el nombre del programa
     *
     * @return devuelve un mensaje mostrando el número de documento del paciente
     * y el nombre del programa
     */
    @Override
    public String toString() {
        return "co.edu.usbbog.paoe.model.ProgramasEstudiantePK[ paciente=" + paciente + ", programa=" + programa + " ]";
    }

}
