package zi.objetivamobile.business;

import android.content.Context;
import android.os.Environment;

import java.io.File;

import zi.objetivamobile.data.FotoData;

/**
 * Created by leite on 05/06/2018.
 */

public class FotoBusiness {

    private static String mDirFoto="/laudos_objetiva/";
    private FotoData fotoData;

    private Context context;

    public FotoBusiness(Context context) {
        this.context = context;
        fotoData =new FotoData(context);
    }

    public void removerFoto(Long numLaudo, String foto){
        File diretorioLaudos = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), mDirFoto);
        File imagem = new File(diretorioLaudos, foto);
        if(imagem.exists()){
            imagem.delete();
            imagem.deleteOnExit();
            fotoData.delete(numLaudo, foto);
            System.out.println("[ Imagem ] : removida com sucesso! => "+foto);
        }else{
            System.out.println("[ Imagem ] : Não encontrada!");
        }

    }
}
