public class OrdenadorLexicografico {

    public static void main(String[] args) {
        // Texto de entrada: cada línea tal como está escrita
        String[] lines = {
            "Hombres necios que acusáis",
            "    a la mujer sin razón,",
            "sin ver que sois la ocasión",
            "    de lo mismo que culpáis.",
            "                             ",  // línea en blanco
            "Si con ansia sin igual",
            "    si las incitáis al mal?",
            "                           ",  // línea en blanco
            "Combatís su resistencia",
            "    y luego con gravedad",
            "decís que fue liviandad",
            "    lo que hizo la diligencia.",
            "                            ",  // línea en blanco
            "Parecer quiere el denuedo",
            "    de vuestro parecer loco",
            "al niño que pone el coco",
            "    y luego le tiene miedo.",
            "                              ",  // línea en blanco
            "Queréis con presunción necia",
            "    hallar a la que buscáis,",
            "para pretendida, Tais,",
            "    y en la posesión, Lucrecia.",
            "                                ",  // línea en blanco
            "¿Qué humor puede ser más raro",
            "    que el que, falto de consejo,",
            "él mismo empaña el espejo",
            "    y siente que no esté claro?"
        };

        // 1. Filtrar las líneas vacías (o que solo tienen espacios)
        int count = 0;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].trim().length() > 0) {
                count++;
            }
        }
        String[] filtered = new String[count];
        int index = 0;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].trim().length() > 0) {
                filtered[index++] = lines[i];
            }
        }

        // 2. Ordenar el arreglo filtrado usando nuestro Merge Sort implementado de forma manual
        mergeSort(filtered, 0, filtered.length - 1);

        System.out.println("\nAntes de ordenar:\n");
        for (String linea: lines){
        	System.out.println(linea);

        }

        // 3. Imprimir el resultado final, conservando el formato original de cada línea.
        System.out.println("Después de ordenar:\n");
        for (int i = 0; i < filtered.length; i++) {
            System.out.println(filtered[i]);
        }
    }

    /**
     * Función que transforma la línea para la comparación.
     * Realiza lo siguiente:
     *  - Quita espacios (incluso internos) usando replaceAll()
     *  - Sustituye manualmente las vocales acentuadas por las correspondientes sin acento
     *  - Elimina cualquier carácter no alfanumérico
     *  - Convierte a minúsculas (para comparar sin distinguir mayúsculas/minúsculas)
     */
    public static String transform(String s) {
        // Quitar espacios al inicio, final e internos
        String result = s.trim().replaceAll("\\s+", "");
        // Reemplazar vocales acentuadas por las equivalentes sin acento
        result = result.replace("á", "a");
        result = result.replace("Á", "a");
        result = result.replace("é", "e");
        result = result.replace("É", "e");
        result = result.replace("í", "i");
        result = result.replace("Í", "i");
        result = result.replace("ó", "o");
        result = result.replace("Ó", "o");
        result = result.replace("ú", "u");
        result = result.replace("Ú", "u");
        result = result.replace("ü", "u");
        result = result.replace("Ü", "u");
        // Eliminar cualquier carácter que no sea letra ni dígito
        result = result.replaceAll("[^a-zA-Z0-9]", "");
        return result.toLowerCase();
    }

    // Implementación del Merge Sort sobre un arreglo de String
    public static void mergeSort(String[] a, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(a, left, mid);
            mergeSort(a, mid + 1, right);
            merge(a, left, mid, right);
        }
    }

    // Función merge para combinar dos subarreglos ordenados
    public static void merge(String[] a, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        String[] L = new String[n1];
        String[] R = new String[n2];
        int i, j, k;
        for (i = 0; i < n1; i++) {
            L[i] = a[left + i];
        }
        for (j = 0; j < n2; j++) {
            R[j] = a[mid + 1 + j];
        }
        i = 0;
        j = 0;
        k = left;
        // Fusionar los dos subarreglos comparando con la versión transformada
        while (i < n1 && j < n2) {
            if (transform(L[i]).compareTo(transform(R[j])) <= 0) {
                a[k] = L[i];
                i++;
            } else {
                a[k] = R[j];
                j++;
            }
            k++;
        }
        // Copiar los elementos restantes, si los hay, de L y de R
        while (i < n1) {
            a[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            a[k] = R[j];
            j++;
            k++;
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