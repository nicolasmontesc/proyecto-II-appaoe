/**
 * Paquete que contiene la logica de negocio de la aplicación
 */
package co.edu.usbbog.paoe.controller.logic;

/**
 * Estas son los import necesarios para clase AsesoriaBusiness para su correcto
 * funcionamiento
 */
import co.edu.usbbog.paoe.controller.jpa.PsicologoJpaController;
import co.edu.usbbog.paoe.model.Psicologo;

/**
 * @author Juan Nicolas, Juan Sebastían, Santiago Echeverri
 * @version 15/09/2020/1.0
 * @serial JPA
 * @since 15/09/2020
 */
/**
 * Esta clase contiene los metodos para crear, editar, buscar y listar los
 * objetos de tipo Psicologo
 */
public class PsicologoBusiness {

    /**
     * Campos o atributos de la clase
     */
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
     * PsicologoJpaController, para hacer uso de los metodos de cada clase
     */
    public PsicologoBusiness() {
        this.psicologoJpaController = new PsicologoJpaController();
    }

    /**
     * Método que devuleve un mensaje confirmando si se pudo guardar y crear
     * correctamente un Psicologo con sus respectivos atributos o por el
     * contrario se presento un error y no fue posible guardar la informacion
     * correctamente. Para eso necesitamos los siguintes parámetros
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
     * @return un mensaje informando si los datos de un Psicologo se guardaron
     * correctamente en la base de datos o si se presento un error y no fue
     * posible guardar la información en la base de datos
     */
    public String createPsicologo(Integer cedula, String nombres, String apellidos, int edad, String correo, String contrasena,
            String telefono) {
        try {
            Psicologo psicologo = new Psicologo(cedula, nombres, apellidos, edad, correo, contrasena, telefono);
            psicologoJpaController.create(psicologo);
            mensaje = "Guardado correctamente.";
        } catch (Exception e) {
            mensaje = "No se pudo guardar la información. \n" + e.getMessage();
        }
        return mensaje;
    }

    /**
     * Método que devuleve un mensaje confirmando si se pudo editar
     * correctamente los datos de un Psicologo o por el contrario se presento un
     * error y no fue posible editar la informacion correctamente. Para eso
     * necesitamos los siguintes parámetros
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
     * @return un mensaje informando si los datos de un psicolog se editaron
     * correctamente en la base de datos o si se presento un error y no fue
     * posible editar la información en la base de datos
     */
    public String editPsicologo(Integer cedula, String nombres, String apellidos, int edad, String correo, String contrasena,
            String telefono) {

        try {
            Psicologo psicologo = psicologoJpaController.findPsicologo(cedula);
            psicologo.setEdad(edad);
            psicologo.setCorreo(correo);
            psicologo.setTelefono(telefono);
            psicologoJpaController.edit(psicologo);
            mensaje = "Se ha editado correctamente";
        } catch (Exception e) {
            mensaje = "No se pudo editar la información. \n" + e.getMessage();
        }

        return mensaje;
    }

    /**
     * Método que devuleve un mensaje confirmando si se pudo encontrar el
     * registro de un Psicologo en especifico correctamente en la base de datos,
     * mediante su atributo identificador cedula o por el contrario no fue
     * posible encontrar el registro de ese Psicologo por diferentes razones
     *
     * @param cedula El parámetro cedula define el número de cedula que
     * identifica a un psicologo en especifico
     * @return un mensaje informando si los datos de un psicologo fueron
     * encontrados correctamente en la base de datos o si se presento un error y
     * no fue posible encontrar la información de ese psicolofo en especifico en
     * la base de datos
     */
    public Psicologo findPsicologo(Integer cedula) {
        Psicologo p = null;
        try {
            p = psicologoJpaController.findPsicologo(cedula);
            System.out.println("Se ha encontrado correctamente la información");
        } catch (Exception e) {
            System.out.println("No se pudo encontrar la información. \n" + e.getMessage());
        }
        return p;
    }

    /**
     * Método que devuleve un mensaje confirmando si fue posible encontrar todos
     * los registros de los psicologos registrados en la base de datos o por el
     * contrario no se pudieron encontrar todos los registros de los psicolgos
     * por diferentes razones
     *
     * @return un mensaje informando si se pudo encontrar la lista de los
     * psicologos registradas en la base de datos o si se presento un error y no
     * fue posible encontrar la lsita de los psicologos registradas en la base
     * de datos
     */
    public String findPacienteEntities() {
        try {
            psicologoJpaController.findPsicologoEntities();
            return "Se ha encontrado la lista de psicologos";
        } catch (Exception ex) {
            System.err.println("error: " + ex.getMessage());
            return "No se ha encontrado la lista de psicologos \n" + ex.getMessage();
        }
    }

//    /**
//     *
//     * @param args
//     */
//    public static void main(String[] args) {
//
//        PsicologoBusiness psicologoB = new PsicologoBusiness();
//
//        System.out.println(psicologoB.createPsicologo(76538921, "Angela", "Martinez", 35, "angelamar@gmail.com", "MiMamaMeMima", "54673892"));
//        System.out.println(psicologoB.editPsicologo(76538921, "Angela", "Martinez", 35, "angemartinez062@gmail.com", "MiMamaMeMima", "65483726"));
//    }
}
