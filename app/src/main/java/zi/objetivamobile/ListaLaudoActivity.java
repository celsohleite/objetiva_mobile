package zi.objetivamobile;

import android.*;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zi.objetivamobile.adapter.ListaLaudoAdapter;
import zi.objetivamobile.base.Base;
import zi.objetivamobile.business.LaudoBusiness;
import zi.objetivamobile.evalid.ServiceParam;
import zi.objetivamobile.model.ClienteLaudoModel;
import zi.objetivamobile.model.LaudoItemModel;
import zi.objetivamobile.rest.ClientLaudo;
import zi.objetivamobile.util.GPSTracker;

public class ListaLaudoActivity extends Base implements
        AdapterView.OnItemClickListener {

    private ListaLaudoAdapter mListLaudoAdapter;

    final int REQUEST_PERMISSIONS_CODE = 128;

    private ListView mListLaudo;
    private List<LaudoItemModel> mLaudoItem;
    private List<ClienteLaudoModel> mClienteLaudo;
    private LaudoItemModel mLaudo;
    private Bundle mBundle = null;

    private TextView mIdentificacao = null;
    private EditText mEditNumeracao;

    private RadioGroup mRdbRadioIdentificacao;
    private RadioButton mRdbPlaca;
    private RadioButton mRdbMotor;
    private RadioButton mRdbCambio;

    private LaudoBusiness mLaudoBusiness;

    private LocationManager locationManager;

    private GPSTracker mGps;

    private String mTipoLaudo;
    private Long mCodigoLaudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onPermissao();
        setContentView(R.layout.activity_lista_laudo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLaudoBusiness = new LaudoBusiness(this);
        mRdbRadioIdentificacao = (RadioGroup) findViewById(R.id.rdb_laudo_identificacao);
        mIdentificacao = (TextView) findViewById(R.id.txtv_identi_laudo);

        locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);

        mBundle = getIntent().getExtras();

        if(mBundle !=null) {
            mTipoLaudo = mBundle.getString("tipoLaudo");
            mCodigoLaudo = mBundle.getLong("codigoLaudo");
        }

        mGps = new GPSTracker(this);

        //usar em todas as telas - importante
        mBundle = getIntent().getExtras();

        mLaudo = new LaudoItemModel();
        loadLaudos();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, LaudoFotoActivity.class);
        Long numLaudo = mLaudoItem.get(position).getIdLaudo();
        Long codigoLaudo = mLaudoItem.get(position).getCdLaudo();
        String descVistoria = mLaudoItem.get(position).getIdentificacao();
        intent.putExtra("tipoLaudo", mTipoLaudo);
        intent.putExtra("pNumLaudo", numLaudo);
        intent.putExtra("codigoLaudo", codigoLaudo);
        finish();
        startActivity(intent);
    }

    public void doInformeVeiculo(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View alertView = inflater.inflate(R.layout.alert_content_laudo, null);

        alert.setTitle(getString(R.string.alert_title_veiculo));
        alert.setView(alertView);
        alert.setCancelable(false);

        alert.setNeutralButton(getString(R.string.alert_btn_add), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                isValida(mLaudo);
            }

        });

        alert.setNegativeButton(R.string.alert_btn_cancel, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });

        alert.show();
    }

    public void onChangeRadioIdentifica(View radioId) {

        switch (radioId.getId()) {
            case R.id.rdb_placa:
                mLaudo.setImageID(R.mipmap.car_dark);
                mLaudo.setIdentificacao("placa");
                mLaudo.setLng(mGps.getLongitude());
                mLaudo.setLat(mGps.getLatitude());
                break;
            case R.id.rdb_chassis:
                mLaudo.setImageID(R.mipmap.cambio);
                mLaudo.setIdentificacao("cambio");
                mLaudo.setLng(mGps.getLongitude());
                mLaudo.setLat(mGps.getLatitude());
                break;
            case R.id.rdb_motor:
                mLaudo.setImageID(R.mipmap.motor);
                mLaudo.setIdentificacao("motor");
                mLaudo.setLng(mGps.getLongitude());
                mLaudo.setLat(mGps.getLatitude());
                break;
            case R.id.rdb_cambio:
                mLaudo.setImageID(R.mipmap.cambio);
                mLaudo.setIdentificacao("cambio");
                mLaudo.setLng(mGps.getLongitude());
                mLaudo.setLat(mGps.getLatitude());
                break;
        }
    }

    public void onChangeRadioPorte(View radioId) {

        switch (radioId.getId()) {
            case R.id.rdb_base:
                mLaudo.setVistoria("base");
                break;
            case R.id.rdb_movel:
                mLaudo.setVistoria("movel");
                break;
        }
    }

    public void loadLaudos() {
        mLaudo = new LaudoItemModel();
        mLaudoItem = new ArrayList<LaudoItemModel>();
        mClienteLaudo = new ArrayList<ClienteLaudoModel>();

        //mLaudoItem = mLaudoBusiness.getLaudoExecucao();
        mClienteLaudo = mLaudoBusiness.getLaudoInfoCliente(null, getcodVistoriador(), 0); // caso o laudo estiver aberto a tela recebe o valor 0

        for (ClienteLaudoModel model :mClienteLaudo) {
            mLaudo = new LaudoItemModel();
            mLaudo.setModelo(model.getNumLaudo().toString());
            mLaudo.setCdLaudo(model.getCdLaudo());
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
        mListLaudo.setOnItemClickListener(this);

    }

    public void onPermissao() {

        String permissaoGPS = "this.checkCallingOrSelfPermission";

        // If request is cancelled, the result arrays are empty.
        if (ContextCompat.checkSelfPermission(ListaLaudoActivity.this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(ListaLaudoActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSIONS_CODE);
            ActivityCompat.requestPermissions(ListaLaudoActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            ActivityCompat.requestPermissions(ListaLaudoActivity.this, new String[]{Manifest.permission.INTERNET}, 1);
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 42);
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 42);

            this.checkCallingOrSelfPermission(permissaoGPS);


        }
    }

    public void isValida(LaudoItemModel laudoItem) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        List<Long> dadosLaudoNovo = new ArrayList<Long>();
        Long idLaudo;

        try {
            if (mLaudoItem.size() == 0)
                idLaudo = mLaudoItem.get(0).getIdLaudo();
            else
                idLaudo = mLaudoItem.get(mLaudoItem.size() - 1).getIdLaudo();
        }catch (Exception e){
            idLaudo = 1l;
        }

        dadosLaudoNovo = ClientLaudo.getNovoNumLaudo(ServiceParam.URL_HANDLEBAR.concat(ServiceParam.PORT_HANDLEBAR).concat(ServiceParam.URL_GERA_NUM_LAUDO));

        Intent intent = new Intent(this, LaudoClienteActivity.class);
        intent.putExtra("tipoLaudo", mLaudo.getIdentificacao());
        intent.putExtra("pIdLaudo", idLaudo);
        intent.putExtra("codigoLaudo", dadosLaudoNovo.get(0));//considera-se o id primario da tabela laudo
        intent.putExtra("novoNumLaudo", dadosLaudoNovo.get(1));

        mLaudo.setCdLaudo(dadosLaudoNovo.get(0));

        boolean valida = false;
        String sNumeracao = "";

        if (laudoItem.getIdentificacao() == null || laudoItem.getIdentificacao().equals("")) {
            alert.setTitle(getString(R.string.alert_title_atencao));
            alert.setMessage(getString(R.string.alert_err_identificacao));
            valida = true;

            alert.setNegativeButton(R.string.alert_btn_ok, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    doInformeVeiculo(null);
                }

            });

            alert.show();
            return;
        } else if (laudoItem.getVistoria() == null || laudoItem.getVistoria().equals("")) {
            alert.setTitle(getString(R.string.alert_title_atencao));
            alert.setMessage(getString(R.string.alert_err_vistoria));
            valida = true;
            alert.setNegativeButton(R.string.alert_btn_ok, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    doInformeVeiculo(null);
                }

            });

            alert.show();
            return;
        } else if (laudoItem.getNumeracao() == null || laudoItem.getNumeracao().equals("")) {
              sNumeracao = "S.I - ";
            //mLaudo.setNumeracao(sNumeracao.concat(String.valueOf(mLaudoBusiness.geraCodeSemIdentificacao())));
            mLaudo.setStatus("AT");
            mLaudoBusiness.insertLaudo(mLaudo);
            finish();

            startActivity(intent);

        } else if (!valida) {
            mLaudo.setStatus("AT");
            mLaudoBusiness.insertLaudo(mLaudo);
            finish();

            startActivity(intent);
        }
    }
}
