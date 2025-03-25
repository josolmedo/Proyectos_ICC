package mx.unam.ciencias.icc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Clase abstracta para bases de datos. Provee métodos para agregar y eliminar
 * registros, y para guardarse y cargarse de una entrada y salida dados. Además,
 * puede hacer búsquedas con valores arbitrarios sobre los campos de los
 * registros.
 *
 * Las clases que extiendan a BaseDeDatos deben implementar el método {@link
 * #creaRegistro}, que crea un registro en blanco.
 */
public abstract class BaseDeDatos {

    /* Lista de registros en la base de datos. */
    private Lista registros;

    /**
     * Constructor único.
     */
    public BaseDeDatos() {
        registros=new Lista();
    }

    /**
     * Regresa el número de registros en la base de datos.
     * @return el número de registros en la base de datos.
     */
    public int getNumRegistros() {
        return registros.getLongitud();
    }

    /**
     * Regresa una lista con los registros en la base de datos. Modificar esta
     * lista no cambia a la información en la base de datos.
     * @return una lista con los registros en la base de datos.
     */
    public Lista getRegistros() {
        Lista copia=new Lista();
        int longitud=registros.getLongitud();
        for(int i=0; i<longitud; i++){
            copia.agregaFinal(registros.get(i));
        }
        return copia;       
    }

    /**
     * Agrega el registro recibido a la base de datos.
     * @param registro el registro que hay que agregar a la base de datos.
     */
    public void agregaRegistro(Registro registro) {
        registros.agregaFinal(registro);
    }

    /**
     * Elimina el registro recibido de la base de datos.
     * @param registro el registro que hay que eliminar de la base de datos.
     */
    public void eliminaRegistro(Registro registro) {
        registros.elimina(registro);
    }

    /**
     * Limpia la base de datos.
     */
    public void limpia() {
    registros.limpia();
    //registros=new Lista();
    }

    /**
     * Guarda todos los registros en la base de datos en la salida recibida.
     * @param out la salida donde hay que guardar los registos.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    public void guarda(BufferedWriter out) throws IOException {
        Lista.Nodo n = registros.getCabeza();
        while(n != null){
            Registro r = (Registro)n.get();
            out.write(r.seria());
            n=n.getSiguiente();
        }
    }

    /**
     * Carga los registros de la entrada recibida en la base de datos. Si antes
     * de llamar el método había registros en la base de datos, estos son
     * eliminados.
     * @param in la entrada de donde hay que cargar los registos.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    public void carga(BufferedReader in) throws IOException {
        limpia();
        String linea;
        while ((linea = in.readLine()) != null) {
            linea = linea.trim();
            if (linea.isEmpty()) {
                break; // Ignora líneas vacías
            }
            try {
                Registro registro = creaRegistro();
                registro.deseria(linea);
                agregaRegistro(registro);
            } catch (ExcepcionLineaInvalida e) {
                throw new IOException();
            }
        }
    }

    /**
     * Busca registros por un campo específico.
     * @param campo el campo del registro por el cuál buscar.
     * @param valor el valor a buscar.
     * @return una lista con los registros tales que casan el campo especificado
     *         con el valor dado.
     * @throws IllegalArgumentException si el campo no es de la enumeración
     *         correcta.
     */
    public Lista buscaRegistros(Enum campo, Object valor) {
        Lista resultados = new Lista();
        int n = registros.getLongitud();
        for (int i = 0; i < n; i++) {
            Registro r = (Registro) registros.get(i);
            if (r.casa(campo, valor))
                resultados.agregaFinal(r);
        }
        return resultados;
    }

    /**
     * Crea un registro en blanco.
     * @return un registro en blanco.
     */
    public abstract Registro creaRegistro();
}
