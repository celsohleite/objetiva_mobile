package zi.objetivamobile.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import zi.objetivamobile.business.BaseBusiness;
import zi.objetivamobile.data.ClienteLaudoData;
import zi.objetivamobile.data.VistoriadorData;
import zi.objetivamobile.ibase.IBase;
import zi.objetivamobile.model.AuthVistoriadorModel;

/**
 * Created by leite on 24/05/2018.
 */

public class Base extends AppCompatActivity {
    private VistoriadorData data;
    private AuthVistoriadorModel vistoriadorModel;
    private Context context = null;
    private ClienteLaudoData clienteLaudoData;

    public Base(){
        data = new VistoriadorData(this);
        clienteLaudoData = new ClienteLaudoData(this);
    }

    public void gravarDadosVistoriador(AuthVistoriadorModel model){
        AuthVistoriadorModel vistoriador = new AuthVistoriadorModel();
        try {
            model = data.getVistoriador();
            if (vistoriador.getNomeVistoriador() != null) {
                data.delete();
            } else {
                data.insert(model);
            }
        }catch (Exception e){
            data.insert(model);
        }
    }

    public String getNomeUsuario() {
        vistoriadorModel = data.getVistoriador();
        return vistoriadorModel.getNomeVistoriador().concat(" ").concat(vistoriadorModel.getSobrenomeVistoriador());
    }

    public int getCodUsuario() {
        vistoriadorModel = data.getVistoriador();
        return vistoriadorModel.getCodUsuario();
    }

    public int getcodVistoriador(){
        vistoriadorModel = data.getVistoriador();
        return vistoriadorModel.getCodVistoriador();
    }

    public String getEmailVistoriador(){
        vistoriadorModel = data.getVistoriador();
        return vistoriadorModel.getEmailVistoriador();
    }

    public String getTipoLaudo(Long numLaudo){
        return clienteLaudoData.getTipoLaudo(numLaudo);
    }


}
