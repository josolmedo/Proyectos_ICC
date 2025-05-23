package mx.unam.ciencias.icc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Práctica 7: Iteradores.
 */
public class Proyecto2 {

    /* Código de terminación por error de uso. */
    private static final int ERROR_USO = 1;

    /* Imprime en pantalla cómo debe usarse el programa y lo termina. */
    private static void uso() {

        
        
        
        
        
        
        
        
        
        
        System.out.println("Algo salió mal (probablemente pusiste banderas incorrectas o de más)"); 
        /**$ cat hombres.txt | java -jar target/proyecto2.jar
        $ java -jar target/proyecto2.jar < hombres.txt
        **/


        System.exit(ERROR_USO);
    }

    public static void main(String[] args) {
        if (args.length == 1){
            try {

            String registro;

            Lista<String> lista = new Lista<String>();
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(args[0])));
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
            

            //lista = lista.mergeSort((e1, e2) -> e1.compareToIgnoreCase(e2));

                        
            for (int contador = 0; contador<lista.getLongitud(); contador++) {
                registro = lista.get(contador);
                System.out.println(registro);
            }           

            

            } catch (IOException ioe) {
                System.err.printf("No pude sobreescribir el archivo \"%s\".\n",args[0]);
                System.exit(1);
            }
        }
        else if(args.length == 3){
            try{                
                if(!args[0].equals("-r")){
                    System.out.println("Banderas incorrectas, solo puedes ingresar '-r' o '-o' ");
                    System.exit(1);
                }
                else{
                    Aplicacion aplicacion = new Aplicacion(args[0], args[2]);
                    aplicacion.ejecuta2();

                }                
            } catch (IllegalArgumentException iae){
                uso();
            }


        }
        else{
            try {
                Aplicacion aplicacion = new Aplicacion(args[0], args[1]);
                aplicacion.ejecuta();
            } catch (IllegalArgumentException iae) {
                uso();
            }
        }
    }
}
