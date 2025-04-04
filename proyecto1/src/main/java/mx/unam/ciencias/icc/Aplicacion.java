package mx.unam.ciencias.icc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase para aplicaciones de la base de datos de Perros.
 */
public class Aplicacion {

    /* Modo de la aplicación. */
    private enum Modo {
        /* Modo para guardar. */
        GUARDA(1),
        /* Modo para cargar. */
        CARGA(2);

        /* Código de terminación. */
        private int codigo;

        /* Constructor. */
        private Modo(int codigo) {
            this.codigo = codigo;
        }

        /* Regresa el código. */
        public int getCodigo() {
            return codigo;
        }

        /* Regresa el modo de la bandera. */
        public static Modo getModo(String bandera) {
            switch (bandera) {
            case "-g": return GUARDA;
            case "-c": return CARGA;
            default: throw new IllegalArgumentException(
                "Bandera inválida: " + bandera);
            }
        }
    }

    /* La base de datos. */
    private BaseDeDatosPerros bdd;
    /* La ruta del archivo. */
    private String ruta;
    /* El modo de la aplicación. */
    private Modo modo;

    /**
     * Define el estado inicial de la aplicación.
     * @param bandera la bandera de modo.
     * @param ruta la ruta del archivo.
     * @throws IllegalArgumentException si la bandera no es "-r" o "-g".
     */
    public Aplicacion(String bandera, String ruta) {
        modo = Modo.getModo(bandera);
        this.ruta = ruta;
        bdd = new BaseDeDatosPerros();
    }

    /**
     * Ejecuta la aplicación.
     */
    public void ejecuta() {
        switch (modo) {
        case GUARDA:
            guarda();
            break;
        case CARGA:
            carga();
            break;
        }
    }

    /* Modo de guarda de la aplicación. */
    private void guarda() {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        verificaSalida(sc);
        agregaPerros(sc);
        sc.close();
        try {
            BufferedWriter out =
                new BufferedWriter(
                    new OutputStreamWriter(
                        new FileOutputStream(ruta)));
            bdd.guarda(out);
            out.close();
        } catch (IOException ioe) {
            System.err.printf("No pude guardar en el archivo \"%s\".\n",
                              ruta);
            System.exit(modo.getCodigo());
        }
    }

    /* Verifica que la salida no exista o le permite salir al usuario. */
    private void verificaSalida(Scanner sc) {
        File archivo = new File(ruta);
        if (archivo.exists()) {
            System.out.printf("El archivo \"%s\" ya existe y será " +
                              "reescrito.\n¿Desea continuar? (s/n): ", ruta);
            String r = sc.next();
            if (!r.equals("s")) {
                sc.close();
                System.exit(0);
            }
        }
    }

    /* Agrega Perros a la base de datos mientras el usuario lo desee. */
    private void agregaPerros(Scanner sc) {
         System.out.println("\nDeje el nombre en blanco para " +
                            "parar la captura de Perros.");
        Perro e = null;
        do {
            try {
                e = getPerro(sc);
                if (e != null)
                    bdd.agregaRegistro(e);
            } catch (InputMismatchException ime) {
                System.err.printf("\nNúmero inválido. Se descartará " +
                                  "este Perro.\n");
                sc.next(); // Purgamos la última entrada del usuario.
                continue;
            }
        } while (e != null);
    }

    /* Obtiene un Perro de la línea de comandos. */
    private Perro getPerro(Scanner sc) {

        System.out.printf("\nNombre   : ");
        String nombre = sc.next();
        if (nombre.equals("")){
            return null;
        }

        System.out.printf("Raza   : ");
        String raza=sc.next();
        if (raza.equals("")){
            return null;
        }

        System.out.printf("Edad     : ");
        int edad = sc.nextInt();

        System.out.printf("Estatura     : ");
        double estatura = sc.nextDouble();

        System.out.printf("Peso     : ");
        double peso = sc.nextDouble();
      
        return new Perro(nombre, raza, edad, estatura, peso);
    }

    /* Modo de carga de la aplicación. */
    private void carga() {
        try {
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(ruta)));
            bdd.carga(in);
            in.close();
        } catch (IOException ioe) {
            System.err.printf("No pude cargar del archivo \"%s\".\n",
                              ruta);
            System.exit(modo.getCodigo());
        }
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        busca(sc);
        sc.close();
    }

    /* Hace la búsqueda. */
    private void busca(Scanner sc) {
        System.out.println("\nDeje el campo en blanco para " +
                           "parar la búsqueda de Perros.");
        String c = "X";
        while (!(c = getCampo(sc)).equals("")) {
            Lista l;
            try {
                l = getResultados(c, sc);
            } catch (ExcepcionOpcionInvalida epi) {
                System.out.printf("%s\n", epi.getMessage());
                continue;
            } catch (InputMismatchException ime) {
                System.out.printf("\nValor inválido. Búsqueda descartada.\n");
                sc.next(); // Purgamos la entrada.
                continue;
            }
            Lista.Nodo nodo = l.getCabeza();
            String m = nodo != null ? "" :
                "\nCero registros casan la búsqueda.";
            System.out.println(m);
            while (nodo != null) {
                System.out.printf("%s\n\n", nodo.get().toString());
                nodo = nodo.getSiguiente();
            }
        }
    }

    /* Regresa el campo. */
    private String getCampo(Scanner sc) {
        System.out.printf("\n¿Por qué campo quiere buscar? (n/r/ed/es/p): ");
        return sc.next();
    }

    /* Regresa los resultados de la búsqueda. */
    private Lista getResultados(String c, Scanner sc) {
        System.out.println();
        switch (c) {
        case "n": return bdd.buscaRegistros(CampoPerro.NOMBRE,
                                            getValorNombre(sc));
        case "r": return bdd.buscaRegistros(CampoPerro.RAZA,
                                            getValorRaza(sc));
        case "ed": return bdd.buscaRegistros(CampoPerro.EDAD,
                                            getValorEdad(sc));
        case "es": return bdd.buscaRegistros(CampoPerro.ESTATURA,
                                            getValorEstatura(sc));
        case "p": return bdd.buscaRegistros(CampoPerro.PESO,
                                            getValorPeso(sc));
        default:
            String m = String.format("El campo '%s' es inválido.", c);
            throw new ExcepcionOpcionInvalida(m);
        }
    }

    /* Regresa el valor a buscar para nombre. */
    private String getValorNombre(Scanner sc) {
        System.out.printf("Ingrese el nombre del perro: ");
        return sc.next();
    }

    /* Regresa el valor a buscar para raza. */
    private String getValorRaza(Scanner sc) {
        System.out.printf("Ingrese la raza del perro: ");
        return sc.next();
    }

    /* Regresa el valor a buscar para la edad. */
    private Integer getValorEdad(Scanner sc) {
        System.out.printf("La edad debe ser mayor o igual a: ");
        return Integer.valueOf(sc.nextInt());
    }

    /* Regresa el valor a buscar para la estatura. */
    private Double getValorEstatura(Scanner sc) {
        System.out.printf("La estatura debe ser mayor o igual a: ");
        return Double.valueOf(sc.nextDouble());
    }

    /* Regresa el valor a buscar para el peso. */
    private Double getValorPeso(Scanner sc) {
        System.out.printf("El peso debe ser mayor o igual a: ");
        return Double.valueOf(sc.nextDouble());
    }

}
