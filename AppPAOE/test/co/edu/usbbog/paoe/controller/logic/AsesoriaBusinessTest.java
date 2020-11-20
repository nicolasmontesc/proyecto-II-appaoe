/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.paoe.controller.logic;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juan Montes, Juan Rodriguez y Compañia
 */
public class AsesoriaBusinessTest {

    private final AsesoriaBusiness asesoriaBusiness;
    private final PsicologoBusiness psicologoBusiness;
    private final PacienteBusiness pacienteBusiness;

    public AsesoriaBusinessTest() {
        this.asesoriaBusiness = new AsesoriaBusiness();
        this.pacienteBusiness = new PacienteBusiness();
        this.psicologoBusiness = new PsicologoBusiness();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testCreateAsesoria() {
        Date fecha = null;
        String mensaje;
        try {
            Date now = new Date(System.currentTimeMillis());
            SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
            SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss");
            String cadena = date.format(now) + " " + hour.format(now);
            fecha = date.parse(cadena);

            mensaje = asesoriaBusiness.createAsesoria(11, fecha, "individual", "Voluntad propia", "Docente",
                    "Malas notas", "Compromiso de mejora", "Asistió", "En proceso",
                    "Na", "Problemas familiares", 12345642, 1023462315);

            assertEquals("Guardado Correctamente", mensaje);
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
            fail("Ocurrio un error al insertar el registro en la tabla Paciente");
        }
    }
}
