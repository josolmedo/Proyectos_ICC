public class LimpiadorDeCadenas{
	public static String[] limpiar(String[] lines){

		 int i = 0;

        while (i < lines.length) {
            lines[i] = lines[i].trim();
            lines[i] = lines[i].replaceAll("^\\p{L}\\p{N}", "");
            //lines[i] = lines[i].replaceAll("[^a-zA-Z0-9]", "");
            lines[i] = lines[i].replaceAll(" ", "");
            lines[i] = lines[i].replaceAll("\\?", "");
            lines[i] = lines[i].replaceAll("\\¡", "");
            lines[i] = lines[i].replaceAll("\\¿", "");
            lines[i] = lines[i].replaceAll("\\%", "");
            lines[i] = lines[i].replaceAll("\\&", "");
            lines[i] = lines[i].replaceAll("\\,", "");
            lines[i] = lines[i].replaceAll("\\.", "");
            lines[i] = lines[i].replaceAll("á", "a");
            lines[i] = lines[i].replaceAll("Á", "a");
            lines[i] = lines[i].replaceAll("é", "e");
            lines[i] = lines[i].replaceAll("É", "e");
            lines[i] = lines[i].replaceAll("í", "i");
            lines[i] = lines[i].replaceAll("Í", "i");
            lines[i] = lines[i].replaceAll("ó", "o");
            lines[i] = lines[i].replaceAll("Ó", "o");
            lines[i] = lines[i].replaceAll("ú", "u");
            lines[i] = lines[i].replaceAll("Ú", "u");
            lines[i] = lines[i].replaceAll("ü", "u");
            lines[i] = lines[i].replaceAll("Ü", "u");
            lines[i] = lines[i].toLowerCase();
            i++;
        }

	}
	return lines;
}