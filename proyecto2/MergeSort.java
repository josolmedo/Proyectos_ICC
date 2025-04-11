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
			cadenaIzquierda = mitadIzquierda[i].replaceAll("[^a-zA-Z0-9]", "");
			cadenaDerecha = mitadDerecha[j].replaceAll("[^a-zA-Z0-9]", "");
			cadenaIzquierda = mitadIzquierda[i].replace("á", "a");
			cadenaIzquierda = mitadIzquierda[i].replace("Á", "a");
			cadenaIzquierda = mitadIzquierda[i].replace("é", "e");
        	cadenaIzquierda = mitadIzquierda[i].replace("É", "e");
        	cadenaIzquierda = mitadIzquierda[i].replace("í", "i");
        	cadenaIzquierda = mitadIzquierda[i].replace("Í", "i");
        	cadenaIzquierda = mitadIzquierda[i].replace("ó", "o");
       		cadenaIzquierda = mitadIzquierda[i].replace("Ó", "o");
        	cadenaIzquierda = mitadIzquierda[i].replace("ú", "u");
        	cadenaIzquierda = mitadIzquierda[i].replace("Ú", "u");
        	cadenaIzquierda = mitadIzquierda[i].replace("ü", "u");
        	cadenaIzquierda = mitadIzquierda[i].replace("Ü", "u");

        	cadenaDerecha = mitadDerecha[j].replace("á", "a");
			cadenaDerecha = mitadDerecha[j].replace("Á", "a");
			cadenaDerecha = mitadDerecha[j].replace("é", "e");
        	cadenaDerecha = mitadDerecha[j].replace("É", "e");
        	cadenaDerecha = mitadDerecha[j].replace("í", "i");
        	cadenaDerecha = mitadDerecha[j].replace("Í", "i");
        	cadenaDerecha = mitadDerecha[j].replace("ó", "o");
       		cadenaDerecha = mitadDerecha[j].replace("Ó", "o");
        	cadenaDerecha = mitadDerecha[j].replace("ú", "u");
        	cadenaDerecha = mitadDerecha[j].replace("Ú", "u");
        	cadenaDerecha = mitadDerecha[j].replace("ü", "u");
        	cadenaDerecha = mitadDerecha[j].replace("Ü", "u");


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