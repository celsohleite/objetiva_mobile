package zi.objetivamobile.sync;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.util.List;

import zi.objetivamobile.data.FotoData;
import zi.objetivamobile.evalid.ServiceParam;
import zi.objetivamobile.model.FotoModel;
import zi.objetivamobile.rest.ClientFoto;
import zi.objetivamobile.util.Image64Util;

/**
 * Created by leite on 08/05/2018.
 */
//classe invalida
public class EnvFotoLaudoSync {

    private List<FotoModel> mListFotoModel;
    private Context mContext;
    private FotoData fotoData;
    private Image64Util base64Util;
    private static String mDirFoto="/laudos_objetiva/";

    public EnvFotoLaudoSync(Context context) {
        mContext = context;
        fotoData = new FotoData(mContext);
        base64Util = new Image64Util();
    }

    public boolean sincronizarEnvioFotos()throws Exception{
        Bitmap bitmapConverterToString = null;
        String imageBase64 = null;

        mListFotoModel = fotoData.getFoto(null);

        for (FotoModel model :mListFotoModel ) {

            bitmapConverterToString = base64Util.decodeFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()
                    .concat(mDirFoto)
                    .concat(model.getNomeFoto()),"sync");
            imageBase64 = base64Util.imageToSend(bitmapConverterToString);

            model.setFotoArquivo(imageBase64);

            fotoData.getFoto(model.getIdLaudo());

            ClientFoto.get(ServiceParam.URL_HANDLEBAR.concat(ServiceParam.PORT_HANDLEBAR).concat(ServiceParam.URL_FOTO_ENVIO), model);
        }

        return true;
    }
}
