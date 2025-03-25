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
        s = CampoPerro.CUENTA.toString();
        Assert.assertTrue(s.equals("# Cuenta"));
        s = CampoPerro.PROMEDIO.toString();
        Assert.assertTrue(s.equals("Promedio"));
        s = CampoPerro.EDAD.toString();
        Assert.assertTrue(s.equals("Edad"));
    }
}
