package mx.unam.ciencias.icc;

/**
 * Clase para representar perros. Los perros tendràn nombre, raza, edad, peso, estatura. La clase implementa {@link Registro}, por lo que
 * puede seriarse en una línea de texto y deseriarse de una línea de
 * texto; además de determinar si sus campos casan valores arbitrarios.
 */
public class Perro implements Registro {

    /* Nombre del perro. */
    private String nombre;
    /* Raza del perro */
    private String raza;
    /* Edad del perro. */
    private int edad;
    /* Estatura del perro. */
    private double estatura;
    /* Peso del perro.*/
    private double peso;

    /**
     * Define el estado inicial de un perro.
     * @param nombre: Nombre del perro.
     * @param raza: Raza del perro.
     * @param edad: Edad del perro.
     * @param estatura: Estatura del perro.
     * @param peso: Peso del perro
     */
    public Perro(String nombre, String raza, int edad, double estatura, double peso) {
        this.nombre=nombre;
        this.raza=raza;
        this.edad=edad;
        this.estatura=estatura;
        this.peso=peso;
    }

    /**
     * Regresa el nombre del perro.
     * @return el nombre del perro.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el nombre del perro.
     * @param nombre el nuevo nombre del perro.
     */
    public void setNombre(String nombre) {
        this.nombre=nombre;
    }

    /**
     * Regresa la raza del perro.
     * @return la raza del perro.
     */
    public String getRaza() {
        return raza;
    }

    /**
     * Define la nueva raza del perro.
     * @param raza la nueva raza del perro.
     */
    public void setRaza(String raza) {
        this.raza=raza;
    }

    /**
     * Regresa la edad del perro.
     * @return la edad del perro.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Define la edad del perro.
     * @param edad la nueva edad del perro.
     */
    public void setEdad(int edad) {
        this.edad=edad;
    }

    /**
     * Regresa la estatura del perro.
     * @return la estatura del perro.
     */
    public double getEstatura() {
        return estatura;
    }

    /**
     * Define la estatura del perro.
     * @param estatura la nueva estatura del perro.
     */
    public void setEstatura(double estatura) {
	this.estatura=estatura;
    }

     /**
     * Regresa el peso del perro.
     * @return el peso del perro.
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Define el peso del perro.
     * @param peso el nuevo peso del perro.
     */
    public void setPeso(double peso) {
	this.peso=peso;
    }

    /**
     * Regresa una representación en cadena del perro.
     * @return una representación en cadena del perro.
     */
    @Override public String toString() {
    	String cadena = String.format("Nombre: %s\n" + "Raza: %s\n" + "Edad: %d\n" + "Estatura: %2.2f\n" + "Peso: %2.2f\n", nombre, raza, edad, estatura, peso);
	return cadena;
    }

    /**
     * Nos dice si el objeto recibido es un perro igual al que manda llamar
     * el método.
     * @param objeto el objeto con el que el perro se comparará.
     * @return <code>true</code> si el objeto recibido es un perro con las
     *         mismas propiedades que el objeto que manda llamar al método,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof Perro)){
            return false;
        }
        Perro perro = (Perro)objeto;
       	return this.nombre.equals(perro.nombre) && this.raza == perro.raza && this.edad == perro.edad && this.estatura == perro.estatura && this.peso == perro.peso;
    }

    /**
     * Regresa el perro seriado en una línea de texto. La línea de
     * texto que este método regresa debe ser aceptada por el método {@link
     * perro#deseria}.
     * @return la seriación del perro en una línea de texto.
     */
    @Override public String seria() {
    	String linea = String.format("%s\t%s\t%d\t%2.2f\t%2.2f\n",nombre,raza, edad, estatura, peso);
        return linea;
    }

    /**
     * Deseria una línea de texto en las propiedades del perro. La
     * seriación producida por el método {@link perro#seria} debe
     * ser aceptada por este método.
     * @param linea la línea a deseriar.
     * @throws ExcepcionLineaInvalida si la línea recibida es nula, vacía o no
     *         es una seriación válida de un perro.
     */
    @Override public void deseria(String linea) throws ExcepcionLineaInvalida{
        if (linea == null){
            throw new ExcepcionLineaInvalida();
        }
        String[] partes = linea.trim().split("\t");
        if (partes.length != 5){
            throw new ExcepcionLineaInvalida();
        }
        try {
            this.nombre = partes[0];
            this.raza = partes[1];
            this.edad = Integer.parseInt(partes[2]);
            this.estatura = Double.parseDouble(partes[3]);
            this.peso = Double.parseDouble(partes[4]);
        } catch (Exception e) {
            throw new ExcepcionLineaInvalida();
        }
    }

    /*catch (ExcepcionLineaInvalida()){
     public void deseria(String linea);
    }*/

    /**
     * Nos dice si el perro casa el valor dado en el campo especificado.
     * @param campo el campo que hay que casar.
     * @param valor el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si:
     *         <ul>
     *           <li><code>campo</code> es {@link CampoPerro#NOMBRE} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena del nombre del perro.</li>
     *           <li><code>campo</code> es {@link CampoPerro#RAZA} y
     *              <code>valor</code> es instancia de {@link String} y su
     *              subcadena de la raza del perro.</li>
     *           <li><code>campo</code> es {@link CampoPerro#EDAD} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor doble es menor o igual a la edad del perro.</li>
     *           <li><code>campo</code> es {@link CampoPerro#ESTATURA} y
     *              <code>valor</code> es instancia de {@link Double} y su
     *              valor double es menor o igual a la estatura del perro.</li>
     *            <li><code>campo</code> es {@link CampoPerro#PESO} y
     *              <code>valor</code> es instancia de {@link Double} y su
     *              valor double es menor o igual a l peso del perro.</li>
     *         </ul>
     *         <code>false</code> en otro caso.
     * @throws IllegalArgumentException si el campo no es instancia de {@link
     *         CampoPerro}.
     */
    @Override public boolean casa(Enum campo, Object valor) {
	if (!(campo instanceof CampoPerro)){
	    throw new IllegalArgumentException("El campo debe ser CampoPerro");
    }
	CampoPerro c = (CampoPerro) campo;
	switch (c) {
        case NOMBRE:
            if (!(valor instanceof String)){
                return false;
            }
            String s = (String) valor;
            if (s.isEmpty()){
                return false;
            }
            return nombre.contains(s);

        case RAZA:
            if (!(valor instanceof String)){
                return false;
            }
            String p = (String) valor;
            if (p.isEmpty()){
                return false;
            }
            return raza.contains(p);

        case EDAD:
            return (valor instanceof Integer) && (edad >= (Integer) valor);

        case ESTATURA:
            return (valor instanceof Double) && (estatura >= (Double) valor);

        case PESO:
            return (valor instanceof Double) && (peso >= (Double) valor);

        default:
            throw new IllegalArgumentException("Campo no válido");
        }
    }
}
