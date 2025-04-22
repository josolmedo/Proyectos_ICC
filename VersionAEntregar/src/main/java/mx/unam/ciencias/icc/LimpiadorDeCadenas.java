package mx.unam.ciencias.icc;

public class LimpiadorDeCadenas{
	public static String limpiar(String linea){       
        
        linea = linea.trim();
        linea = linea.replaceAll("^\\p{L}\\p{N}", "");
        linea = linea.replaceAll("[\\{\\}\\¨\\\\\\*\\+\\-]", ""); // elimina caracteres individuales
        linea = linea.replaceAll("/\\*\\*", ""); // elimina /** específicamente
        linea = linea.replaceAll(" ", "");
        linea = linea.replaceAll("\\?", "");
        linea = linea.replaceAll("\"", "");
        linea = linea.replaceAll("\"", "");
        linea = linea.replaceAll("\\*", "");
        linea = linea.replaceAll("[*\\\\]", "");
        linea = linea.replaceAll("\\¡", "");
        linea = linea.replaceAll("\\¿", "");
        linea = linea.replaceAll("\\%", "");
        linea = linea.replaceAll("\\&", "");
        linea = linea.replaceAll("\\,", "");
        linea = linea.replaceAll("\\.", "");
        linea = linea.replaceAll("á", "a");
        linea = linea.replaceAll("Á", "a");
        linea = linea.replaceAll("é", "e");
        linea = linea.replaceAll("É", "e");
        linea = linea.replaceAll("í", "i");
        linea = linea.replaceAll("Í", "i");
        linea = linea.replaceAll("ó", "o");
        linea = linea.replaceAll("Ó", "o");
        linea = linea.replaceAll("ú", "u");
        linea = linea.replaceAll("Ú", "u");
        linea = linea.replaceAll("ü", "u");
        linea = linea.replaceAll("Ü", "u");
        linea = linea.toLowerCase();
        
    	return linea;
    }
}