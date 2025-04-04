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
    private Perro perro;

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
        perro = new Perro(nombre, raza, edad, estatura, peso);
        Assert.assertTrue(perro.getNombre().equals(nombre));
        Assert.assertTrue(perro.getRaza() == raza);
        Assert.assertTrue(perro.getEdad() == edad);
        Assert.assertTrue(perro.getEstatura() == estatura);
        Assert.assertTrue(perro.getPeso() == peso);
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
        perro = new Perro(nombre, raza, edad, estatura, peso);
        Assert.assertTrue(perro.getNombre().equals(nombre));
        Assert.assertFalse(perro.getNombre().equals(nombre + " X"));
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
        perro = new Perro(nombre, raza, edad, estatura, peso);
        Assert.assertTrue(perro.getNombre().equals(nombre));
        Assert.assertFalse(perro.getNombre().equals(nuevoNombre));
        perro.setNombre(nuevoNombre);
        Assert.assertFalse(perro.getNombre().equals(nombre));
        Assert.assertTrue(perro.getNombre().equals(nuevoNombre));
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
        perro = new Perro(nombre, raza, edad, estatura, peso);
        Assert.assertTrue(perro.getRaza().equals(raza));
        Assert.assertFalse(perro.getRaza().equals(raza + " X"));
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
        perro = new Perro(nombre, raza, edad, estatura, peso);
        Assert.assertTrue(perro.getRaza().equals(raza));
        Assert.assertFalse(perro.getRaza().equals(nuevaRaza));
        perro.setRaza(nuevaRaza);
        Assert.assertFalse(perro.getRaza().equals(raza));
        Assert.assertTrue(perro.getRaza().equals(nuevaRaza));
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
        perro = new Perro(nombre, raza, edad, estatura, peso);
        Assert.assertTrue(perro.getEdad() == edad);
        Assert.assertFalse(perro.getEdad() == edad + 50);
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
        perro = new Perro(nombre, raza, edad, estatura, peso);        
        Assert.assertTrue(perro.getEdad() == edad);
        Assert.assertFalse(perro.getEdad() == nuevaEdad);
        perro.setEdad(nuevaEdad);
        Assert.assertFalse(perro.getEdad() == edad);
        Assert.assertTrue(perro.getEdad() == nuevaEdad);
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
        perro = new Perro(nombre, raza, edad, estatura, peso);
        Assert.assertTrue(perro.getEstatura() == estatura);
        Assert.assertFalse(perro.getEstatura() == estatura + 1.0);
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
        perro = new Perro(nombre, raza, edad, estatura, peso);
        Assert.assertTrue(perro.getEstatura() == estatura);
        Assert.assertFalse(perro.getEstatura() == nuevaEstatura);
        perro.setEstatura(nuevaEstatura);
        Assert.assertFalse(perro.getEstatura() == estatura);
        Assert.assertTrue(perro.getEstatura() == nuevaEstatura);
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
        perro = new Perro(nombre, raza, edad, estatura, peso);
        Assert.assertTrue(perro.getPeso() == peso);
        Assert.assertFalse(perro.getPeso() == peso + 1.0);
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
        perro = new Perro(nombre, raza, edad, estatura, peso);
        Assert.assertTrue(perro.getPeso() == peso);
        Assert.assertFalse(perro.getPeso() == nuevopeso);
        perro.setPeso(nuevopeso);
        Assert.assertFalse(perro.getPeso() == peso);
        Assert.assertTrue(perro.getPeso() == nuevopeso);
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
        perro = new Perro(nombre, raza, edad, estatura, peso);
        String cadena = String.format("Nombre: %s\n" + "Raza: %s\n" + "Edad: %d\n" + "Estatura: %2.2f\n" + "Peso: %2.2f\n", nombre, raza, edad, estatura, peso);
        Assert.assertTrue(perro.toString().equals(cadena));
        edad = 7;
        estatura = 40.22;
        perro.setEdad(edad);
        perro.setEstatura(estatura);
        cadena = String.format("Nombre: %s\n" + "Raza: %s\n" + "Edad: %d\n" + "Estatura: %2.2f\n" + "Peso: %2.2f\n", nombre, raza, edad, estatura, peso);
        Assert.assertTrue(perro.toString().equals(cadena));
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
        perro = new Perro(nombre, raza, edad, estatura, peso);

        Perro igual = new Perro(new String(nombre), raza, edad, estatura, peso);
        Assert.assertTrue(perro.equals(igual));
        String otroNombre = nombre + " Segundo";
        String otraRaza = raza + " Segunda";
        int otraEdad = edad + 1;        
        double otraEstatura = (estatura != 0.0) ? estatura / 10.0 : 10.0;
        double otroPeso = (peso != 0.0) ? peso / 10.0 : 10.0;

        Perro distinto = new Perro(otroNombre, raza, edad, estatura, peso);
        Assert.assertFalse(perro.equals(distinto));
        distinto = new Perro(nombre, otraRaza, edad, estatura, peso);
        Assert.assertFalse(perro.equals(distinto));
        distinto = new Perro(nombre, raza, otraEdad, estatura, peso);
        Assert.assertFalse(perro.equals(distinto));
        distinto = new Perro(nombre, raza, edad, otraEstatura, peso);
        Assert.assertFalse(perro.equals(distinto));
        distinto = new Perro(nombre, raza, edad, estatura, otroPeso);
        Assert.assertFalse(perro.equals(distinto));

        distinto = new Perro(otroNombre, otraRaza, otraEdad, otraEstatura, otroPeso);
        Assert.assertFalse(perro.equals(distinto));
        Assert.assertFalse(perro.equals("Una cadena"));
        Assert.assertFalse(perro.equals(null));
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
        perro = new Perro(nombre, raza, edad, estatura, peso);

        String linea = String.format("%s\t%s\t%d\t%2.2f\t%2.2f\n", nombre, raza, edad,estatura, peso);
        Assert.assertTrue(perro.seria().equals(linea));
    }

    /**
     * Prueba unitaria para {@link Perro#deseria}.
     
    @Test public void testDeseria() {

        perro = new Perro(null, null, 0, 0.0, 0.0);
        

        String nombre = nombreAleatorio();
        String raza = razaAleatoria();
        int edad = edadAleatoria();
        double estatura = estaturaAleatoria();
        double peso = pesoAleatorio();

        
        

        String linea = String.format("%s\t%s\t%d\t%2.2f\t%2.2f\n", nombre, raza, edad,estatura, peso);

        try {
            perro.deseria(linea);
        } catch (ExcepcionLineaInvalida eli) {
            Assert.fail();
        }

        Assert.assertTrue(perro.getNombre().equals(nombre));
        Assert.assertTrue(perro.getRaza().equals(raza));
        Assert.assertTrue(perro.getEdad() == edad);
        //Assert.assertTrue(perro.getEstatura() == estatura);
        //Assert.assertTrue(perro.getPeso() == peso);

        String[] invalidas = {"", " ", "\t", "  ", "\t\t",
                              " \t", "\t ", "\n", "a\ta\ta",
                              "a\ta\ta\ta"};

        for (int i = 0; i < invalidas.length; i++) {
            linea = invalidas[i];
            try {
                perro.deseria(linea);
                Assert.fail();
            } catch (ExcepcionLineaInvalida eli) {}
        }
    }*/

    /**
     * Prueba unitaria para {@link perro#casa}.
     */
    @Test public void testCasa() {
        String nombre = nombreAleatorio();
        String raza = razaAleatoria();
        int edad = edadAleatoria();
        double estatura = estaturaAleatoria();
        double peso = pesoAleatorio();
        perro = new Perro(nombre, raza, edad, estatura, peso);

        String n = perro.getNombre();
        int m = perro.getNombre().length();
        Assert.assertTrue(perro.casa(CampoPerro.NOMBRE, n));
        n = perro.getNombre().substring(0, m/2);
        Assert.assertTrue(perro.casa(CampoPerro.NOMBRE, n));
        n = perro.getNombre().substring(m/2, m);
        Assert.assertTrue(perro.casa(CampoPerro.NOMBRE, n));
        n = perro.getNombre().substring(m/3, 2*(m/3));
        Assert.assertTrue(perro.casa(CampoPerro.NOMBRE, n));
        Assert.assertFalse(perro.casa(CampoPerro.NOMBRE, ""));
        Assert.assertFalse(perro.casa(CampoPerro.NOMBRE, "XXX"));
        Assert.assertFalse(perro.casa(CampoPerro.NOMBRE, Integer.valueOf(1000)));
        Assert.assertFalse(perro.casa(CampoPerro.NOMBRE, null));

        String p = perro.getRaza(); 
        int q = perro.getRaza().length(); 
        Assert.assertTrue(perro.casa(CampoPerro.RAZA, p));
        p = perro.getRaza().substring(0, q/2);
        Assert.assertTrue(perro.casa(CampoPerro.RAZA, p));
        p = perro.getRaza().substring(q/2, q);
        Assert.assertTrue(perro.casa(CampoPerro.RAZA, p));
        p = perro.getRaza().substring(q/3, 2*(q/3));
        Assert.assertTrue(perro.casa(CampoPerro.RAZA, p));
        Assert.assertFalse(perro.casa(CampoPerro.RAZA, ""));
        Assert.assertFalse(perro.casa(CampoPerro.RAZA, "XXX"));
        Assert.assertFalse(perro.casa(CampoPerro.RAZA, Integer.valueOf(1000)));
        Assert.assertFalse(perro.casa(CampoPerro.RAZA, null));

        Integer e = Integer.valueOf(perro.getEdad());
        Assert.assertTrue(perro.casa(CampoPerro.EDAD, e));
        e = Integer.valueOf(perro.getEdad() - 10);
        Assert.assertTrue(perro.casa(CampoPerro.EDAD, e));
        e = Integer.valueOf(perro.getEdad() + 10);
        Assert.assertFalse(perro.casa(CampoPerro.EDAD, e));
        Assert.assertFalse(perro.casa(CampoPerro.EDAD, "XXX"));
        Assert.assertFalse(perro.casa(CampoPerro.EDAD, null));

        
        Double es = Double.valueOf(perro.getEstatura());
        Assert.assertTrue(perro.casa(CampoPerro.ESTATURA, es));
        es = Double.valueOf(perro.getEstatura() - 5.0);
        Assert.assertTrue(perro.casa(CampoPerro.ESTATURA, es));
        es = Double.valueOf(perro.getEstatura() + 5.0);
        Assert.assertFalse(perro.casa(CampoPerro.ESTATURA, es));
        Assert.assertFalse(perro.casa(CampoPerro.ESTATURA, "XXX"));
        Assert.assertFalse(perro.casa(CampoPerro.ESTATURA, null));

        Double a = Double.valueOf(perro.getPeso());
        Assert.assertTrue(perro.casa(CampoPerro.PESO, a));
        a = Double.valueOf(perro.getPeso() - 5.0);
        Assert.assertTrue(perro.casa(CampoPerro.PESO, a));
        a = Double.valueOf(perro.getPeso() + 5.0);
        Assert.assertFalse(perro.casa(CampoPerro.PESO, a));
        Assert.assertFalse(perro.casa(CampoPerro.PESO, "XXX"));
        Assert.assertFalse(perro.casa(CampoPerro.PESO, null));

        

        try {
            perro.casa(null, null);
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
        try {
            perro.casa(X.A, perro.getNombre());
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
        try {
            perro.casa(X.A, perro.getRaza());
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
        try {
            Object o = Integer.valueOf(perro.getEdad());
            perro.casa(X.A, o);
        } catch (IllegalArgumentException iae) {}
        try {
            Object o = Double.valueOf(perro.getEstatura());
            perro.casa(X.A, o);
        } catch (IllegalArgumentException iae) {}
        try {
            Object o = Double.valueOf(perro.getPeso());
            perro.casa(X.A, o);
        } catch (IllegalArgumentException iae) {}
        try {
            Assert.assertFalse(perro.casa(X.A, null));
        } catch (IllegalArgumentException iae) {}
    }

    /* Inicializa el generador de números aleatorios. */
    static {
        random = new Random();
    }
}
