package zi.objetivamobile.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;

import java.util.ArrayList;
import java.util.List;

import zi.objetivamobile.data.FotoData;
import zi.objetivamobile.evalid.ServiceParam;
import zi.objetivamobile.imobile.IActionsListener;
import zi.objetivamobile.model.FotoModel;
import zi.objetivamobile.rest.ClientFoto;
import zi.objetivamobile.util.Image64Util;

/**
 * Created by leite on 21/07/2018.
 */

public class EnvioFotoHelper extends AsyncTask<String, Integer,  String> {

    private final IActionsListener<ErrorType, ActionsType> mIAcionsListener;
    private List<FotoModel> mListFotoModel = null;
    private FotoData mFotoData = null;
    private Image64Util base64Util;
    private static String mDirFoto="/laudos_objetiva/";

    public EnvioFotoHelper(Context context, IActionsListener<ErrorType, ActionsType> mIAcionsListener) {
        this.mIAcionsListener = mIAcionsListener;
        this.mFotoData = new FotoData(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        Bitmap bitmapConverterToString = null;
        String imageBase64 = null;
        mIAcionsListener.doResultBusiness(ActionsType.onSartLoading, true);
        try {
            mListFotoModel = new ArrayList<FotoModel>();
            mListFotoModel = this.mFotoData.getFoto(null);
            base64Util = new Image64Util();

            for (FotoModel model : mListFotoModel) {

                bitmapConverterToString = base64Util.decodeFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()
                        .concat(mDirFoto)
                        .concat(model.getNomeFoto()), "sync");
                imageBase64 = base64Util.imageToSend(bitmapConverterToString);

                model.setFotoArquivo(imageBase64);

                this.mFotoData.getFoto(model.getIdLaudo());

                ClientFoto.get(ServiceParam.URL_HANDLEBAR.concat(ServiceParam.PORT_HANDLEBAR).concat(ServiceParam.URL_FOTO_ENVIO), model);

                this.mFotoData.updateFotoSync(model);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        String concluido = "";
        concluido = "OK";
        mIAcionsListener.doResultBusiness(ActionsType.onStopLoading, concluido);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    public enum ErrorType {
        onMessageAttention

    }

    public enum ActionsType {
        onSartLoading, onStopLoading
    }
}
