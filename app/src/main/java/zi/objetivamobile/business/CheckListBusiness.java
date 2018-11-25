package zi.objetivamobile.business;

import android.content.Context;

import zi.objetivamobile.LaudoClienteActivity;
import zi.objetivamobile.data.CheckListData;
import zi.objetivamobile.data.ClienteLaudoData;
import zi.objetivamobile.data.LaudoItemData;
import zi.objetivamobile.model.CategoriaAuxModel;
import zi.objetivamobile.model.CheckListModel;
import zi.objetivamobile.model.ClienteLaudoModel;

/**
 * Created by leite on 19/11/17.
 */

public class CheckListBusiness {

    private CheckListData mCheckListData;
    private LaudoItemData mLaudoItemData;
    private ClienteLaudoData mClienteLaudoData;


    public CheckListBusiness(Context context) {
        this.mCheckListData = new CheckListData(context);
        this.mLaudoItemData = new LaudoItemData(context);
        this.mClienteLaudoData = new ClienteLaudoData(context);

    }

    public void doGravaLaudo(CheckListModel model) {
        mCheckListData.insert(model);
    }

    public void doRemoverLaudo(Long idLaudo, Integer idTitulo, Integer idSubTitulo, Integer idResposta) {
        mCheckListData.delete(idLaudo, idTitulo, idSubTitulo, idResposta);
    }

    public boolean doPreviousLaudo(Long idLaudo, CategoriaAuxModel categoria) {
        boolean result = false;
        if (mCheckListData.getCheckList(idLaudo, categoria.getIdTitulo(), categoria.getIdSubTitulo(), categoria.getIdResposta()).size() > 0)
            result = true;
        return result;
    }

    public void concluirLaudo(Long idLaudo, String observacao){
        mLaudoItemData.updateConcluirLaudo(idLaudo, observacao);
    }

    public void concluir(ClienteLaudoModel cliente){
        mClienteLaudoData.concluirLaudo(cliente);
    }

}
