package mx.unam.ciencias.icc;

/**
 * Enumeración para los campos de un {@link Perro}.
 */
public enum CampoPerro {

    /** El nombre del Perro. */
    NOMBRE,
    /** La raza del Perro. */
    RAZA,
    /** La edad del Perro. */
    EDAD,
    /** La estatura del Perro. */
    ESTATURA,
    /** El peso del Perro **/
    PESO;

    /**
     * Regresa una representación en cadena del campo para ser usada en
     * interfaces gráficas.
     * @return una representación en cadena del campo.
     */
    @Override public String toString() {
        switch(this) {
        case NOMBRE:
            return "Nombre";
        case RAZA:
            return "Raza";
        case EDAD:
            return "Edad";
        case ESTATURA:
            return "Estatura";
        case PESO:
            return "Peso";
        default:
            return "";
        }
    }
}
