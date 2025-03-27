package mx.unam.ciencias.icc.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Random;
import mx.unam.ciencias.icc.CampoPerro;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Clase para pruebas unitarias de la enumeración {@link CampoPerro}.
 */
public class TestCampoPerro {

    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /**
     * Prueba unitaria para {@link CampoPerro#toString}.
     */
    @Test public void testToString() {
        String s = CampoPerro.NOMBRE.toString();
        Assert.assertTrue(s.equals("Nombre"));

        s = CampoPerro.RAZA.toString();
        Assert.assertTrue(s.equals("Raza"));

        s = CampoPerro.EDAD.toString();
        Assert.assertTrue(s.equals("Edad"));

        s = CampoPerro.ESTATURA.toString();
        Assert.assertTrue(s.equals("Estatura"));

        s = CampoPerro.PESO.toString();
        Assert.assertTrue(s.equals("Peso"));
    }
}
