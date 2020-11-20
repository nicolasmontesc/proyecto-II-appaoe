/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.paoe.controller.logic;

import co.edu.usbbog.paoe.security.Seguridad;
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
public class PsicologoBusinessTest {

    PsicologoBusiness ap;
    Seguridad seg;

    public PsicologoBusinessTest() {
        this.ap = new PsicologoBusiness();
        this.seg = new Seguridad();
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("--------------- Inicio Del Testeo ---------------");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("---------------- Fin Del Testeo ----------------");
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
    public void testFindPsicologo() {
        try {

            ap.createPsicologo(1023462885, "Maria Clara", "Rojas Rojas", 22, "mariaclararr22@usbbog.edu.co",
                    seg.encriptar("IngenierosSistemas", "MariaClara123"), "3764562");

            assertNotEquals(null, ap.findPsicologo(1023462885));
            System.out.println("Prueba del método findPsicologo realizada con éxito");
        } catch (Exception ex) {
            System.err.println("Error. " + ex.getMessage());
            fail("Ocurrio un error al buscar el registro en la tabla Psicólogo");
        }
    }

    @Test
    public void testCreatePsicologo() {
        String mensaje;
        try {
            mensaje = ap.createPsicologo(1023468818, "Juan Camilo", "Rodriguez Cortez", 32, "juancamilorc32@usbbog.edu.co",
                    seg.encriptar("IngenierosSistemas", "JuanCamilo123"), "4527891");
            assertEquals("Guardado correctamente.", mensaje);
            System.out.println("Prueba del método createPsicologo realizada con éxito");
        } catch (Exception ex) {
            System.err.println("Error. " + ex.getMessage());
            fail("Ocurrio un error al insertar el registro en la tabla Psicólogo");
        }
    }

    @Test
    public void testEditPsicologo() {
        String mensaje;
        try {
            mensaje = ap.editPsicologo(1023468818, "Juan Camilo", "Rodriguez Cortez", 38, "juancamilorc32@usbbog.edu.co",
                    seg.encriptar("IngenierosSistemas", "JuanCamilo123"), "4527844");
            assertEquals("Se ha editado correctamente", mensaje);
            System.out.println("Prueba del método editPsicologo realizada con éxito");
        } catch (Exception ex) {

        }
    }
}
