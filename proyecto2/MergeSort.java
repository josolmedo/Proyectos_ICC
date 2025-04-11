public class MergeSort{

	public static void mergeSort(String[] arregloDeCadenas){

		int longitudOriginal = arregloDeCadenas.length; //Será util tener la longitud del arreglo de entrada
		
		if(longitudOriginal < 2){ //Si la longitud es de 1 ó 0, entonces ya está ordenado
			return; //Terminamos la pila de ejecución
		}

		int indiceDeEnmedio = longitudOriginal / 2; //Si el arreglo es de longitud impar truncará el entero
		String[] mitadIzquierda = new String[indiceDeEnmedio]; //El arreglo tendrá la longitud de la mitad de la longitud original
		String[] mitadDerecha = new String[longitudOriginal - indiceDeEnmedio]; //En caso de que la longitudOriginal sea impar

		/*Procedemos a rellenar el arreglo mitadIzquierda*/
		for(int contador = 0; contador < indiceDeEnmedio; contador++) {
			mitadIzquierda[contador] = arregloDeCadenas[contador];
		}

		/*Procedemos a rellenar el arreglo mitadDerecha*/
		for(int contador = indiceDeEnmedio; contador < longitudOriginal; contador++){
			mitadDerecha[contador - indiceDeEnmedio] = arregloDeCadenas[contador];
		}

		mergeSort(mitadIzquierda);
		mergeSort(mitadDerecha);

		merge(arregloDeCadenas, mitadIzquierda, mitadDerecha);
	}

	public static void merge(String[] arregloDeCadenas, String[] mitadIzquierda, String[] mitadDerecha){
		int longitudMitadIzquierda = mitadIzquierda.length;
		int longitudMitadDerecha = mitadDerecha.length;

		int i = 0, j= 0, k = 0;
		String cadenaIzquierda;
		String cadenaDerecha;

		while (i < longitudMitadIzquierda && j < longitudMitadDerecha) {
			cadenaIzquierda = mitadIzquierda[i].trim();
			cadenaDerecha = mitadDerecha[j].trim();
			cadenaIzquierda = cadenaIzquierda.replaceAll("^\\p{L}\\p{N}", "");
			cadenaDerecha = cadenaDerecha.replaceAll("^\\p{L}\\p{N}", "");

			cadenaIzquierda = cadenaIzquierda.replaceAll("\\?", "");
			cadenaDerecha = cadenaDerecha.replaceAll("\\?", "");

			cadenaIzquierda = cadenaIzquierda.replaceAll("\\¡", "");
			cadenaDerecha = cadenaDerecha.replaceAll("\\¡", "");

			cadenaIzquierda = cadenaIzquierda.replaceAll("\\¿", "");
			cadenaDerecha = cadenaDerecha.replaceAll("\\¿", "");

			cadenaIzquierda = cadenaIzquierda.replaceAll(" ", "");
			cadenaDerecha = cadenaDerecha.replaceAll(" ", "");

			cadenaIzquierda = cadenaIzquierda.replaceAll("\\%", "");
			cadenaDerecha = cadenaDerecha.replaceAll("\\%", "");

			cadenaIzquierda = cadenaIzquierda.replaceAll("\\,", "");
			cadenaDerecha = cadenaDerecha.replaceAll("\\,", "");

			cadenaIzquierda = cadenaIzquierda.replaceAll("\\.", "");
			cadenaDerecha = cadenaDerecha.replaceAll("\\.", "");





			//cadenaIzquierda = mitadIzquierda[i].replaceAll("[^a-zA-Z0-9]", "");
			//cadenaDerecha = cadenaDerecha.replaceAll("[^a-zA-Z0-9]", "");
			cadenaIzquierda = cadenaIzquierda.replaceAll("á", "a");
			cadenaIzquierda = cadenaIzquierda.replaceAll("Á", "a");
			cadenaIzquierda = cadenaIzquierda.replaceAll("é", "e");
        	cadenaIzquierda = cadenaIzquierda.replaceAll("É", "e");
        	cadenaIzquierda = cadenaIzquierda.replaceAll("í", "i");
        	cadenaIzquierda = cadenaIzquierda.replaceAll("Í", "i");
        	cadenaIzquierda = cadenaIzquierda.replaceAll("ó", "o");
       		cadenaIzquierda = cadenaIzquierda.replaceAll("Ó", "o");
        	cadenaIzquierda = cadenaIzquierda.replaceAll("ú", "u");
        	cadenaIzquierda = cadenaIzquierda.replaceAll("Ú", "u");
        	cadenaIzquierda = cadenaIzquierda.replaceAll("ü", "u");
        	cadenaIzquierda = cadenaIzquierda.replaceAll("Ü", "u");

        	cadenaDerecha = cadenaDerecha.replaceAll("á", "a");
			cadenaDerecha = cadenaDerecha.replaceAll("Á", "a");
			cadenaDerecha = cadenaDerecha.replaceAll("é", "e");
        	cadenaDerecha = cadenaDerecha.replaceAll("É", "e");
        	cadenaDerecha = cadenaDerecha.replaceAll("í", "i");
        	cadenaDerecha = cadenaDerecha.replaceAll("Í", "i");
        	cadenaDerecha = cadenaDerecha.replaceAll("ó", "o");
       		cadenaDerecha = cadenaDerecha.replaceAll("Ó", "o");
        	cadenaDerecha = cadenaDerecha.replaceAll("ú", "u");
        	cadenaDerecha = cadenaDerecha.replaceAll("Ú", "u");
        	cadenaDerecha = cadenaDerecha.replaceAll("ü", "u");
        	cadenaDerecha = cadenaDerecha.replaceAll("Ü", "u");

        	cadenaIzquierda = cadenaIzquierda.toLowerCase();
        	cadenaDerecha = cadenaDerecha.toLowerCase();




			if (cadenaIzquierda.compareToIgnoreCase(cadenaDerecha) <= 0){
				arregloDeCadenas[k] = mitadIzquierda[i]; //Asignamos como primer elemento del arreglo 
				i++;
			}
			else {
				arregloDeCadenas[k] = mitadDerecha[j];
				j++;
			}
			k++;
		}
		while (i < longitudMitadIzquierda){ // Ciclo para iterar en el arreglo izquierdo o derecho faltante
			arregloDeCadenas[k] = mitadIzquierda[i];
			i++;
			k++;
		}
		while (j < longitudMitadDerecha){ // Ciclo para iterar en el arreglo izquierdo o derecho faltante
			arregloDeCadenas[k] = mitadDerecha[j];
			j++;
			k++;
		}

	}
	
}