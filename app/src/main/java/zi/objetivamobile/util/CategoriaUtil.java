package zi.objetivamobile.util;

/**
 * Created by leite on 30/11/17.
 */

public class CategoriaUtil {
    public static int corrigirCategoria(String categoria, int position) {
        int retorno = 0;

        categoria = categoria.replace(".", ";").substring(0, 6).trim();
        String[] tit = categoria.split(";");
        try {
            if(tit.length > 2)
              retorno = Integer.parseInt(tit[position]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno;
    }
}
