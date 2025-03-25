package mx.unam.ciencias.icc;

import java.util.NoSuchElementException;

/**
 * <p>Clase para listas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas son iterables utilizando sus nodos. Las listas no aceptan a
 * <code>null</code> como elemento.</p>
 */
public class Lista {

    /**
     * Clase interna para nodos.
     */
    public class Nodo {

        /* El elemento del nodo. */
        private Object elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(Object elemento) {
        this.elemento=elemento;
        }

        /**
         * Regresa el nodo anterior del nodo.
         * @return el nodo anterior del nodo.
         */
        public Nodo getAnterior() {
            return anterior;
        }

        /**
         * Regresa el nodo siguiente del nodo.
         * @return el nodo siguiente del nodo.
         */
        public Nodo getSiguiente() {
            return siguiente;
        }

        /**
         * Regresa el elemento del nodo.
         * @return el elemento del nodo.
         */
        public Object get() {
            return elemento;
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
        return longitud==0;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(Object elemento) throws IllegalArgumentException{
     if(elemento==null){ //Si el elemento es null (vacío)
         throw new IllegalArgumentException();
    }
    if(esVacia()){//Si la lista es vacìa

        Nodo n=new Nodo(elemento); //Creamos un nuevo objeto nodo 
        cabeza=n;
        rabo=n;
        longitud=longitud+1;
    }
    else{//Osea si la lista no es vacìa
        Nodo n = new Nodo(elemento);
        rabo.siguiente = n; // Enlaza el último nodo al nuevo
        n.anterior = rabo;  // El nuevo nodo apunta al antiguo rabo
        rabo = n;           // Actualiza rabo al nuevo nodo
        longitud++;
    }
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(Object elemento) throws IllegalArgumentException {
     if(elemento==null){
         throw new IllegalArgumentException();
     }
     if(esVacia()){//si la lista es vacìa
         Nodo n=new Nodo(elemento); //Creamos un nuevo objeto nodo
         cabeza=n;
         rabo=n;
         longitud=longitud+1;
     }
     else{//Osea si la lista no es vacìa
         Nodo n=new Nodo(elemento);
         cabeza.anterior=n;
         n.siguiente=cabeza;
         cabeza=n;
         longitud=longitud+1;
         
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

    public void auxiliarInserta(int i, Nodo iterable, Nodo insertar){
    if(i==0){
        insertar.anterior=iterable.anterior;
        insertar.siguiente=iterable;
        iterable.anterior.siguiente=insertar;
        iterable.anterior=insertar;
        longitud++;
        return;
    }
    auxiliarInserta(i-1,iterable.siguiente,insertar);
    }

    
    public void inserta(int i, Object elemento) throws IllegalArgumentException {
         if(elemento==null){
         throw new IllegalArgumentException();
    }
    if(i<=0){
        agregaInicio(elemento);
        return;
        
    }
    else if(i>=longitud){
        agregaFinal(elemento);
        return;
    }
    else{
        Nodo insertar=new Nodo(elemento);
        auxiliarInserta(i-1, cabeza.siguiente,insertar);
    }
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */

    public void auxiliarElimina(Object elemento, Nodo iterable) {
    if (iterable == null) { 
        return;
    }
    if (iterable.elemento.equals(elemento)) {
        iterable.anterior.siguiente = iterable.siguiente;
        if (iterable.siguiente != null) {
        iterable.siguiente.anterior = iterable.anterior; 
        } else { 
        rabo = iterable.anterior; 
        }
        longitud--;
        return;
    }
    auxiliarElimina(elemento, iterable.siguiente); 
    }



    
    public void elimina(Object elemento) {
        if (esVacia() || elemento == null) {
        return;
    }
    if (cabeza.elemento.equals(elemento)) { 
        eliminaPrimero();
        return;
    }
    auxiliarElimina(elemento, cabeza); 
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public Object eliminaPrimero() {
    if (longitud == 0) {
        throw new NoSuchElementException("La lista está vacía");
    }
    Object elemento = cabeza.elemento;
    if (cabeza == rabo) {
        cabeza = null;
        rabo = null;
    } else {
        cabeza = cabeza.siguiente;
        cabeza.anterior = null;
    }
    longitud--;
    return elemento;
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public Object eliminaUltimo() {
        if(esVacia()){
        throw new NoSuchElementException("La lista està vacìa"); 
    }
    Object e=rabo.elemento;
    if(cabeza==rabo){
        cabeza=null; 
        rabo=null; 
    }
    else{
        rabo=rabo.anterior; 
        rabo.siguiente=null; 
    }
    longitud=longitud-1; 
    return e; 
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <code>true</code> si <code>elemento</code> está en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean auxiliarContiene(Object elemento, Nodo iterable){
    if(iterable==null){
        return false;
    }
    if(iterable.elemento.equals(elemento)){
        return true;
    }
    
    return auxiliarContiene(elemento, iterable.siguiente);
    }

    
    public boolean contiene(Object elemento) {
    return auxiliarContiene(elemento, cabeza);
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista auxiliarReversa(Lista alreves, Nodo iterable){
    if(iterable==null){
        return alreves;
    }
    alreves.agregaInicio(iterable.elemento);
    return auxiliarReversa(alreves, iterable.siguiente);
     }
    
    public Lista reversa() {
    Lista inversa=new Lista(); 
    return auxiliarReversa(inversa, cabeza); 
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista auxiliarCopia(Lista copia, Nodo iterable){
        if (iterable==null){
            return copia;
        }
        copia.agregaFinal(iterable.elemento);
        return auxiliarCopia(copia, iterable.siguiente);
     }
    
    public Lista copia() {
        Lista copia = new Lista();
        return auxiliarCopia(copia, cabeza); 
    }

   
    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    public void limpia() {
        cabeza=rabo=null;
        longitud=0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public Object getPrimero() {
        if(esVacia()){
            throw new NoSuchElementException("La lista está vacía");
        }
        return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public Object getUltimo() {

        if(esVacia()){
            throw new NoSuchElementException("La lista està vacìa");
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
    public Object get(int i) {
        if (i < 0 || i >= longitud) {
            throw new ExcepcionIndiceInvalido("Índice inválido: " + i);
        }
        Nodo nodoActual = cabeza;
        for (int contador = 0; contador < i; contador++) {
            nodoActual = nodoActual.siguiente;
        }
        return nodoActual.elemento;
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    
    public int auxiliarIndiceDe(Object elemento, Nodo iterable, int indice){
    if(iterable==null){
        return -1;
    }
    if(iterable.elemento.equals(elemento)){
        return indice;
    }
    return auxiliarIndiceDe(elemento,iterable.siguiente,indice+1);
    }
    
    public int indiceDe(Object elemento) {
        int indice=0;
    return auxiliarIndiceDe(elemento,cabeza,indice);
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    
    public String auxiliarToString(String s, Nodo iterable){
    if(iterable==null){
         s+="]";
         return s;
     }
     s += String.format("%s", iterable.elemento);
     if(iterable.siguiente!=null){
         s+=", ";
     }
     return auxiliarToString(s, iterable.siguiente);
     }
    @Override public String toString() {
        if (esVacia()){
            return "[]";
    }
    String s = "[";
    return auxiliarToString(s, cabeza); 
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <code>true</code> si la lista es igual al objeto recibido;
     *         <code>false</code> en otro caso.
     */
    public boolean auxiliarEquals(Lista lista, Nodo nodo1, Nodo nodo2){
    if(nodo1==null && nodo2==null){
        return true;
    }
    if (!nodo1.elemento.equals(nodo2.elemento)){
        return false; //Devuelve false
    }
    return auxiliarEquals(lista, nodo1.siguiente, nodo2.siguiente);
    }
    
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof Lista))
            return false;
        Lista lista = (Lista)objeto;
        if(lista==null){
        return false;
    }
        if(this.longitud!=lista.longitud){
        return false;
    }
    Nodo nodo1 = this.cabeza; 
        Nodo nodo2 = lista.cabeza;
        return auxiliarEquals(lista, nodo1, nodo2);
    }

    /**
     * Regresa el nodo cabeza de la lista.
     * @return el nodo cabeza de la lista.
     */
    public Nodo getCabeza() {
        return cabeza;
    }

    /**
     * Regresa el nodo rabo de la lista.
     * @return el nodo rabo de la lista.
     */
    public Nodo getRabo() {
        return rabo;
    }
}