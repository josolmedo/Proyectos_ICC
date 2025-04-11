public class MergeSort{

	public static void mergeSort(Lista<String> lista){

		int longitudOriginal = lista.getLongitud(); //Será util tener la longitud del arreglo de entrada
		
		if(longitudOriginal < 2){ //Si la longitud es de 1 ó 0, entonces ya está ordenado
			return; //Terminamos la pila de ejecución
		}

		int indiceDeEnmedio = longitudOriginal / 2; //Si el arreglo es de longitud impar truncará el entero
		int i = 0;
		int j = indiceDeEnmedio;

		Lista<String> mitadIzquierda = new Lista<String>();
		Lista<String> mitadDerecha = new Lista<String>();
		String cosita;
		String cosota;

		while (i < indiceDeEnmedio){
			cosita = lista.get(i);
			mitadIzquierda.agregaFinal(cosita);
			i++;
		}


		while (j < longitudOriginal){ //En caso de que la longitudOriginal sea impar
			cosota = lista.get(j);
			mitadDerecha.agregaFinal(cosota);
			j++;
		}

		mergeSort(mitadIzquierda);
		mergeSort(mitadDerecha);

		merge(lista, mitadIzquierda, mitadDerecha);
	}

	public static void merge(Lista<String> lista, Lista<String> mitadIzquierda, Lista<String> mitadDerecha){
		int longitudMitadIzquierda = mitadIzquierda.getLongitud();
		int longitudMitadDerecha = mitadDerecha.getLongitud();

		int i = 0, j= 0, k = 0;
		String cadenaIzquierda;
		String cadenaDerecha;

		while (i < longitudMitadIzquierda && j < longitudMitadDerecha) {

			cadenaIzquierda = LimpiadorDeCadenas.limpiar(mitadIzquierda.get(i));
			cadenaDerecha = LimpiadorDeCadenas.limpiar(mitadDerecha.get(j));

			if (cadenaIzquierda.compareToIgnoreCase(cadenaDerecha) <= 0){
				lista.cambia(k, mitadIzquierda.get(i)); 
				i++;
			}
			else {
				lista.cambia(k, mitadDerecha.get(j)); 
				j++;
			}
			k++;
		}
		while (i < longitudMitadIzquierda){ // Ciclo para iterar en el arreglo izquierdo o derecho faltante
			lista.cambia(k, mitadIzquierda.get(i));
			i++;
			k++;
		}
		while (j < longitudMitadDerecha){ // Ciclo para iterar en el arreglo izquierdo o derecho faltante
			lista.cambia(k, mitadDerecha.get(j));
			j++;
			k++;
		}

	}


	public static void mergeSortInvertido(Lista<String> lista){

		int longitudOriginal = lista.getLongitud(); //Será util tener la longitud del arreglo de entrada
		
		if(longitudOriginal < 2){ //Si la longitud es de 1 ó 0, entonces ya está ordenado
			return; //Terminamos la pila de ejecución
		}

		int indiceDeEnmedio = longitudOriginal / 2; //Si el arreglo es de longitud impar truncará el entero
		int i = 0;
		int j = indiceDeEnmedio;

		Lista<String> mitadIzquierda = new Lista<String>();
		Lista<String> mitadDerecha = new Lista<String>();
		String cosita;
		String cosota;

		while (i < indiceDeEnmedio){
			cosita = lista.get(i);
			mitadIzquierda.agregaFinal(cosita);
			i++;
		}


		while (j < longitudOriginal){ //En caso de que la longitudOriginal sea impar
			cosota = lista.get(j);
			mitadDerecha.agregaFinal(cosota);
			j++;
		}

		mergeSortInvertido(mitadIzquierda);
		mergeSortInvertido(mitadDerecha);

		mergeInverso(lista, mitadIzquierda, mitadDerecha);
	}

	public static void mergeInverso(Lista<String> lista, Lista<String> mitadIzquierda, Lista<String> mitadDerecha){
		int longitudMitadIzquierda = mitadIzquierda.getLongitud();
		int longitudMitadDerecha = mitadDerecha.getLongitud();

		int i = 0, j= 0, k = 0;
		String cadenaIzquierda;
		String cadenaDerecha;

		while (i < longitudMitadIzquierda && j < longitudMitadDerecha) {
			
			cadenaIzquierda = LimpiadorDeCadenas.limpiar(mitadIzquierda.get(i));
			cadenaDerecha = LimpiadorDeCadenas.limpiar(mitadDerecha.get(j));

			if (cadenaIzquierda.compareToIgnoreCase(cadenaDerecha) >= 0){
				lista.cambia(k, mitadIzquierda.get(i)); 
				i++;
			}
			else {
				lista.cambia(k, mitadDerecha.get(j)); 
				j++;
			}
			k++;
		}
		while (i < longitudMitadIzquierda){ // Ciclo para iterar en el arreglo izquierdo o derecho faltante
			lista.cambia(k, mitadIzquierda.get(i));
			i++;
			k++;
		}
		while (j < longitudMitadDerecha){ // Ciclo para iterar en el arreglo izquierdo o derecho faltante
			lista.cambia(k, mitadDerecha.get(j));
			j++;
			k++;
		}

	}










	
}