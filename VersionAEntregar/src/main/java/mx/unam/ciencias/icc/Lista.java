package mx.unam.ciencias.icc;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Clase para listas genéricas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas implementan la interfaz {@link Iterable}, y por lo tanto se
 * pueden recorrer usando la estructura de control <em>for-each</em>. Las listas
 * no aceptan a <code>null</code> como elemento.</p>
 *
 * @param <T> El tipo de los elementos de la lista.
 */
public class Lista<T> implements Iterable<T> {

    /* Clase interna privada para nodos. */
    private class Nodo {
        /* El elemento del nodo. */
        private T elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(T elemento) {
            this.elemento = elemento;
        }
    }

    /* Clase interna privada para iteradores. */
    private class Iterador implements IteradorLista<T> {
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nuevo iterador. */
        private Iterador() {
            siguiente = cabeza;
            anterior = null;
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            return siguiente != null;
        }

        /* Nos da el elemento siguiente. */
        @Override public T next() {
            if(siguiente == null){
                throw new NoSuchElementException();
            }
            anterior = siguiente;
            siguiente = siguiente.siguiente;
            return anterior.elemento;
        }

        /* Nos dice si hay un elemento anterior. */
        @Override public boolean hasPrevious() {
            return anterior != null;
        }

        /* Nos da el elemento anterior. */
        @Override public T previous() {
            if(anterior == null){
                throw new NoSuchElementException();
            }
            siguiente = anterior;
            anterior = anterior.anterior;
            return siguiente.elemento;
        }

        /* Mueve el iterador al inicio de la lista. */
        @Override public void start() {
            anterior = null;
            siguiente = cabeza;

        }

        /* Mueve el iterador al final de la lista. */
        @Override public void end() {
            siguiente = null;
            anterior = rabo;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        return longitud;
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean esVacia() {
        return longitud == 0;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) throws IllegalArgumentException{
        if (elemento == null){
            throw new IllegalArgumentException("El estudiante a ingresar es nulo");
        }
        Nodo n = new Nodo(elemento);
        if (longitud == 0){
            cabeza=rabo=n;
            longitud = longitud + 1;
        }
        else{
            rabo.siguiente = n;
            n.anterior = rabo;
            rabo = n;
            longitud = longitud + 1;
        }
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        if (elemento == null){
            throw new IllegalArgumentException("El estudiante a ingresar es nulo"); 
        }
        Nodo n = new Nodo(elemento);
        if (longitud == 0){
            cabeza=rabo=n;
            longitud = longitud + 1;
        }
        else{
            n.anterior = null;
            n.siguiente = cabeza;
            cabeza.anterior = n;
            cabeza = n;
            longitud = longitud + 1;
        }
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void inserta(int i, T elemento) throws IllegalArgumentException {
        if(elemento == null){
            throw new IllegalArgumentException("El registro que quieres ingresar está vacío");            
        }
        if(i <= 0){
            agregaInicio(elemento);
        }
        else if(i >= longitud){
            agregaFinal(elemento);
        }else{            
            // Creamos un iterador para iterar la lista
            Iterador iterador = new Iterador();
            int posicion = 0;
            // Avanzamos hasta el índice           
            while (posicion < i) {
                T actual = iterador.next();
                posicion++;
            }
            
            Nodo nuevo = new Nodo(elemento);
            nuevo.anterior = iterador.anterior;
            nuevo.siguiente = iterador.siguiente;
            iterador.anterior.siguiente = nuevo;
            iterador.siguiente.anterior = nuevo;
            longitud++;
        }
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(T elemento) {
        Nodo iterable = cabeza; 
        while (iterable != null) { 
            if (iterable.elemento.equals(elemento)){ 
                if (iterable==cabeza) { 
                    eliminaPrimero(); 
                } else if (iterable==rabo) { 
                    eliminaUltimo(); 
                } else {
                   iterable.anterior.siguiente = iterable.siguiente; 
                   iterable.siguiente.anterior = iterable.anterior;  
          
                   longitud--;
               }       
                return; 
            }
            iterable=iterable.siguiente;
        }
        
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() throws NoSuchElementException{
       if(longitud == 0){
            throw new NoSuchElementException("No hay registros");
        }
        Nodo n = cabeza;
        if(cabeza == rabo){
            cabeza = rabo = null;            
        } else {
            cabeza.siguiente.anterior = null;
            cabeza = cabeza.siguiente;          
        }
        longitud = longitud - 1;
        return n.elemento;
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() throws NoSuchElementException {
        if(longitud == 0){
            throw new NoSuchElementException("No hay registros");
        }
        Nodo n = rabo;
        if(cabeza == rabo){
            cabeza = rabo = null;            
        } else {
            rabo.anterior.siguiente = null;
            rabo = rabo.anterior;            
        }
        longitud = longitud - 1;
        return n.elemento;
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <code>true</code> si <code>elemento</code> está en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean contiene(T elemento) {        
        for(T e: this){
            if (e.equals(elemento)){
                return true;
            }
        }
        return false;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista<T> reversa() {
        Lista<T> alreves = new Lista<T>();
        for(T e: this){
            alreves.agregaInicio(e);
        }
        return alreves;
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista<T> copia() {
        Lista<T> copiona = new Lista<T>();
        for(T e: this){
            copiona.agregaFinal(e);
        }
        return copiona;
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    public void limpia() {
        cabeza = rabo = null;
        longitud = 0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() {
        if(longitud == 0){
            throw new NoSuchElementException("No hay registros");
        }
        return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() throws NoSuchElementException{
        if(longitud == 0){
            throw new NoSuchElementException("No hay registros");
        }
        return rabo.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *         igual que el número de elementos en la lista.
     */
    public T get(int i) throws ExcepcionIndiceInvalido{
        if (i < 0 || i >= longitud){
            throw new ExcepcionIndiceInvalido("Índice inválido");
        } 
        int contador = 0;
        for (T e: this){
            if(contador == i){                
                return e;
            }
            contador++;
        }
        // Esta línea nunca debería alcanzarse, pero es necesaria para compilar.
        throw new ExcepcionIndiceInvalido("Índice inválido");
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
        int contador = 0; 
        for(T e: this){
            if(e.equals(elemento)){
                return contador;
            }
            contador++;
        }
        return -1;
    }

    public void cambia(int i, T elemento){
        elimina(elemento);
        inserta(i, elemento);
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
        if (esVacia()){
           return "[]";
        }
        String s = "[";
        for(T e: this){
            s += String.format("%s", e);
            if(e != getUltimo()){
                s+=", ";
            }
        }
        s+="]";
        return s;

       /* if (esVacia()){
           return "[]";
        }
        String s = "[";
        Nodo iterador = cabeza;
        while(iterador!= null){
            s += String.format("%s", iterador.elemento);
            if(iterador.siguiente != null){
                s+=", ";
            }
            iterador=iterador.siguiente;
        }
        s+="]";
        return s;*/
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <code>true</code> si la lista es igual al objeto recibido;
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>)objeto;
        if(lista==null){
            return false;
        }
        if(this.longitud!=lista.longitud){
            return false;
        }
        Nodo nodo1 = this.cabeza;
        Nodo nodo2 = lista.cabeza;
        while (nodo1 != null && nodo2 != null) {
            if (!nodo1.elemento.equals(nodo2.elemento)){
                return false;
            }
            nodo1 = nodo1.siguiente; 
            nodo2 = nodo2.siguiente;
        }
        return true;    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }
}
