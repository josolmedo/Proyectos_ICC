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
            Perro e = TestPerro.PerroAleatorio();
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
        Perro[] Perros = new Perro[total];
        for (int i = 0; i < total; i++) {
            Perros[i] = TestPerro.PerroAleatorio();
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
            Perro e = TestPerro.PerroAleatorio();
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
            Perro e = TestPerro.PerroAleatorio(ini + i);
            bdd.agregaRegistro(e);
        }
        while (bdd.getNumRegistros() > 0) {
            int i = random.nextInt(bdd.getNumRegistros());
            Perro e = (Perro)bdd.getRegistros().get(i);
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
            Perro e = TestPerro.PerroAleatorio();
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
     
    @Test public void testGuarda() {
        for (int i = 0; i < total; i++) {
            Perro e = TestPerro.PerroAleatorio();
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
            Perro e = (Perro)nodo.get();
            String el = String.format("%s\t%s\t%d\t%2.2f\t%2.2f\n", e.getNombre(), e.getRaza(), e.getEdad(), e.getEstatura(), e.getPeso());
            Assert.assertTrue(lineas[c++].equals(el));
            nodo = nodo.getSiguiente();
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#carga}.
     
    @Test public void testCarga() {
        int ini = random.nextInt(1000000);
        String entrada = "";
        Perro[] Perros = new Perro[total];
        for (int i = 0; i < total; i++) {
            Perros[i] = TestPerro.PerroAleatorio(ini + i);
            entrada += String.format("%s\t%s\t%d\t%2.2f\t%2.2f\n", Perros[i].getNombre(), Perros[i].getRaza(), Perros[i].getEdad(), Perros[i].getEstatura(), Perros[i].getPeso());
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
        entrada = String.format("%s\t%s\t%d\t%2.2f\t%2.2f\n", Perros[0].getNombre(), Perros[0].getRaza(), Perros[0].getEdad(), Perros[0].getEstatura(), Perros[0].getPeso());
        entrada += " \n";
        entrada += String.format("%s\t%s\t%d\t%2.2f\t%2.2f\n", Perros[1].getNombre(), Perros[1].getRaza(), Perros[1].getEdad(), Perros[1].getEstatura(), Perros[1].getPeso());
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
    }*/

    /**
     * Prueba unitaria para {@link BaseDeDatosPerros#creaRegistro}.
     */
    @Test public void testCreaRegistro() {
        Perro e = (Perro)bdd.creaRegistro();
        Assert.assertTrue(e.getNombre() == null);
        Assert.assertTrue(e.getRaza() == null);
        Assert.assertTrue(e.getEdad() == 0);
        Assert.assertTrue(e.getEstatura() == 0.0);
        Assert.assertTrue(e.getPeso() == 0.0);
        
    }

    /**
     * Prueba unitaria para {@link BaseDeDatosPerros#buscaRegistros}.
     
    @Test public void testBuscaRegistros() {
        Perro[] Perros = new Perro[total];
        int ini = 1000000 + random.nextInt(999999);
        for (int i = 0; i < total; i++) {
            Perro e =  new Perro(String.valueOf(ini+i), String.valueOf(ini+1), ini+i, (i * 10.0) / total, (i * 10.0) / total);
            Perros[i] = e;
            bdd.agregaRegistro(e);
        }

        Perro Perro;
        Lista l;
        Lista.Nodo nodo;
        int i;

        for (int k = 0; k < total/10 + 3; k++) {
            i = random.nextInt(total);
            Perro = Perros[i];
//////////////////////////////////////////////////////////////////////////////////
            String nombre = Perro.getNombre();
            l = bdd.buscaRegistros(CampoPerro.NOMBRE, nombre);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(Perro));
            nodo = l.getCabeza();
            while (nodo != null) {
                Perro e = (Perro)nodo.get();
                Assert.assertTrue(e.getNombre().indexOf(nombre) > -1);
                nodo = nodo.getSiguiente();
            }
            int n = nombre.length();
            String bn = nombre.substring(random.nextInt(2),
                                         2 + random.nextInt(n-2));
            l = bdd.buscaRegistros(CampoPerro.NOMBRE, bn);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(Perro));
            nodo = l.getCabeza();
            while (nodo != null) {
                Perro e = (Perro)nodo.get();
                Assert.assertTrue(e.getNombre().indexOf(bn) > -1);
                nodo = nodo.getSiguiente();
            }
////////////////////////////////////////////////////////////////////////////////////
            String raza = Perro.getRaza();
            l = bdd.buscaRegistros(CampoPerro.RAZA, raza);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(Perro));
            nodo = l.getCabeza();
            while (nodo != null) {
                Perro e = (Perro)nodo.get();
                Assert.assertTrue(e.getRaza().indexOf(raza) > -1);
                nodo = nodo.getSiguiente();
            }
            int ra = raza.length();
            String cn = raza.substring(random.nextInt(2),
                                         2 + random.nextInt(ra-2));
            l = bdd.buscaRegistros(CampoPerro.RAZA, cn);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(Perro));
            nodo = l.getCabeza();
            while (nodo != null) {
                Perro e = (Perro)nodo.get();
                Assert.assertTrue(e.getNombre().indexOf(cn) > -1);
                nodo = nodo.getSiguiente();
            }
//////////////////////////////////////////////////////////////////////////////////////
            Integer edad = Integer.valueOf(Perro.getEdad());
            l = bdd.buscaRegistros(CampoPerro.EDAD, edad);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(Perro));
            nodo = l.getCabeza();
            while (nodo != null) {
                Perro e = (Perro)nodo.get();
                Assert.assertTrue(e.getEdad() >= edad.intValue());
                nodo = nodo.getSiguiente();
            }
            Integer be = Integer.valueOf(edad.intValue() - 10);
            l = bdd.buscaRegistros(CampoPerro.EDAD, be);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(Perro));
            nodo = l.getCabeza();
            while (nodo != null) {
                Perro e = (Perro)nodo.get();
                Assert.assertTrue(e.getEdad() >= be.intValue());
                nodo = nodo.getSiguiente();
            }
/////////////////////////////////////////////////////////////////////////////////////////////////            

            Double estatura = Double.valueOf(Perro.getEstatura());
            l = bdd.buscaRegistros(CampoPerro.ESTATURA, estatura);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(Perro));
            nodo = l.getCabeza();
            while (nodo != null) {
                Perro e = (Perro)nodo.get();
                Assert.assertTrue(e.getEstatura() >= estatura.doubleValue());
                nodo = nodo.getSiguiente();
            }
            Double bp = Double.valueOf(estatura.doubleValue() - 5.0);
            l = bdd.buscaRegistros(CampoPerro.ESTATURA, bp);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(Perro));
            nodo = l.getCabeza();
            while (nodo != null) {
                Perro e = (Perro)nodo.get();
                Assert.assertTrue(e.getEstatura() >= bp.doubleValue());
                nodo = nodo.getSiguiente();
            }
/////////////////////////////////////////////////////////////////////////////////////////////////
            Double peso = Double.valueOf(Perro.getPeso());
            l = bdd.buscaRegistros(CampoPerro.PESO, peso);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(Perro));
            nodo = l.getCabeza();
            while (nodo != null) {
                Perro e = (Perro)nodo.get();
                Assert.assertTrue(e.getPeso() >= peso.doubleValue());
                nodo = nodo.getSiguiente();
            }
            Double pb = Double.valueOf(peso.doubleValue() - 5.0);
            l = bdd.buscaRegistros(CampoPerro.PESO, pb);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(Perro));
            nodo = l.getCabeza();
            while (nodo != null) {
                Perro e = (Perro)nodo.get();
                Assert.assertTrue(e.getPeso() >= pb.doubleValue());
                nodo = nodo.getSiguiente();
            }

////////////////////////////////////////////////////////////////////////////////////////////////

            
        }

        l = bdd.buscaRegistros(CampoPerro.NOMBRE, "xxx-nombre");
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoPerro.RAZA, "xxx-raza");
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoPerro.EDAD, Integer.valueOf(127));
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoPerro.ESTATURA, Double.valueOf(97.12));
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoPerro.PESO, Double.valueOf(67.55));
        Assert.assertTrue(l.esVacia());
        

        l = bdd.buscaRegistros(CampoPerro.NOMBRE, "");
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoPerro.RAZA, "");
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoPerro.EDAD, Integer.valueOf(Integer.MAX_VALUE));
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoPerro.ESTATURA, Double.valueOf(Double.MAX_VALUE));
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoPerro.PESO, Double.valueOf(Double.MAX_VALUE));
        Assert.assertTrue(l.esVacia());
        

        l = bdd.buscaRegistros(CampoPerro.NOMBRE, null);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoPerro.RAZA, null);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoPerro.EDAD, null);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoPerro.ESTATURA, null);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoPerro.PESO, null);
        Assert.assertTrue(l.esVacia());

        try {
            l = bdd.buscaRegistros(null, null);
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
        try {
            l = bdd.buscaRegistros(X.A, null);
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
    }*/
}
