public class Main{
	public static void main(String[] args){

		int[] arregloDeNumerosAleatorios = GenerarArregloDeEnterosAleatorios.generar(20);
		System.out.println("\nAntes de ordenar: \n");
		ImprimirArreglo.imprimir(arregloDeNumerosAleatorios);

		System.out.println("\nDespues de ordenar: \n");
		MergeSort.mergeSort(arregloDeNumerosAleatorios);
		ImprimirArreglo.imprimir(arregloDeNumerosAleatorios);

		System.out.println("\n");



	}
}