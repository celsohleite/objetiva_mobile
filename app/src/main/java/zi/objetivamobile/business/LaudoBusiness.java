package zi.objetivamobile.business;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import zi.objetivamobile.data.ClienteData;
import zi.objetivamobile.data.ClienteLaudoData;
import zi.objetivamobile.data.LaudoItemData;
import zi.objetivamobile.evalid.ServiceParam;
import zi.objetivamobile.model.ClienteLaudoModel;
import zi.objetivamobile.model.ClienteModel;
import zi.objetivamobile.model.LaudoItemModel;
import zi.objetivamobile.sync.EnvLaudoSync;
import zi.objetivamobile.sync.RemoverLaudoGeradoSync;

/**
 * Created by leite on 25/10/17.
 */

public class LaudoBusiness {

    private List<ClienteLaudoModel> mListClienteLaudoModel;

    private List<LaudoItemModel> listLaudo;
    private LaudoItemData mDataLaudo = null;
    private ClienteLaudoData mDataLaudoCliente = null;
    private ClienteData mClienteData = null;

    public LaudoBusiness(Context mContext) {
        mDataLaudo = new LaudoItemData(mContext);
        mDataLaudoCliente = new ClienteLaudoData(mContext);
        mClienteData = new ClienteData(mContext);
    }

    public List<LaudoItemModel> getLaudoExecucao() {
        listLaudo = new ArrayList<LaudoItemModel>();
        listLaudo = mDataLaudo.getItemLaudo("AT");
        return listLaudo;
    }

    public List<LaudoItemModel> getLaudoExecutado() {
        listLaudo = new ArrayList<LaudoItemModel>();
        listLaudo = mDataLaudo.getItemLaudo("IN");
        return listLaudo;
    }

    public List<LaudoItemModel> getLaudo() {
        listLaudo = new ArrayList<LaudoItemModel>();
        listLaudo = mDataLaudo.getItemLaudo(null);
        return listLaudo;
    }

    public void insertLaudo(LaudoItemModel laudo){
        mDataLaudo.insert(laudo);
    }

    public boolean insertLaudoInfoCliente(ClienteLaudoModel laudoCliente){
        return mDataLaudoCliente.insert(laudoCliente);
    }

    public void deleteLaudo(Long idLaudo){
        mDataLaudo.deleteLaudo(idLaudo);
    }

    public List<ClienteLaudoModel> getLaudoInfoCliente(Long numLaudo,int codVistoriador, int tela){
        return mDataLaudoCliente.getLaudoCliente(0L, codVistoriador, tela);
    }

    public String getNomeClienteEmpresa(int codUsuario, int cdCliente){
        String nomeClienteEmpresa = null;
        List<ClienteModel> listClientes = new ArrayList<ClienteModel>();
        listClientes = mClienteData.getCliente(codUsuario);

        for (ClienteModel model :listClientes) {
            if(model.getCdCliente() == cdCliente){
                nomeClienteEmpresa = model.getNomeFantasia();
                break;
            }
        }
    return nomeClienteEmpresa;
    }

    public void envLaudo(){
        mListClienteLaudoModel = mDataLaudoCliente.getLaudoCliente(0L, 0, 0);
        String url = ServiceParam.URL_HANDLEBAR.concat(ServiceParam.PORT_HANDLEBAR).concat(ServiceParam.URL_LAUDO_ENVIO);

        for (ClienteLaudoModel model : mListClienteLaudoModel) {
            model.setCdCliente(13);
            model.setCdUsuario(1);
            model.setCdVistoriador(17);
            EnvLaudoSync.sendLaudo(url,model);
        }
    }

    public void removerLaudoGerado(Long numLaudo){
        String url = ServiceParam.URL_HANDLEBAR.concat(ServiceParam.PORT_HANDLEBAR).concat(ServiceParam.URL_REMOVER_LAUDO_GERADO);
        ClienteLaudoModel model = new ClienteLaudoModel();
        model.setNumLaudo(numLaudo);
        RemoverLaudoGeradoSync.removerLaudo(url,model);
    }

    public List<ClienteModel> getCliente(int codUsuario){
        return mClienteData.getCliente(codUsuario);
    }

}