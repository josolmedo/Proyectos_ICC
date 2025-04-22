package mx.unam.ciencias.icc;

/**
 * Práctica 7: Iteradores.
 */
public class Proyecto2 {

    /* Código de terminación por error de uso. */
    private static final int ERROR_USO = 1;

    /* Imprime en pantalla cómo debe usarse el programa y lo termina. */
    private static void uso() {
        System.out.println("Uso: java -jar practica5.jar [-g|-c] <archivo>"); 
        /**$ cat hombres.txt | java -jar target/proyecto2.jar
        $ java -jar target/proyecto2.jar < hombres.txt
        ES CON GUARDA PARA SOBRE ESCRIBIR (-o)
        CARGA serà usado para Reversa (-r)
        **/
        System.exit(ERROR_USO);
    }

    public static void main(String[] args) {
        if (args.length != 2)
            uso();

        try {
            Aplicacion aplicacion = new Aplicacion(args[0], args[1]);
            aplicacion.ejecuta();
        } catch (IllegalArgumentException iae) {
            uso();
        }
    }
}
