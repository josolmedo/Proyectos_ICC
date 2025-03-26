package mx.unam.ciencias.icc.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Random;
import mx.unam.ciencias.icc.BaseDeDatos;
import mx.unam.ciencias.icc.BaseDeDatosPerros;
import mx.unam.ciencias.icc.CampoPerro;
import mx.unam.ciencias.icc.Perro;
import mx.unam.ciencias.icc.Lista;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Clase para pruebas unitarias de la clase {@link BaseDeDatosPerros}.
 */
public class TestBaseDeDatosPerros {

    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /* Generador de números aleatorios. */
    private Random random;
    /* Base de datos de perros. */
    private BaseDeDatosPerros bdd;
    /* Número total de Perros. */
    private int total;

    /* Enumeración espuria. */
    private enum X {
        /* Campo espurio. */
        A;
    }

    /**
     * Crea un generador de números aleatorios para cada prueba y una base de
     * datos de Perros.
     */
    public TestBaseDeDatosPerros() {
        random = new Random();
        bdd = new BaseDeDatosPerros();
        total = 2 + random.nextInt(100);
    }

    /**
     * Prueba unitaria para {@link
     * BaseDeDatosPerros#BaseDeDatosPerros}.
     */
    @Test public void testConstructor() {
        Lista Perros = bdd.getRegistros();
        Assert.assertFalse(Perros == null);
        Assert.assertTrue(Perros.getLongitud() == 0);
        Assert.assertTrue(bdd.getNumRegistros() == 0);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#getNumRegistros}.
     */
    @Test public void testGetNumRegistros() {
        Assert.assertTrue(bdd.getNumRegistros() == 0);
        for (int i = 0; i < total; i++) {
            Perro e = TestPerro.perroAleatorio();
            bdd.agregaRegistro(e);
            Assert.assertTrue(bdd.getNumRegistros() == i+1);
        }
        Assert.assertTrue(bdd.getNumRegistros() == total);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#getRegistros}.
     */
    @Test public void testGetRegistros() {
        Lista l = bdd.getRegistros();
        Lista r = bdd.getRegistros();
        Assert.assertTrue(l.equals(r));
        Assert.assertFalse(l == r);
        Estudiante[] Perros = new Estudiante[total];
        for (int i = 0; i < total; i++) {
            Perros[i] = TestEstudiante.estudianteAleatorio();
            bdd.agregaRegistro(Perros[i]);
        }
        l = bdd.getRegistros();
        int c = 0;
        Lista.Nodo nodo = l.getCabeza();
        while (nodo != null) {
            Assert.assertTrue(Perros[c++].equals(nodo.get()));
            nodo = nodo.getSiguiente();
        }
        l.elimina(Perros[0]);
        Assert.assertFalse(l.equals(bdd.getRegistros()));
        Assert.assertFalse(l.getLongitud() == bdd.getNumRegistros());
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#agregaRegistro}.
     */
    @Test public void testAgregaRegistro() {
        for (int i = 0; i < total; i++) {
            Estudiante e = TestEstudiante.estudianteAleatorio();
            Assert.assertFalse(bdd.getRegistros().contiene(e));
            bdd.agregaRegistro(e);
            Assert.assertTrue(bdd.getRegistros().contiene(e));
            Lista l = bdd.getRegistros();
            Assert.assertTrue(l.get(l.getLongitud() - 1).equals(e));
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#eliminaRegistro}.
     */
    @Test public void testEliminaRegistro() {
        int ini = random.nextInt(1000000);
        for (int i = 0; i < total; i++) {
            Estudiante e = TestEstudiante.estudianteAleatorio(ini + i);
            bdd.agregaRegistro(e);
        }
        while (bdd.getNumRegistros() > 0) {
            int i = random.nextInt(bdd.getNumRegistros());
            Estudiante e = (Estudiante)bdd.getRegistros().get(i);
            Assert.assertTrue(bdd.getRegistros().contiene(e));
            bdd.eliminaRegistro(e);
            Assert.assertFalse(bdd.getRegistros().contiene(e));
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#limpia}.
     */
    @Test public void testLimpia() {
        for (int i = 0; i < total; i++) {
            Estudiante e = TestEstudiante.estudianteAleatorio();
            bdd.agregaRegistro(e);
        }
        Lista registros = bdd.getRegistros();
        Assert.assertFalse(registros.esVacia());
        Assert.assertFalse(registros.getLongitud() == 0);
        bdd.limpia();
        registros = bdd.getRegistros();
        Assert.assertTrue(registros.esVacia());
        Assert.assertTrue(registros.getLongitud() == 0);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#guarda}.
     */
    @Test public void testGuarda() {
        for (int i = 0; i < total; i++) {
            Estudiante e = TestEstudiante.estudianteAleatorio();
            bdd.agregaRegistro(e);
        }
        String guardado = "";
        try {
            StringWriter swOut = new StringWriter();
            BufferedWriter out = new BufferedWriter(swOut);
            bdd.guarda(out);
            out.close();
            guardado = swOut.toString();
        } catch (IOException ioe) {
            Assert.fail();
        }
        String[] lineas = guardado.split("\n");
        Assert.assertTrue(lineas.length == total);
        Lista l = bdd.getRegistros();
        int c = 0;
        Lista.Nodo nodo = l.getCabeza();
        while (nodo != null) {
            Estudiante e = (Estudiante)nodo.get();
            String el = String.format("%s\t%d\t%2.2f\t%d",
                                      e.getNombre(),
                                      e.getCuenta(),
                                      e.getPromedio(),
                                      e.getEdad());
            Assert.assertTrue(lineas[c++].equals(el));
            nodo = nodo.getSiguiente();
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#carga}.
     */
    @Test public void testCarga() {
        int ini = random.nextInt(1000000);
        String entrada = "";
        Estudiante[] Perros = new Estudiante[total];
        for (int i = 0; i < total; i++) {
            Perros[i] = TestEstudiante.estudianteAleatorio(ini + i);
            entrada += String.format("%s\t%d\t%2.2f\t%d\n",
                                     Perros[i].getNombre(),
                                     Perros[i].getCuenta(),
                                     Perros[i].getPromedio(),
                                     Perros[i].getEdad());
            bdd.agregaRegistro(Perros[i]);
        }
        try {
            StringReader srInt = new StringReader(entrada);
            BufferedReader in = new BufferedReader(srInt, 8192);
            bdd.carga(in);
            in.close();
        } catch (IOException ioe) {
            Assert.fail();
        }
        Assert.assertTrue(bdd.getNumRegistros() == total);
        int c = 0;
        Lista l = bdd.getRegistros();
        Lista.Nodo nodo = l.getCabeza();
        while (nodo != null) {
            Assert.assertTrue(Perros[c++].equals(nodo.get()));
            nodo = nodo.getSiguiente();
        }
        entrada = String.format("%s\t%d\t%2.2f\t%d\n",
                                Perros[0].getNombre(),
                                Perros[0].getCuenta(),
                                Perros[0].getPromedio(),
                                Perros[0].getEdad());
        entrada += " \n";
        entrada += String.format("%s\t%d\t%2.2f\t%d\n",
                                 Perros[1].getNombre(),
                                 Perros[1].getCuenta(),
                                 Perros[1].getPromedio(),
                                 Perros[1].getEdad());
        try {
            StringReader srInt = new StringReader(entrada);
            BufferedReader in = new BufferedReader(srInt, 8192);
            bdd.carga(in);
            in.close();
        } catch (IOException ioe) {
            Assert.fail();
        }
        Assert.assertTrue(bdd.getNumRegistros() == 1);
        entrada = "";
        try {
            StringReader srInt = new StringReader(entrada);
            BufferedReader in = new BufferedReader(srInt, 8192);
            bdd.carga(in);
            in.close();
        } catch (IOException ioe) {
            Assert.fail();
        }
        Assert.assertTrue(bdd.getNumRegistros() == 0);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatosPerros#creaRegistro}.
     */
    @Test public void testCreaRegistro() {
        Estudiante e = (Estudiante)bdd.creaRegistro();
        Assert.assertTrue(e.getNombre() == null);
        Assert.assertTrue(e.getCuenta() == 0);
        Assert.assertTrue(e.getPromedio() == 0.0);
        Assert.assertTrue(e.getEdad() == 0);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatosPerros#buscaRegistros}.
     */
    @Test public void testBuscaRegistros() {
        Estudiante[] Perros = new Estudiante[total];
        int ini = 1000000 + random.nextInt(999999);
        for (int i = 0; i < total; i++) {
            Estudiante e =  new Estudiante(String.valueOf(ini+i),
                                           ini+i, (i * 10.0) / total, i);
            Perros[i] = e;
            bdd.agregaRegistro(e);
        }

        Estudiante estudiante;
        Lista l;
        Lista.Nodo nodo;
        int i;

        for (int k = 0; k < total/10 + 3; k++) {
            i = random.nextInt(total);
            estudiante = Perros[i];

            String nombre = estudiante.getNombre();
            l = bdd.buscaRegistros(CampoEstudiante.NOMBRE, nombre);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(estudiante));
            nodo = l.getCabeza();
            while (nodo != null) {
                Estudiante e = (Estudiante)nodo.get();
                Assert.assertTrue(e.getNombre().indexOf(nombre) > -1);
                nodo = nodo.getSiguiente();
            }
            int n = nombre.length();
            String bn = nombre.substring(random.nextInt(2),
                                         2 + random.nextInt(n-2));
            l = bdd.buscaRegistros(CampoEstudiante.NOMBRE, bn);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(estudiante));
            nodo = l.getCabeza();
            while (nodo != null) {
                Estudiante e = (Estudiante)nodo.get();
                Assert.assertTrue(e.getNombre().indexOf(bn) > -1);
                nodo = nodo.getSiguiente();
            }

            Integer cuenta = Integer.valueOf(estudiante.getCuenta());
            l = bdd.buscaRegistros(CampoEstudiante.CUENTA, cuenta);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(estudiante));
            nodo = l.getCabeza();
            while (nodo != null) {
                Estudiante e = (Estudiante)nodo.get();
                Assert.assertTrue(e.getCuenta() >= cuenta.intValue());
                nodo = nodo.getSiguiente();
            }
            Integer bc = Integer.valueOf(cuenta.intValue() - 10);
            l = bdd.buscaRegistros(CampoEstudiante.CUENTA, bc);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(estudiante));
            nodo = l.getCabeza();
            while (nodo != null) {
                Estudiante e = (Estudiante)nodo.get();
                Assert.assertTrue(e.getCuenta() >= bc.intValue());
                nodo = nodo.getSiguiente();
            }

            Double promedio = Double.valueOf(estudiante.getPromedio());
            l = bdd.buscaRegistros(CampoEstudiante.PROMEDIO, promedio);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(estudiante));
            nodo = l.getCabeza();
            while (nodo != null) {
                Estudiante e = (Estudiante)nodo.get();
                Assert.assertTrue(e.getPromedio() >= promedio.doubleValue());
                nodo = nodo.getSiguiente();
            }
            Double bp = Double.valueOf(promedio.doubleValue() - 5.0);
            l = bdd.buscaRegistros(CampoEstudiante.PROMEDIO, bp);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(estudiante));
            nodo = l.getCabeza();
            while (nodo != null) {
                Estudiante e = (Estudiante)nodo.get();
                Assert.assertTrue(e.getPromedio() >= bp.doubleValue());
                nodo = nodo.getSiguiente();
            }

            Integer edad = Integer.valueOf(estudiante.getEdad());
            l = bdd.buscaRegistros(CampoEstudiante.EDAD, edad);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(estudiante));
            nodo = l.getCabeza();
            while (nodo != null) {
                Estudiante e = (Estudiante)nodo.get();
                Assert.assertTrue(e.getEdad() >= edad.intValue());
                nodo = nodo.getSiguiente();
            }
            Integer be = Integer.valueOf(edad.intValue() - 10);
            l = bdd.buscaRegistros(CampoEstudiante.EDAD, be);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(estudiante));
            nodo = l.getCabeza();
            while (nodo != null) {
                Estudiante e = (Estudiante)nodo.get();
                Assert.assertTrue(e.getEdad() >= be.intValue());
                nodo = nodo.getSiguiente();
            }
        }

        l = bdd.buscaRegistros(CampoEstudiante.NOMBRE,
                               "xxx-nombre");
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoEstudiante.CUENTA,
                               Integer.valueOf(9123456));
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoEstudiante.PROMEDIO,
                               Double.valueOf(97.12));
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoEstudiante.EDAD,
                               Integer.valueOf(127));
        Assert.assertTrue(l.esVacia());

        l = bdd.buscaRegistros(CampoEstudiante.NOMBRE, "");
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoEstudiante.CUENTA,
                               Integer.valueOf(Integer.MAX_VALUE));
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoEstudiante.PROMEDIO,
                               Double.valueOf(Double.MAX_VALUE));
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoEstudiante.EDAD,
                               Integer.valueOf(Integer.MAX_VALUE));
        Assert.assertTrue(l.esVacia());

        l = bdd.buscaRegistros(CampoEstudiante.NOMBRE, null);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoEstudiante.CUENTA, null);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoEstudiante.PROMEDIO, null);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoEstudiante.EDAD, null);
        Assert.assertTrue(l.esVacia());

        try {
            l = bdd.buscaRegistros(null, null);
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
        try {
            l = bdd.buscaRegistros(X.A, null);
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
    }
}
