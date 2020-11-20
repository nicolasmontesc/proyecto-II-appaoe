/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.paoe.controller.logic;

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
public class PacienteBusinessTest {

    private final PacienteBusiness pacienteBusiness;

    public PacienteBusinessTest() {
        this.pacienteBusiness = new PacienteBusiness();
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

    @Test
    public void testCreatePacienteEstudiante() {
        String mensaje;
        try {
            mensaje = pacienteBusiness.createPaciente(12345882, "Estudiante", "Sergio", "Lopez", 6543568,
                    "Ubate", 21, "Masculino", "Icetex", "30000076548", "Ovtavo");
            String expResult = "Se ha guardado al paciente";
            assertEquals(expResult, mensaje);
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            fail("Ocurrio un error al insertar el registro en la tabla Paciente");
        }
    }

    @Test
    public void testCreatePaciente() {
        String mensaje;
        try {
            mensaje = pacienteBusiness.createPaciente(654388853, "Profesor", "Ernesto", "Escamilla", 7643827,
                    "Usquen", 37, "Masculino", null, null, null);
            String expResult = "Se ha guardado al paciente";
            assertEquals(expResult, mensaje);
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            fail("Ocurrio un error al insertar el registro en la tabla Paciente");
        }
    }

    @Test
    public void testFindPaciente() {

        try {
            pacienteBusiness.createPaciente(97453887, "Estudiante", "Laura", "Niño", 6543567,
                    "Ubate", 21, "Femenino", "De contado", "30000045654", "Sexto");
            assertNotEquals(null, pacienteBusiness.findPaciente(97453887));

        } catch (Exception e) {
        }
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
