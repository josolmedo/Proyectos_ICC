package mx.unam.ciencias.icc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
//import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
//import java.util.InputMismatchException;
//import java.util.Scanner;

/**
 * Clase para aplicaciones de la base de datos de estudiantes.
 */
public class Aplicacion {

    /* Modo de la aplicación. */
    private enum Modo {
        /* Modo para guardar. */
        GUARDA(1),
        /* Modo para cargar. */
        REVERSA(2);
        

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
            case "-o": return GUARDA;
            case "-r": return REVERSA;
            default: throw new IllegalArgumentException(
                "Bandera inválida: " + bandera);
            }
        }
    }

    /* La base de datos. 
    private BaseDeDatosEstudiantes bdd;*/
    /* La ruta del archivo. */
    private String ruta;
    /* El modo de la aplicación. */
    private Modo modo;

    private Modo modo1;

    private Modo modo2;

    /**
     * Define el estado inicial de la aplicación.
     * @param bandera la bandera de modo.
     * @param ruta la ruta del archivo.
     * @throws IllegalArgumentException si la bandera no es "-r" o "-g".
     */
    public Aplicacion(String bandera, String ruta) {
        modo = Modo.getModo(bandera);
        this.ruta = ruta;        
    }

    

    /**
     * Ejecuta la aplicación.
     */
    public void ejecuta() {
        switch (modo) {
        case GUARDA:
            guarda();
            break;
        case REVERSA:
            reversa();
            break;
        }
    }

    public void ejecuta2() {
        switch (modo) {
        case REVERSA:
            guarda2();
            break;
        }
    }    



     /* Modo de guarda de la aplicación. */
    private void guarda2() {
        
        try {

            String registro;

            Lista<String> lista = new Lista<String>();
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(ruta)));
            String linea;
            while ((linea =in.readLine()) != null){
                if(linea.isEmpty()){
                    lista.agregaInicio(linea);
                }
                else{
                    lista.agregaFinal(linea);
                }
                
            }

            in.close();

           

            MergeSort.mergeSortInvertido(lista);

            

            
            


            

            BufferedWriter out =
                new BufferedWriter(
                    new OutputStreamWriter(
                        new FileOutputStream(ruta)));
            
            for (String nuevasLineas : lista) {
                out.write(nuevasLineas);
                out.newLine();
            }
            out.close();
            System.out.println("\nArchivo guardado exitosamente\n");

        } catch (IOException ioe) {
            System.err.printf("No pude sobreescribir el archivo \"%s\".\n",
                              ruta);
            System.exit(modo.getCodigo());
        }
    }


    /* Modo de guarda de la aplicación. */
    private void guarda() {
        /*Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        verificaSalida(sc);
        agregaEstudiantes(sc);
        sc.close();*/
        try {

            

            Lista<String> lista = new Lista<String>();
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(ruta)));
            String linea;
            while ((linea =in.readLine()) != null){
                if(linea.isEmpty()){
                    lista.agregaInicio(linea);
                }
                else{
                    lista.agregaFinal(linea);
                }
                
            }

            in.close();

            

            lista = lista.mergeSort((e1, e2) -> {
                // quitamos sólo la sangría inicial para comparar, pero NO modificamos e1/e2
                String t1 = LimpiadorDeCadenas.limpiar(e1);
                String t2 = LimpiadorDeCadenas.limpiar(e2);
                return t1.compareToIgnoreCase(t2);
            });

           

            
            BufferedWriter out =
                new BufferedWriter(
                    new OutputStreamWriter(
                        new FileOutputStream(ruta)));
            
            for (String nuevasLineas : lista) {
                out.write(nuevasLineas);
                out.newLine();
            }
            out.close();
            System.out.println("\nArchivo guardado exitosamente\n");

        } catch (IOException ioe) {
            System.err.printf("No pude sobreescribir el archivo \"%s\".\n",
                              ruta);
            System.exit(modo.getCodigo());
        }
    }

    
    /* Modo de reversa de la aplicación. */
    private void reversa() {
        try {

            String registro;

            Lista<String> lista = new Lista<String>();
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(ruta)));
            String linea;
            while ((linea =in.readLine()) != null){
                lista.agregaFinal(linea);
            }
            in.close();

           
            MergeSort.mergeSortInvertido(lista);

            
            
            for (int contador = 0; contador<lista.getLongitud(); contador++) {
                registro = lista.get(contador);
                System.out.println(registro);
            }

        } catch (IOException ioe) {
            System.err.printf("No pude leer el archivo \"%s\".\n",
                              ruta);
            System.exit(modo.getCodigo());
        }
        
    }
    
}
