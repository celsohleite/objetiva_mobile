package zi.objetivamobile.business;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import zi.objetivamobile.data.VistoriadorData;
import zi.objetivamobile.ibase.IBase;
import zi.objetivamobile.model.AuthVistoriadorModel;

/**
 * Created by leite on 24/05/2018.
 */

public class BaseBusiness{

    private VistoriadorData data;
    private AuthVistoriadorModel vistoriadorModel;
    private Context context = null;
    public BaseBusiness(Context context) {
        this.context  = context;
        data = new VistoriadorData(context);
        vistoriadorModel = new AuthVistoriadorModel();
    }

    public void insereDadosVistoriador(AuthVistoriadorModel model){
        data.insert(model);
    }

    public String getTipoLaudo() {
        return null;
    }

    public Long getNumLaudo() {
        return null;
    }

    public String getNomeUsuario() {
        data = new VistoriadorData(this.context);
        vistoriadorModel = data.getVistoriador();
        return vistoriadorModel.getNomeVistoriador().concat(" ").concat(vistoriadorModel.getSobrenomeVistoriador());
    }

    public int getCodUsuario() {
        data = new VistoriadorData(this.context);
        vistoriadorModel = data.getVistoriador();
        return vistoriadorModel.getCodUsuario();
    }
}
