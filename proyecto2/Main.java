public class Main{
	public static void main(String[] args){

        Lista<String> lista = new Lista<String>();

        String[] lineas = {
            "Hombres necios que acusáis",
            "    a la mujer sin razón,",
            "sin ver que sois la ocasión",
            "    de lo mismo que culpáis.",
            "                             ",  // línea en blanco
            "Si con ansia sin igual",
            "    solicitáis su desdén,",
            "¿por qué queréis que obren bien",
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

        for (String linea: lineas){
            lista.agregaFinal(linea);
        }

        System.out.println("\nAntes de ordenar: \n");
        for (int contador = 0; contador<lista.getLongitud(); contador++) {
        	String registro = lista.get(contador);
            System.out.println(registro);
        }

        MergeSort.mergeSort(lista);

        System.out.println("\nDespués de ordenar: \n");
        for (int contador = 0; contador<lista.getLongitud(); contador++) {
            String registro = lista.get(contador);
            System.out.println(registro);
        }
        System.out.print("////////////////////////////////////////////////////////////////////////////////////////////////////\n");


        MergeSort.mergeSortInvertido(lista);

        System.out.println("\nOrdenamiento inverso: \n");
        for (int contador = 0; contador<lista.getLongitud(); contador++) {
            String registro2 = lista.get(contador);
            System.out.println(registro2);
        }
        System.out.print("////////////////////////////////////////////////////////////////////////////////////////////////////\n");
    }
}