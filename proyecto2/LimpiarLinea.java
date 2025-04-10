public class LimpiarLinea {
    static String clean(String identifier) {
        identifier = identifier.replaceAll(" ", "_").trim();        
        char[] arreglo = identifier.toCharArray();        
        for(int contador = 0; contador<arreglo.length; contador++){            
            if (arreglo[contador] == '-'){
                if (contador + 1 < arreglo.length) {
                    arreglo[contador + 1] = Character.toUpperCase(arreglo[contador + 1]);
                }                
            }            
        }              
        identifier = new String (arreglo);
        identifier = identifier.replaceAll("-", "")
            .replaceAll("4","a")
            .replaceAll("3","e")
            .replaceAll("0","o")
            .replaceAll("1","l")
            .replaceAll("7","t")
            .replaceAll("¡","")
            .replaceAll("!","")
            .replaceAll("[^a-zA-Z0-9_]", "");//De otra forma tendría que poner $%& y no, que hueva.
        return identifier;
    }
}