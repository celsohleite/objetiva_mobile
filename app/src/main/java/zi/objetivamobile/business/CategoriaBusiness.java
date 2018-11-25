package zi.objetivamobile.business;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import zi.objetivamobile.R;
import zi.objetivamobile.data.CategoriaAuxData;
import zi.objetivamobile.imobile.IActionsListener;
import zi.objetivamobile.model.CategoriaAuxModel;
import zi.objetivamobile.sync.CategoriaSync;

/**
 * Created by leite on 02/11/17.
 */

public class CategoriaBusiness extends Thread implements IActionsListener<CategoriaSync.ErrorType, CategoriaSync.ActionsType> {

    private CategoriaAuxData mCategiriaData = null;
    private Context mContext = null;
    private int mQuest = 0;

    private List<CategoriaAuxModel> listCategoria = null;
    private List<CategoriaAuxModel> mListQuestAtiva = null;
    private List<CategoriaAuxModel> mListTitulo = null;
    private List<CategoriaAuxModel> mListResposta = null;

    private View mView = null;
    private ProgressDialog mProgressDialog;

    public CategoriaBusiness(Context context) {
        this.mContext = context;
        mView = new View(context);
        mProgressDialog = new ProgressDialog(mView.getContext());
    }

    public List<CategoriaAuxModel> getPerguntasCategoria(int idVistoria) {
        mCategiriaData = new CategoriaAuxData(mContext);
        listCategoria = new ArrayList<CategoriaAuxModel>();

        listCategoria = mCategiriaData.getCategoria(idVistoria);

        return listCategoria;
    }

    public List<CategoriaAuxModel> getCheckListTitulo() {
        mListTitulo = new ArrayList<CategoriaAuxModel>();
        mListTitulo.add(listCategoria.get(0));
        mListTitulo.add(listCategoria.get(1));

        return mListTitulo;
    }

    public List<CategoriaAuxModel> getCheckListResposta() {
        mListResposta = new ArrayList<CategoriaAuxModel>();

        if (listCategoria != null) {
            for (CategoriaAuxModel model : listCategoria) {
                if (model.getIdResposta() > 0)
                    mListResposta.add(model);
            }
        }
        return mListResposta;
    }

    public List<CategoriaAuxModel> saveQuest() {
        mQuest = 3;
        mListQuestAtiva = new ArrayList<CategoriaAuxModel>();

        for (CategoriaAuxModel categoria : listCategoria) {
            if (categoria.getCategoria().substring(0, 1).equals(String.valueOf(mQuest))) {
                mListQuestAtiva.add(categoria);
            }
        }
        return mListQuestAtiva;
    }

    public int getNumCategoria(String categoria) {
        mCategiriaData = new CategoriaAuxData(mContext);
        return mCategiriaData.getTipoCategoria(categoria);
    }

    public void syncCategorias() {
        new CategoriaSync(mContext, this).execute();
    }

    public void loading(final boolean exibir) {
        Handler mHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                if (exibir) {
                    mProgressDialog.setMax(100);
                    mProgressDialog.setTitle(mContext.getString(R.string.title_loading_recebe_dados));
                    mProgressDialog.setMessage(mContext.getString(R.string.title_loading_aguarde_recebe_dados));
                    mProgressDialog.setCancelable(false);
                    mProgressDialog.show();
                } else {
                    mProgressDialog.dismiss();
                }
            }
        };

        Message message = mHandler.obtainMessage();
        message.sendToTarget();
    }

    @Override
    public void onErrorBusiness(CategoriaSync.ErrorType type, String errorMessage) {

    }

    @Override
    public void onErrorBusiness(Throwable error) {

    }

    @Override
    public void doResultBusiness(CategoriaSync.ActionsType type, Object object) {
        switch (type) {
            case onStartLoading:
                loading(true);
                break;
            case onStopLoading:
                loading(false);
                break;
        }
    }
}
