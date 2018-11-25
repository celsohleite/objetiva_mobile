package zi.objetivamobile.sync;

import android.content.Context;

import java.util.List;

import zi.objetivamobile.data.ClienteData;
import zi.objetivamobile.evalid.ServiceParam;
import zi.objetivamobile.model.ClienteModel;
import zi.objetivamobile.rest.ClientCliente;

/**
 * Created by leite on 14/03/2018.
 */

public class ClienteSync {

    private Context context = null;
    private List<ClienteModel> mListCategoria = null;
    private ClienteModel mCliente = null;
    private ClienteData clienteData = null;

    public ClienteSync(Context mContext) {
        this.context = mContext;
        clienteData = new ClienteData(mContext);
    }

    public void syncCliente(int codusuario) {
        mCliente = new ClienteModel();
        mCliente.setCdUsuario(codusuario);
        mListCategoria = ClientCliente.get(ServiceParam.URL_HANDLEBAR.concat(ServiceParam.PORT_HANDLEBAR).concat(ServiceParam.URL_CLIENTE).concat("?codusuario=").concat(mCliente.getCdUsuario().toString()));

        clienteData.delete();

        for (ClienteModel model :mListCategoria) {
            clienteData.insert(model);
        }
    }



}
