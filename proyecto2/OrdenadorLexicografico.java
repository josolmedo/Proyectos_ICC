public class OrdenadorLexicografico {

    // Método público para ordenar
    public static void mergeSort(String[] arreglo) {
        if (arreglo.length > 1) {
            int mitad = arreglo.length / 2;

            // Dividir arreglo en dos mitades
            String[] izquierda = new String[mitad];
            String[] derecha = new String[arreglo.length - mitad];

            // Copiar elementos
            System.arraycopy(arreglo, 0, izquierda, 0, mitad);
            System.arraycopy(arreglo, mitad, derecha, 0, arreglo.length - mitad);

            // Recursión
            mergeSort(izquierda);
            mergeSort(derecha);

            // Combinar (merge)
            merge(arreglo, izquierda, derecha);
        }
    }

    // Método para combinar dos mitades ordenadas
    private static void merge(String[] resultado, String[] izquierda, String[] derecha) {
        int i = 0, j = 0, k = 0;

        while (i < izquierda.length && j < derecha.length) {
            if (izquierda[i].compareTo(derecha[j]) <= 0) {
                resultado[k++] = izquierda[i++];
            } else {
                resultado[k++] = derecha[j++];
            }
        }

        // Copiar lo que sobra
        while (i < izquierda.length) {
            resultado[k++] = izquierda[i++];
        }
        while (j < derecha.length) {
            resultado[k++] = derecha[j++];
        }
    }

    // Método principal para probar
    public static void main(String[] args) {
        String[] palabras = {
            "Hombres necios que acusáis",
             "    a la mujer sin razón,",
              "sin ver que sois la ocasión",
               "    de lo mismo que culpáis.",
               "                             ",
                "Si con ansia sin igual",
                "    si las incitáis al mal?",
                "                           ",
                "Combatís su resistencia",
                "    y luego con gravedad",
                "decís que fue liviandad",
                "    lo que hizo la diligencia.",
                "                            ",
                "Parecer quiere el denuedo",
                "    de vuestro parecer loco",
                "al niño que pone el coco",
                "    y luego le tiene miedo.",
                "                             ",
                "Queréis con presunción necia",
                "    hallar a la que buscáis,",
                "para pretendida, Tais,",
                "    y en la posesión, Lucrecia.",
                "                               ",
                "¿Qué humor puede ser más raro",
                "    que el que, falto de consejo,",
                "él mismo empaña el espejo",
                "    y siente que no esté claro?"};

        System.out.println("Antes de ordenar:");
        for (String s : palabras) {
            System.out.println(s);
        }

        mergeSort(palabras);

        System.out.println("\nDespués de ordenar:");
        for (String s : palabras) {
            System.out.println(s);
        }
    }
}











/***

public class OrdenadorLexicografico{
	public static void main (String [] args){
		String cadena1 ="Combatís su resistencia";
		String cadena2 ="al niño que pone el coco";

		int comparador = cadena1.compareTo(cadena2);
		System.out.println(comparador);

	}
}***/