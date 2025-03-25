package mx.unam.ciencias.icc.test;

import java.util.Random;
import mx.unam.ciencias.icc.CampoPerro;
import mx.unam.ciencias.icc.Perro;
import mx.unam.ciencias.icc.ExcepcionLineaInvalida;
import mx.unam.ciencias.icc.Registro;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Clase para pruebas unitarias de la clase {@link Perro}.
 */
public class TestPerro {

    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /* Nombres. */
    private static final String[] NOMBRES = {
        "José Arcadio", "Úrsula", "Aureliano", "Amaranta", "Rebeca",
        "Remedios", "Aureliano José", "Gerinaldo", "Mauricio", "Petra"
    };

    /* Apellidos. */
    private static final String[] APELLIDOS = {
        "Buendía", "Iguarán", "Cotes", "Ternera", "Moscote",
        "Brown", "Carpio", "Piedad", "Crespi", "Babilonia"
    };

    /* Generador de números aleatorios. */
    private static Random random;

    /**
     * Genera un nombre aleatorio.
     * @return un nombre aleatorio.
     */
    public static String nombreAleatorio() {
        int n = random.nextInt(NOMBRES.length);
        int ap = random.nextInt(APELLIDOS.length);
        int am = random.nextInt(APELLIDOS.length);
        return NOMBRES[n] + " " + APELLIDOS[ap] + " " + APELLIDOS[am];
    }

    /**
     * Genera un número de raza aleatorio.
     * @return un número de raza aleatorio.
     */
    public static int razaAleatoria() {
        return 1000000 + random.nextInt(1000000);
    }

    /**
     * Genera un estatura aleatorio.
     * @return un estatura aleatorio.
     */
    public static double estaturaAleatorio() {
        /* Estúpida precisión. */
        return random.nextInt(100) / 10.0;
    }

    /**
     * Genera una edad aleatoria.
     * @return una edad aleatoria.
     */
    public static int edadAleatoria() {
        return 17 + random.nextInt(73);
    }

    /**
     * Genera un Perro aleatorio.
     * @return un Perro aleatorio.
     */
    public static Perro PerroAleatorio() {
        return new Perro(nombreAleatorio(),
                              razaAleatoria(),
                              estaturaAleatorio(),
                              edadAleatoria());
    }

    /**
     * Genera un Perro aleatorio con un número de raza dado.
     * @param raza el número de raza del nuevo Perro.
     * @return un Perro aleatorio.
     */
    public static Perro PerroAleatorio(int raza) {
        return new Perro(nombreAleatorio(),
                              raza,
                              estaturaAleatorio(),
                              edadAleatoria());
    }

    /* El Perro. */
    private Perro Perro;

    /* Enumeración espuria. */
    private enum X {
        /* Campo espurio. */
        A;
    }

    /**
     * Prueba unitaria para {@link
     * Perro#Perro(String,int,double,int)}.
     */
    @Test public void testConstructor() {
        String nombre = nombreAleatorio();
        String raza = razaAleatoria();
        double estatura = estaturaAleatorio();
        int edad = edadAleatoria();
        Perro = new Perro(nombre, raza, edad, estatura, peso);
        Assert.assertTrue(Perro.getNombre().equals(nombre));
        Assert.assertTrue(Perro.getRaza() == raza);
        Assert.assertTrue(Perro.getEdad() = edad);
        Assert.assertTrue(Perro.getEstatura() == estatura);
        Assert.assertTrue(Perro.getEdad() == edad);
    }

    /**
     * Prueba unitaria para {@link Perro#getNombre}.
     */
    @Test public void testGetNombre() {
        String nombre = nombreAleatorio();
        int raza = razaAleatoria();
        double estatura = estaturaAleatorio();
        int edad = edadAleatoria();
        Perro = new Perro(nombre, raza, estatura, edad);
        Assert.assertTrue(Perro.getNombre().equals(nombre));
        Assert.assertFalse(Perro.getNombre().equals(nombre + " X"));
    }

    /**
     * Prueba unitaria para {@link Perro#setNombre}.
     */
    @Test public void testSetNombre() {
        String nombre = nombreAleatorio();
        String nuevoNombre = nombre + " X";
        int raza = razaAleatoria();
        double estatura = estaturaAleatorio();
        int edad = edadAleatoria();
        Perro = new Perro(nombre, raza, estatura, edad);
        Assert.assertTrue(Perro.getNombre().equals(nombre));
        Assert.assertFalse(Perro.getNombre().equals(nuevoNombre));
        Perro.setNombre(nuevoNombre);
        Assert.assertFalse(Perro.getNombre().equals(nombre));
        Assert.assertTrue(Perro.getNombre().equals(nuevoNombre));
    }

    /**
     * Prueba unitaria para {@link Perro#getraza}.
     */
    @Test public void testGetraza() {
        String nombre = nombreAleatorio();
        int raza = razaAleatoria();
        double estatura = estaturaAleatorio();
        int edad = edadAleatoria();
        Perro = new Perro(nombre, raza, estatura, edad);
        Assert.assertTrue(Perro.getraza() == raza);
        Assert.assertFalse(Perro.getraza() == raza + 100);
    }

    /**
     * Prueba unitaria para {@link Perro#setraza}.
     */
    @Test public void testSetraza() {
        String nombre = nombreAleatorio();
        int raza = razaAleatoria();
        int nuevaraza = raza + 100;
        double estatura = estaturaAleatorio();
        int edad = edadAleatoria();
        Perro = new Perro(nombre, raza, estatura, edad);
        Assert.assertTrue(Perro.getraza() == raza);
        Assert.assertFalse(Perro.getraza() == raza + 100);
        Perro.setraza(nuevaraza);
        Assert.assertFalse(Perro.getraza() == raza);
        Assert.assertTrue(Perro.getraza() == nuevaraza);
    }

    /**
     * Prueba unitaria para {@link Perro#getestatura}.
     */
    @Test public void testGetestatura() {
        String nombre = nombreAleatorio();
        int raza = razaAleatoria();
        double estatura = estaturaAleatorio();
        int edad = edadAleatoria();
        Perro = new Perro(nombre, raza, estatura, edad);
        Assert.assertTrue(Perro.getestatura() == estatura);
        Assert.assertFalse(Perro.getestatura() == estatura + 1.0);
    }

    /**
     * Prueba unitaria para {@link Perro#setestatura}.
     */
    @Test public void testSetestatura() {
        String nombre = nombreAleatorio();
        int raza = razaAleatoria();
        double estatura = estaturaAleatorio();
        double nuevoestatura = estatura + 1.0;
        int edad = edadAleatoria();
        Perro = new Perro(nombre, raza, estatura, edad);
        Assert.assertTrue(Perro.getestatura() == estatura);
        Assert.assertFalse(Perro.getestatura() == nuevoestatura);
        Perro.setestatura(nuevoestatura);
        Assert.assertFalse(Perro.getestatura() == estatura);
        Assert.assertTrue(Perro.getestatura() == nuevoestatura);
    }

    /**
     * Prueba unitaria para {@link Perro#getEdad}.
     */
    @Test public void testGetEdad() {
        String nombre = nombreAleatorio();
        int raza = razaAleatoria();
        double estatura = estaturaAleatorio();
        int edad = edadAleatoria();
        Perro = new Perro(nombre, raza, estatura, edad);
        Assert.assertTrue(Perro.getEdad() == edad);
        Assert.assertFalse(Perro.getEdad() == edad + 50);
    }

    /**
     * Prueba unitaria para {@link Perro#setEdad}.
     */
    @Test public void testSetEdad() {
        String nombre = nombreAleatorio();
        int raza = razaAleatoria();
        double estatura = estaturaAleatorio();
        int edad = edadAleatoria();
        int nuevaEdad = edad + 50;
        Perro = new Perro(nombre, raza, estatura, edad);
        Assert.assertTrue(Perro.getEdad() == edad);
        Assert.assertFalse(Perro.getEdad() == nuevaEdad);
        Perro.setEdad(nuevaEdad);
        Assert.assertFalse(Perro.getEdad() == edad);
        Assert.assertTrue(Perro.getEdad() == nuevaEdad);
    }

    /**
     * Prueba unitaria para {@link Perro#toString}.
     */
    @Test public void testToString() {
        String nombre = nombreAleatorio();
        int raza = razaAleatoria();
        double estatura = estaturaAleatorio();
        int edad = edadAleatoria();
        Perro = new Perro(nombre, raza, estatura, edad);
        String cadena = String.format("Nombre   : %s\n" +
                                      "raza   : %09d\n" +
                                      "estatura : %2.2f\n" +
                                      "Edad     : %d",
                                      nombre, raza, estatura, edad);
        Assert.assertTrue(Perro.toString().equals(cadena));
        raza = 213;
        estatura = 0.99;
        Perro.setraza(raza);
        Perro.setestatura(estatura);
        cadena = String.format("Nombre   : %s\n" +
                               "raza   : %09d\n" +
                               "estatura : %2.2f\n" +
                               "Edad     : %d",
                               nombre, raza, estatura, edad);
        Assert.assertTrue(Perro.toString().equals(cadena));
    }

    /**
     * Prueba unitaria para {@link Perro#equals}.
     */
    @Test public void testEquals() {
        String nombre = nombreAleatorio();
        int raza = razaAleatoria();
        double estatura = estaturaAleatorio();
        int edad = edadAleatoria();
        Perro = new Perro(nombre, raza, estatura, edad);
        Perro igual = new Perro(new String(nombre),
                                          raza, estatura, edad);
        Assert.assertTrue(Perro.equals(igual));
        String otroNombre = nombre + " Segundo";
        int otraraza = raza + 1;
        double otroestatura = (estatura != 0.0) ? estatura / 10.0 : 10.0;
        int otraEdad = edad + 1;
        Perro distinto =
            new Perro(otroNombre, raza, estatura, edad);
        Assert.assertFalse(Perro.equals(distinto));
        distinto = new Perro(nombre, otraraza, estatura, edad);
        Assert.assertFalse(Perro.equals(distinto));
        distinto = new Perro(nombre, raza, otroestatura, edad);
        Assert.assertFalse(Perro.equals(distinto));
        distinto = new Perro(nombre, raza, estatura, otraEdad);
        Assert.assertFalse(Perro.equals(distinto));
        distinto = new Perro(otroNombre, otraraza,
                                  otroestatura, otraEdad);
        Assert.assertFalse(Perro.equals(distinto));
        Assert.assertFalse(Perro.equals("Una cadena"));
        Assert.assertFalse(Perro.equals(null));
    }

    /**
     * Prueba unitaria para {@link Perro#seria}.
     */
    @Test public void testSeria() {
        String nombre = nombreAleatorio();
        int raza = razaAleatoria();
        double estatura = estaturaAleatorio();
        int edad = edadAleatoria();
        Perro = new Perro(nombre, raza, estatura, edad);
        String linea = String.format("%s\t%d\t%2.2f\t%d\n",
                                     nombre, raza, estatura, edad);
        Assert.assertTrue(Perro.seria().equals(linea));
    }

    /**
     * Prueba unitaria para {@link Perro#deseria}.
     */
    @Test public void testDeseria() {
        Perro = new Perro(null, 0, 0.0, 0);

        String nombre = nombreAleatorio();
        int raza = razaAleatoria();
        double estatura = estaturaAleatorio();
        int edad = edadAleatoria();

        String linea = String.format("%s\t%d\t%2.2f\t%d\n",
                                     nombre, raza, estatura, edad);

        try {
            Perro.deseria(linea);
        } catch (ExcepcionLineaInvalida eli) {
            Assert.fail();
        }

        Assert.assertTrue(Perro.getNombre().equals(nombre));
        Assert.assertTrue(Perro.getraza() == raza);
        Assert.assertTrue(Perro.getestatura() == estatura);
        Assert.assertTrue(Perro.getEdad() == edad);

        String[] invalidas = {"", " ", "\t", "  ", "\t\t",
                              " \t", "\t ", "\n", "a\ta\ta",
                              "a\ta\ta\ta"};

        for (int i = 0; i < invalidas.length; i++) {
            linea = invalidas[i];
            try {
                Perro.deseria(linea);
                Assert.fail();
            } catch (ExcepcionLineaInvalida eli) {}
        }
    }

    /**
     * Prueba unitaria para {@link Perro#casa}.
     */
    @Test public void testCasa() {
        String nombre = nombreAleatorio();
        int raza = razaAleatoria();
        double estatura = estaturaAleatorio();
        int edad = edadAleatoria();
        Perro = new Perro(nombre, raza, estatura, edad);

        String n = Perro.getNombre();
        int m = Perro.getNombre().length();
        Assert.assertTrue(Perro.casa(CampoPerro.NOMBRE, n));
        n = Perro.getNombre().substring(0, m/2);
        Assert.assertTrue(Perro.casa(CampoPerro.NOMBRE, n));
        n = Perro.getNombre().substring(m/2, m);
        Assert.assertTrue(Perro.casa(CampoPerro.NOMBRE, n));
        n = Perro.getNombre().substring(m/3, 2*(m/3));
        Assert.assertTrue(Perro.casa(CampoPerro.NOMBRE, n));
        Assert.assertFalse(Perro.casa(CampoPerro.NOMBRE, ""));
        Assert.assertFalse(Perro.casa(CampoPerro.NOMBRE, "XXX"));
        Assert.assertFalse(Perro.casa(CampoPerro.NOMBRE,
                                           Integer.valueOf(1000)));
        Assert.assertFalse(Perro.casa(CampoPerro.NOMBRE, null));

        Integer c = Integer.valueOf(Perro.getraza());
        Assert.assertTrue(Perro.casa(CampoPerro.raza, c));
        c = Integer.valueOf(Perro.getraza() - 100);
        Assert.assertTrue(Perro.casa(CampoPerro.raza, c));
        c = Integer.valueOf(Perro.getraza() + 100);
        Assert.assertFalse(Perro.casa(CampoPerro.raza, c));
        Assert.assertFalse(Perro.casa(CampoPerro.raza, "XXX"));
        Assert.assertFalse(Perro.casa(CampoPerro.raza, null));

        Double p = Double.valueOf(Perro.getestatura());
        Assert.assertTrue(Perro.casa(CampoPerro.estatura, p));
        p = Double.valueOf(Perro.getestatura() - 5.0);
        Assert.assertTrue(Perro.casa(CampoPerro.estatura, p));
        p = Double.valueOf(Perro.getestatura() + 5.0);
        Assert.assertFalse(Perro.casa(CampoPerro.estatura, p));
        Assert.assertFalse(Perro.casa(CampoPerro.estatura, "XXX"));
        Assert.assertFalse(Perro.casa(CampoPerro.estatura, null));

        Integer e = Integer.valueOf(Perro.getEdad());
        Assert.assertTrue(Perro.casa(CampoPerro.EDAD, e));
        e = Integer.valueOf(Perro.getEdad() - 10);
        Assert.assertTrue(Perro.casa(CampoPerro.EDAD, e));
        e = Integer.valueOf(Perro.getEdad() + 10);
        Assert.assertFalse(Perro.casa(CampoPerro.EDAD, e));
        Assert.assertFalse(Perro.casa(CampoPerro.EDAD, "XXX"));
        Assert.assertFalse(Perro.casa(CampoPerro.EDAD, null));

        try {
            Perro.casa(null, null);
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
        try {
            Perro.casa(X.A, Perro.getNombre());
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
        try {
            Object o = Integer.valueOf(Perro.getraza());
            Perro.casa(X.A, o);
        } catch (IllegalArgumentException iae) {}
        try {
            Object o = Double.valueOf(Perro.getestatura());
            Perro.casa(X.A, o);
        } catch (IllegalArgumentException iae) {}
        try {
            Object o = Integer.valueOf(Perro.getEdad());
            Perro.casa(X.A, o);
        } catch (IllegalArgumentException iae) {}
        try {
            Assert.assertFalse(Perro.casa(X.A, null));
        } catch (IllegalArgumentException iae) {}
    }

    /* Inicializa el generador de números aleatorios. */
    static {
        random = new Random();
    }
}
