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
        "Mike", "Ron", "Han", "Wilson", "Kyara",
        "Blacky", "Bobby", "Lobo", "Ringo", "Ticky"
    };

    /* Apellidos. */
    private static final String[] RAZAS = {
        "Labrador", "Jack Russell Terrier", "Schnauzer", "Beagle", "Poodle",
        "Border Collie", "Mestizo", "Alaska", "Salchicha", "Doberman"
    };

    /* Generador de números aleatorios. */
    private static Random random;

    /**
     * Genera un nombre aleatorio.
     * @return un nombre aleatorio.
     */
    public static String nombreAleatorio() {

        int n = random.nextInt(NOMBRES.length);        
        return NOMBRES[n];
    }

    /**
     * Genera una raza aleatoria.
     * @return una raza aleatoria.
     */
    public static String razaAleatoria() {

        int n = random.nextInt(RAZAS.length);        
        return RAZAS[n];
    }

    /**
     * Genera una edad aleatoria.
     * @return una edad aleatoria.
     */
    public static int edadAleatoria() {
        return 1 + random.nextInt(13);
    }

    

    /**
     * Genera un estatura aleatorio.
     * @return un estatura aleatorio.
     */
    public static double estaturaAleatoria() {
        /* Estúpida precisión. */
        return 20 + random.nextDouble() * 45; // 20 + [0, 45) → [20, 65)
        
    }

    /**
     * Genera un estatura aleatorio.
     * @return un estatura aleatorio.
     */
    public static double pesoAleatorio() {
        /* Estúpida precisión. */
        return 3 + random.nextDouble() * 30; // 20 + [0, 45) → [20, 65)
        
    }

    

    /**
     * Genera un Perro aleatorio.
     * @return un Perro aleatorio.
     */
    public static Perro PerroAleatorio() {
        return new Perro(nombreAleatorio(),
                              razaAleatoria(),
                              edadAleatoria(),
                              estaturaAleatoria(),
                              pesoAleatorio());
    }

    /**
     * Genera un Perro aleatorio con una edad dada.
     * @param edad la edad del nuevo Perro.
     * @return un Perro aleatorio.
     */
    public static Perro PerroAleatorio(int edad) {
        return new Perro(nombreAleatorio(), razaAleatoria(), edad, estaturaAleatoria(), pesoAleatorio());
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
        int edad = edadAleatoria();
        double estatura = estaturaAleatoria();
        double peso = pesoAleatorio();
        Perro = new Perro(nombre, raza, edad, estatura, peso);
        Assert.assertTrue(Perro.getNombre().equals(nombre));
        Assert.assertTrue(Perro.getRaza() == raza);
        Assert.assertTrue(Perro.getEdad() == edad);
        Assert.assertTrue(Perro.getEstatura() == estatura);
        Assert.assertTrue(Perro.getPeso() == peso);
    }

    /**
     * Prueba unitaria para {@link Perro#getNombre}.
     */
    @Test public void testGetNombre() {
        String nombre = nombreAleatorio();
        String raza = razaAleatoria();
        int edad = edadAleatoria();
        double estatura = estaturaAleatoria();
        double peso = pesoAleatorio();
        Perro = new Perro(nombre, raza, edad, estatura, peso);
        Assert.assertTrue(Perro.getNombre().equals(nombre));
        Assert.assertFalse(Perro.getNombre().equals(nombre + " X"));
    }

    /**
     * Prueba unitaria para {@link Perro#setNombre}.
     */
    @Test public void testSetNombre() {
        String nombre = nombreAleatorio();
        String nuevoNombre = nombre + " X";
        String raza = razaAleatoria();
        int edad = edadAleatoria();
        double estatura = estaturaAleatoria();
        double peso = pesoAleatorio();
        Perro = new Perro(nombre, raza, edad, estatura, peso);
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
        String raza = razaAleatoria();
        int edad = edadAleatoria();
        double estatura = estaturaAleatoria();
        double peso = pesoAleatorio();
        Perro = new Perro(nombre, raza, edad, estatura, peso);
        Assert.assertTrue(Perro.getRaza().equals(raza));
        Assert.assertFalse(Perro.getRaza().equals(raza + " X"));
    }

    /**
     * Prueba unitaria para {@link Perro#setraza}.
     */
    @Test public void testSetraza() {
        String nombre = nombreAleatorio();        
        String raza = razaAleatoria();
        String nuevaRaza = raza + " X";
        int edad = edadAleatoria();
        double estatura = estaturaAleatoria();
        double peso = pesoAleatorio();
        Perro = new Perro(nombre, raza, edad, estatura, peso);
        Assert.assertTrue(Perro.getRaza().equals(raza));
        Assert.assertFalse(Perro.getRaza().equals(nuevaRaza));
        Perro.setRaza(nuevaRaza);
        Assert.assertFalse(Perro.getRaza().equals(raza));
        Assert.assertTrue(Perro.getRaza().equals(nuevaRaza));
    }

    /**
     * Prueba unitaria para {@link Perro#getEdad}.
     */
    @Test public void testGetEdad() {
        String nombre = nombreAleatorio();
        String raza = razaAleatoria();
        int edad = edadAleatoria();
        double estatura = estaturaAleatoria();
        double peso = pesoAleatorio();
        Perro = new Perro(nombre, raza, edad, estatura, peso);
        Assert.assertTrue(Perro.getEdad() == edad);
        Assert.assertFalse(Perro.getEdad() == edad + 50);
    }

    /**
     * Prueba unitaria para {@link Perro#setEdad}.
     */
    @Test public void testSetEdad() {
        String nombre = nombreAleatorio();
        String raza = razaAleatoria();
        int edad = edadAleatoria();
        int nuevaEdad = edad + 50;
        double estatura = estaturaAleatoria();
        double peso = pesoAleatorio();
        Perro = new Perro(nombre, raza, edad, estatura, peso);        
        Assert.assertTrue(Perro.getEdad() == edad);
        Assert.assertFalse(Perro.getEdad() == nuevaEdad);
        Perro.setEdad(nuevaEdad);
        Assert.assertFalse(Perro.getEdad() == edad);
        Assert.assertTrue(Perro.getEdad() == nuevaEdad);
    }


    /**
     * Prueba unitaria para {@link Perro#getestatura}.
     */
    @Test public void testGetestatura() {
        String nombre = nombreAleatorio();
        String raza = razaAleatoria();
        int edad = edadAleatoria();
        double estatura = estaturaAleatoria();
        double peso = pesoAleatorio();
        Perro = new Perro(nombre, raza, edad, estatura, peso);
        Assert.assertTrue(Perro.getEstatura() == estatura);
        Assert.assertFalse(Perro.getEstatura() == estatura + 1.0);
    }

    /**
     * Prueba unitaria para {@link Perro#setestatura}.
     */
    @Test public void testSetestatura() {
        String nombre = nombreAleatorio();
        String raza = razaAleatoria();
        int edad = edadAleatoria();
        double estatura = estaturaAleatoria();
        double nuevaEstatura = estatura + 1.0;
        double peso = pesoAleatorio();
        Perro = new Perro(nombre, raza, edad, estatura, peso);
        Assert.assertTrue(Perro.getEstatura() == estatura);
        Assert.assertFalse(Perro.getEstatura() == nuevaEstatura);
        Perro.setEstatura(nuevaEstatura);
        Assert.assertFalse(Perro.getEstatura() == estatura);
        Assert.assertTrue(Perro.getEstatura() == nuevaEstatura);
    }


    /**
     * Prueba unitaria para {@link Perro#getestatura}.
     */
    @Test public void testGetpeso() {
        String nombre = nombreAleatorio();
        String raza = razaAleatoria();
        int edad = edadAleatoria();
        double estatura = estaturaAleatoria();
        double peso = pesoAleatorio();
        Perro = new Perro(nombre, raza, edad, estatura, peso);
        Assert.assertTrue(Perro.getPeso() == peso);
        Assert.assertFalse(Perro.getPeso() == peso + 1.0);
    }

    /**
     * Prueba unitaria para {@link Perro#setestatura}.
     */
    @Test public void testSetpeso() {
        String nombre = nombreAleatorio();
        String raza = razaAleatoria();
        int edad = edadAleatoria();
        double estatura = estaturaAleatoria();
        double nuevopeso = estatura + 1.0;
        double peso = pesoAleatorio();
        Perro = new Perro(nombre, raza, edad, estatura, peso);
        Assert.assertTrue(Perro.getPeso() == peso);
        Assert.assertFalse(Perro.getPeso() == nuevopeso);
        Perro.setPeso(nuevopeso);
        Assert.assertFalse(Perro.getPeso() == peso);
        Assert.assertTrue(Perro.getPeso() == nuevopeso);
    }

    
    /**
     * Prueba unitaria para {@link Perro#toString}.
     */
    @Test public void testToString() {
        String nombre = nombreAleatorio();
        String raza = razaAleatoria();
        int edad = edadAleatoria();
        double estatura = estaturaAleatoria();
        double peso = pesoAleatorio();
        Perro = new Perro(nombre, raza, edad, estatura, peso);
        String cadena = String.format("Nombre: %s\n" + "Raza: %s\n" + "Edad: %d\n" + "Estatura: %2.2f\n" + "Peso: %2.2f\n", nombre, raza, edad, estatura, peso);
        Assert.assertTrue(Perro.toString().equals(cadena));
        edad = 7;
        estatura = 40.22;
        Perro.setEdad(edad);
        Perro.setEstatura(estatura);
        cadena = String.format("Nombre: %s\n" + "Raza: %s\n" + "Edad: %d\n" + "Estatura: %2.2f\n" + "Peso: %2.2f\n", nombre, raza, edad, estatura, peso);
        Assert.assertTrue(Perro.toString().equals(cadena));
    }

    /**
     * Prueba unitaria para {@link Perro#equals}.
     */
    @Test public void testEquals() {
        String nombre = nombreAleatorio();
        String raza = razaAleatoria();
        int edad = edadAleatoria();
        double estatura = estaturaAleatoria();
        double peso = pesoAleatorio();
        Perro = new Perro(nombre, raza, edad, estatura, peso);

        Perro igual = new Perro(new String(nombre), raza, edad, estatura, peso);
        Assert.assertTrue(Perro.equals(igual));
        String otroNombre = nombre + " Segundo";
        String otraRaza = raza + " Segunda";
        int otraEdad = edad + 1;        
        double otraEstatura = (estatura != 0.0) ? estatura / 10.0 : 10.0;
        double otroPeso = (peso != 0.0) ? peso / 10.0 : 10.0;

        Perro distinto = new Perro(otroNombre, raza, edad, estatura, peso);
        Assert.assertFalse(Perro.equals(distinto));
        distinto = new Perro(nombre, otraRaza, edad, estatura, peso);
        Assert.assertFalse(Perro.equals(distinto));
        distinto = new Perro(nombre, raza, otraEdad, estatura, peso);
        Assert.assertFalse(Perro.equals(distinto));
        distinto = new Perro(nombre, raza, edad, otraEstatura, peso);
        Assert.assertFalse(Perro.equals(distinto));
        distinto = new Perro(nombre, raza, edad, estatura, otroPeso);
        Assert.assertFalse(Perro.equals(distinto));

        distinto = new Perro(otroNombre, otraRaza, otraEdad, otraEstatura, otroPeso);
        Assert.assertFalse(Perro.equals(distinto));
        Assert.assertFalse(Perro.equals("Una cadena"));
        Assert.assertFalse(Perro.equals(null));
    }

    /**
     * Prueba unitaria para {@link Perro#seria}.
     */
    @Test public void testSeria() {
        String nombre = nombreAleatorio();
        String raza = razaAleatoria();
        int edad = edadAleatoria();
        double estatura = estaturaAleatoria();
        double peso = pesoAleatorio();
        Perro = new Perro(nombre, raza, edad, estatura, peso);

        String linea = String.format("%s\t%s\t%d\t%2.2f\t%2.2f\n", nombre, raza, edad,estatura, peso);
        Assert.assertTrue(Perro.seria().equals(linea));
    }

    /**
     * Prueba unitaria para {@link Perro#deseria}.
     */
    @Test public void testDeseria() {
        Perro = new Perro(null, null, 0, 0.0, 0.0);

        String nombre = nombreAleatorio();
        String raza = razaAleatoria();
        int edad = edadAleatoria();
        double estatura = estaturaAleatoria();
        double peso = pesoAleatorio();

        String linea = String.format("%s\t%s\t%d\t%2.2f\t%2.2f\n", nombre, raza, edad,estatura, peso);

        try {
            Perro.deseria(linea);
        } catch (ExcepcionLineaInvalida eli) {
            Assert.fail();
        }

        Assert.assertTrue(Perro.getNombre().equals(nombre));
        Assert.assertTrue(Perro.getRaza() == raza);
        Assert.assertTrue(Perro.getEdad() == edad);
        Assert.assertTrue(Perro.getEstatura() == estatura);
        Assert.assertTrue(Perro.getPeso() == peso);

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
        String raza = razaAleatoria();
        int edad = edadAleatoria();
        double estatura = estaturaAleatoria();
        double peso = pesoAleatorio();
        Perro = new Perro(nombre, raza, edad, estatura, peso);

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
        Assert.assertFalse(Perro.casa(CampoPerro.NOMBRE, Integer.valueOf(1000)));
        Assert.assertFalse(Perro.casa(CampoPerro.NOMBRE, null));

        String p = Perro.getRaza(); 
        int q = Perro.getRaza().length(); 
        Assert.assertTrue(Perro.casa(CampoPerro.RAZA, p));
        p = Perro.getRaza().substring(0, q/2);
        Assert.assertTrue(Perro.casa(CampoPerro.RAZA, p));
        p = Perro.getRaza().substring(q/2, q);
        Assert.assertTrue(Perro.casa(CampoPerro.RAZA, p));
        p = Perro.getRaza().substring(q/3, 2*(q/3));
        Assert.assertTrue(Perro.casa(CampoPerro.RAZA, p));
        Assert.assertFalse(Perro.casa(CampoPerro.RAZA, ""));
        Assert.assertFalse(Perro.casa(CampoPerro.RAZA, "XXX"));
        Assert.assertFalse(Perro.casa(CampoPerro.RAZA, Integer.valueOf(1000)));
        Assert.assertFalse(Perro.casa(CampoPerro.RAZA, null));

        Integer e = Integer.valueOf(Perro.getEdad());
        Assert.assertTrue(Perro.casa(CampoPerro.EDAD, e));
        e = Integer.valueOf(Perro.getEdad() - 10);
        Assert.assertTrue(Perro.casa(CampoPerro.EDAD, e));
        e = Integer.valueOf(Perro.getEdad() + 10);
        Assert.assertFalse(Perro.casa(CampoPerro.EDAD, e));
        Assert.assertFalse(Perro.casa(CampoPerro.EDAD, "XXX"));
        Assert.assertFalse(Perro.casa(CampoPerro.EDAD, null));

        
        Double es = Double.valueOf(Perro.getEstatura());
        Assert.assertTrue(Perro.casa(CampoPerro.ESTATURA, es));
        es = Double.valueOf(Perro.getEstatura() - 5.0);
        Assert.assertTrue(Perro.casa(CampoPerro.ESTATURA, es));
        es = Double.valueOf(Perro.getEstatura() + 5.0);
        Assert.assertFalse(Perro.casa(CampoPerro.ESTATURA, es));
        Assert.assertFalse(Perro.casa(CampoPerro.ESTATURA, "XXX"));
        Assert.assertFalse(Perro.casa(CampoPerro.ESTATURA, null));

        Double a = Double.valueOf(Perro.getPeso());
        Assert.assertTrue(Perro.casa(CampoPerro.PESO, a));
        a = Double.valueOf(Perro.getPeso() - 5.0);
        Assert.assertTrue(Perro.casa(CampoPerro.PESO, a));
        a = Double.valueOf(Perro.getPeso() + 5.0);
        Assert.assertFalse(Perro.casa(CampoPerro.PESO, a));
        Assert.assertFalse(Perro.casa(CampoPerro.PESO, "XXX"));
        Assert.assertFalse(Perro.casa(CampoPerro.PESO, null));

        

        try {
            Perro.casa(null, null);
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
        try {
            Perro.casa(X.A, Perro.getNombre());
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
        try {
            Perro.casa(X.A, Perro.getRaza());
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
        try {
            Object o = Integer.valueOf(Perro.getEdad());
            Perro.casa(X.A, o);
        } catch (IllegalArgumentException iae) {}
        try {
            Object o = Double.valueOf(Perro.getEstatura());
            Perro.casa(X.A, o);
        } catch (IllegalArgumentException iae) {}
        try {
            Object o = Double.valueOf(Perro.getPeso());
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
