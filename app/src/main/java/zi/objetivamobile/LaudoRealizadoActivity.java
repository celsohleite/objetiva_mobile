package zi.objetivamobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import zi.objetivamobile.adapter.ListaLaudoAdapter;
import zi.objetivamobile.base.Base;
import zi.objetivamobile.business.LaudoBusiness;
import zi.objetivamobile.model.ClienteLaudoModel;
import zi.objetivamobile.model.LaudoItemModel;

public class LaudoRealizadoActivity extends Base  {

    private ListaLaudoAdapter mListLaudoAdapter;

    final int REQUEST_PERMISSIONS_CODE=128;

    private ListView mListLaudo;
    private List<LaudoItemModel> mLaudoItem;
    private LaudoItemModel mLaudo;
    private List<ClienteLaudoModel> mClienteLaudo;

    private LaudoBusiness mLaudoBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laudo_realizado);

        mLaudoBusiness = new LaudoBusiness(this);

        mLaudo = new LaudoItemModel();
        loadLaudos();
    }

    public void loadLaudos() {
        mLaudo = new LaudoItemModel();
        mLaudoItem = new ArrayList<LaudoItemModel>();
        mClienteLaudo = new ArrayList<ClienteLaudoModel>();

        //mLaudoItem = mLaudoBusiness.getLaudoExecucao();
        mClienteLaudo = mLaudoBusiness.getLaudoInfoCliente(null, getcodVistoriador(), 1); // caso o laudo for realizado o valor da tela recebe o valor 1

        for (ClienteLaudoModel model :mClienteLaudo) {
            mLaudo = new LaudoItemModel();
            mLaudo.setModelo(model.getNumLaudo().toString());
            switch (model.getTipoLaudo()){
                case "placa" :
                    mLaudo.setImageID(R.mipmap.car_dark);
                    mLaudo.setPlaca(model.getModelo()+" - "+ model.getPlaca());
                    break;
                case "cambio" :
                    mLaudo.setImageID(R.mipmap.cambio);
                    mLaudo.setPlaca(model.getTipoLaudo() +" - "+ model.getIdentificacaoCambio());
                    break;
                case "motor" :
                    mLaudo.setImageID(R.mipmap.motor);
                    mLaudo.setPlaca(model.getTipoLaudo() +" - "+ model.getIdentificacaoMotor());
                    break;
                case "chassis" :
                    mLaudo.setImageID(R.mipmap.car_dark);
                    mLaudo.setPlaca(model.getTipoLaudo() +" - "+ model.getChassi());
                    break;
            }

            if(model.getNomeCliente() != null) {
                mLaudo.setNome(model.getNomeCliente());
            }else{
                mLaudo.setNome(mLaudoBusiness.getNomeClienteEmpresa(getCodUsuario(), model.getCdCliente()));
            }

            mLaudo.setIdLaudo(model.getNumLaudo());

            mLaudoItem.add(mLaudo);
        }

        mListLaudo = (ListView) findViewById(R.id.listv_item_laudos);
        mListLaudoAdapter = new ListaLaudoAdapter(this, mLaudoItem);
        mListLaudo.setAdapter(mListLaudoAdapter);

    }



}
