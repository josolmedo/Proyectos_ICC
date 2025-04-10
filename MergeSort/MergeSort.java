public class MergeSort{

	public static void mergeSort(int[] arregloDeNumerosAleatorios){

		int longitudOriginal = arregloDeNumerosAleatorios.length; //Será util tener la longitud del arreglo de entrada
		
		if(longitudOriginal < 2){ //Si la longitud es de 1 ó 0, entonces ya está ordenado
			return; //Terminamos la pila de ejecución
		}

		int indiceDeEnmedio = longitudOriginal / 2; //Si el arreglo es de longitud impar truncará el entero
		int[] mitadIzquierda = new int[indiceDeEnmedio]; //El arreglo tendrá la longitud de la mitad de la longitud original
		int[] mitadDerecha = new int[longitudOriginal - indiceDeEnmedio]; //En caso de que la longitudOriginal sea impar

		/*Procedemos a rellenar el arreglo mitadIzquierda*/
		for(int contador = 0; contador < indiceDeEnmedio; contador++) {
			mitadIzquierda[contador] = arregloDeNumerosAleatorios[contador];
		}

		/*Procedemos a rellenar el arreglo mitadDerecha*/
		for(int contador = indiceDeEnmedio; contador < longitudOriginal; contador++){
			mitadDerecha[contador - indiceDeEnmedio] = arregloDeNumerosAleatorios[contador];
		}

		mergeSort(mitadIzquierda);
		mergeSort(mitadDerecha);

		merge(arregloDeNumerosAleatorios, mitadIzquierda, mitadDerecha);
	}

	public static void merge(int[] arregloDeNumerosAleatorios, int[] mitadIzquierda, int[] mitadDerecha){
		int longitudMitadIzquierda = mitadIzquierda.length;
		int longitudMitadDerecha = mitadDerecha.length;

		int i = 0, j= 0, k = 0;

		while (i < longitudMitadIzquierda && j < longitudMitadDerecha) {
			if (mitadIzquierda[i] <= mitadDerecha[j]){
				arregloDeNumerosAleatorios[k] = mitadIzquierda[i]; //Asignamos como primer elemento del arreglo 
				i++;
			}
			else {
				arregloDeNumerosAleatorios[k] = mitadDerecha[j];
				j++;
			}
			k++;
		}
		while (i < longitudMitadIzquierda){ // Ciclo para iterar en el arreglo izquierdo o derecho faltante
			arregloDeNumerosAleatorios[k] = mitadIzquierda[i];
			i++;
			k++;
		}
		while (j < longitudMitadDerecha){ // Ciclo para iterar en el arreglo izquierdo o derecho faltante
			arregloDeNumerosAleatorios[k] = mitadDerecha[j];
			j++;
			k++;
		}

	}
	
}