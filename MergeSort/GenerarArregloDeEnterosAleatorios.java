import java.util.Random;

public class GenerarArregloDeEnterosAleatorios{

	public static int[] generar(int dimension) {
		Random numerosAleatorios = new Random(); //Creamos un objeto instancia de la clase random
		int[] arregloDeNumerosAleatorios = new int [dimension]; //Creamos un arreglo de numeros enteros de longitud 10

		for (int contador = 0; contador < arregloDeNumerosAleatorios.length; contador++){ //Ciclo for para rellenar el arreglo de numeros aleatorios
			arregloDeNumerosAleatorios[contador] = numerosAleatorios.nextInt(1000000); //Rellenamos el arreglo con números aleatorios del 0 al 999999
		}

		return arregloDeNumerosAleatorios;
	}
}