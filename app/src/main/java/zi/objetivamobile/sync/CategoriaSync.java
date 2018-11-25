package zi.objetivamobile.sync;

import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

import zi.objetivamobile.data.CategoriaAuxData;
import zi.objetivamobile.data.CategoriaData;
import zi.objetivamobile.evalid.CategoriaValid;
import zi.objetivamobile.evalid.ServiceParam;
import zi.objetivamobile.helper.EnvioFotoHelper;
import zi.objetivamobile.imobile.IActionsListener;
import zi.objetivamobile.model.CategoriaAuxModel;
import zi.objetivamobile.model.CategoriaModel;
import zi.objetivamobile.rest.ClientCategoria;
import zi.objetivamobile.util.CategoriaUtil;

/**
 * Created by leite on 04/11/17.
 */

public class CategoriaSync extends AsyncTask<String, Integer, String> {

    private Context context = null;
    private CategoriaData mData = null;
    private CategoriaAuxData mDataAux = null;
    private CategoriaModel mCategoria = null;
    private CategoriaAuxModel mCategoriaAux = null;
    private List<CategoriaModel> mListCategoria = null;
    private final IActionsListener<CategoriaSync.ErrorType, CategoriaSync.ActionsType> mIAcionsListener;

    private int mItensCheckList = 0;

    public CategoriaSync(Context context, IActionsListener<CategoriaSync.ErrorType, CategoriaSync.ActionsType> mIAcionsListener) {
        mData = new CategoriaData(context);
        mDataAux = new CategoriaAuxData(context);
        this.context = context;
        this.mIAcionsListener = mIAcionsListener;
    }

    public void setmItensCheckList(int mItensCheckList) {
        this.mItensCheckList = mItensCheckList;
    }

    @Override
    protected String doInBackground(String... strings) {
        mIAcionsListener.doResultBusiness(ActionsType.onStartLoading, "start");
        syncCategorias();
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        System.out.println("[ Foto ]" + values);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mIAcionsListener.doResultBusiness(ActionsType.onStopLoading,"stop");
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    public void syncCategorias() {

        try {
            //remove dados e insere novos.
            if (mDataAux.getCategoria(1).size() > 0)
                mDataAux.delete();

            mCategoria = new CategoriaModel();
            mListCategoria = new ArrayList<CategoriaModel>();
            mListCategoria = ClientCategoria.get(ServiceParam.URL_HANDLEBAR.concat(ServiceParam.PORT_HANDLEBAR).concat(ServiceParam.URL_CATEGORIA), mCategoria);

            setmItensCheckList(mListCategoria.size());

            for (CategoriaModel model : mListCategoria) {

                mCategoriaAux = new CategoriaAuxModel();

                switch (model.getDescCategoria().substring(0, 2)) {
                    case CategoriaValid.MOTOR:
                        model.setTipoVistoria("motor");
                        break;
                    case CategoriaValid.CAMBIO:
                        model.setTipoVistoria("cambio");
                        break;
                    case CategoriaValid.CHASSIS:
                        model.setTipoVistoria("chassis");
                        break;
                    case CategoriaValid.CHASSIS_2:
                        model.setTipoVistoria("chassis");
                        break;
                }

                mCategoriaAux.setIdTitulo(CategoriaUtil.corrigirCategoria(model.getDescCategoria(), 0));
                mCategoriaAux.setIdSubTitulo(CategoriaUtil.corrigirCategoria(model.getDescCategoria(), 1));
                mCategoriaAux.setIdResposta(CategoriaUtil.corrigirCategoria(model.getDescCategoria(), 2));
                mCategoriaAux.setDescricao(model.getDescCategoria());
                mCategoriaAux.setCategoria(model.getTipoVistoria());
                mCategoriaAux.setCd_categoria(model.getCodCategoria());

                mDataAux.insert(mCategoriaAux);

            }

        } catch (Exception e) {
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setMessage(e.getMessage()).show();
        }
    }

    public enum ErrorType {
        onError
    }

    public enum ActionsType {
        onStartLoading, onStopLoading
    }
}
