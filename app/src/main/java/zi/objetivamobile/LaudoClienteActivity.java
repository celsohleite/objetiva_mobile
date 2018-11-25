package zi.objetivamobile;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import zi.objetivamobile.base.Base;
import zi.objetivamobile.business.LaudoBusiness;
import zi.objetivamobile.model.ClienteLaudoModel;
import zi.objetivamobile.model.ClienteModel;

public class LaudoClienteActivity extends Base  {

    private ClienteLaudoModel mClienteModel;
    private LaudoBusiness mLaudoBusiness;

    private boolean isMercosul = false;
    private Long mIdLaudo;
    private Long mNovoNumLaudo;
    private Long mCodigoLaudo;
    private String mTipoLaudo;
    private Bundle mBundle = null;

    //inteface user data cliente laudo
    private EditText mEdtNumLaudo;
    private Spinner mSpnTipoCliente;
    private EditText mEdtNomeCliente;
    private EditText mEdtPlaca;
    private EditText mEdtCidadeVeiculo;
    private EditText mEdtMarcaVeiculo;
    private EditText mEdtModeloVeiculo;
    private EditText mEdtIdentMotor;
    private EditText mEdtIdentCambio;
    private EditText mEdtIdentCarroceria;
    private EditText mEdtCorVeiculo;
    private EditText mEdtAnoFabricacao;
    private EditText mEdtAnoModelo;
    private EditText mEdtHodometro;
    private EditText mEdtChassi;
    private CheckBox mChkPlacaMercosul;

    private Spinner mSpnUfVeiculo;
    private Spinner mSpnCliente;
    private LinearLayout mLinCliente;

    private List<ClienteModel> mListCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laudo_cliente);

        mClienteModel = new ClienteLaudoModel();
        mLaudoBusiness = new LaudoBusiness(this);

        mEdtNumLaudo = findViewById(R.id.laudo);

        mSpnTipoCliente = findViewById(R.id.spn_tipo_cliente);
        mEdtNomeCliente = findViewById(R.id.cliente);
        mEdtPlaca = findViewById(R.id.placa);
        mChkPlacaMercosul = findViewById(R.id.chk_mercosul);
        mEdtCidadeVeiculo = findViewById(R.id.cidade_veiculo);
        mEdtMarcaVeiculo = findViewById(R.id.marca_veiculo);
        mEdtModeloVeiculo = findViewById(R.id.modelo_veiculo);
        mEdtIdentMotor = findViewById(R.id.ident_motor);
        mEdtIdentCambio = findViewById(R.id.ident_cambio);
        mEdtIdentCarroceria = findViewById(R.id.ident_carroceria);
        mEdtCorVeiculo = findViewById(R.id.cor_veiculo);
        mEdtAnoFabricacao = findViewById(R.id.ano_fabricacao);
        mEdtHodometro = findViewById(R.id.hodometro);

        mSpnUfVeiculo = findViewById(R.id.spn_uf_placa);
        mSpnCliente = findViewById(R.id.spn_cliente);
        mLinCliente = findViewById(R.id.lin_nome_cliente);
        mEdtChassi = findViewById(R.id.chassi_veiculo);
        mEdtAnoModelo = findViewById(R.id.ano_modelo);
        //inicializa oculto
        mSpnCliente.setVisibility(View.GONE);

        mBundle = getIntent().getExtras();

        mIdLaudo = Long.parseLong(String.valueOf(mBundle.get("pIdLaudo")));
        mNovoNumLaudo = Long.parseLong(String.valueOf(mBundle.get("novoNumLaudo")));
        mTipoLaudo = mBundle.getString("tipoLaudo");
        mCodigoLaudo = Long.parseLong(String.valueOf(mBundle.get("codigoLaudo")));

        mListCliente = new ArrayList<ClienteModel>();

        mListCliente = mLaudoBusiness.getCliente(getCodUsuario());

        mEdtNumLaudo.setText(mNovoNumLaudo.toString());
        mEdtNumLaudo.setEnabled(false);

        mChkPlacaMercosul.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mEdtPlaca.setText("");
            }
        });

        //interacao da placa
        mEdtPlaca.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                Log.i("[ onTextChanged ]","teste");
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

                if(mChkPlacaMercosul.isChecked()) {

                    if (arg0.length() == 2) {
                        mEdtPlaca.setInputType(InputType.TYPE_CLASS_NUMBER);
                    } else if (arg0.length() == 3) {
                        mEdtPlaca.setInputType(InputType.TYPE_CLASS_TEXT);
                        mEdtPlaca.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
                    } else if (arg0.length() == 4) {
                        mEdtPlaca.setInputType(InputType.TYPE_CLASS_NUMBER);
                    } else if (arg0.length() == 5) {
                        mEdtPlaca.setInputType(InputType.TYPE_CLASS_NUMBER);
                    } else if (arg0.length() > 5) {
                        mEdtPlaca.setInputType(InputType.TYPE_CLASS_NUMBER);
                        mEdtPlaca.setFilters(new InputFilter[]{new InputFilter.LengthFilter(7)});
                    } else {
                        mEdtPlaca.setInputType(InputType.TYPE_CLASS_TEXT);
                        mEdtPlaca.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
                    }

                } else {
                    //interacao da placa
                    mEdtPlaca.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                            Log.i("[ onTextChanged ]","teste");
                        }
                        @Override
                        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                            if(arg0.length() >= 2){
                                mEdtPlaca.setInputType(InputType.TYPE_CLASS_NUMBER);
                                mEdtPlaca.setFilters(new InputFilter[] { new InputFilter.LengthFilter(7)});
                            } else {
                                mEdtPlaca.setInputType(InputType.TYPE_CLASS_TEXT);
                                mEdtPlaca.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
                            }
                            Log.i("[ beforeTextChanged ]",arg0.toString());
                        }
                        @Override
                        public void afterTextChanged(Editable arg0) {
                            Log.i("[ afterTextChanged ]","teste");
                        }
                    });
                }
                Log.i("[ beforeTextChanged ]",arg0.toString());
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                Log.i("[ afterTextChanged ]","teste");
            }
        });

        mSpnTipoCliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        ocultaCliente();
                        break;
                    case 1:
                        doConfigurarSpinnerCliente();
                        break;
                    case 2:
                        ocultaCliente();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if("motor".equals(mTipoLaudo)){
            doCamposMotor();
        }else if("cambio".equals(mTipoLaudo)){
            doCampoCambio();
        }else if("chassis".equals(mTipoLaudo)){
            doCamposChassis();
        }
    }

    @Override
    public void onBackPressed() {
        return;
    }

    public void doClick_save(View view) {

        try {

            mClienteModel.setNumLaudo(mNovoNumLaudo);
            mClienteModel.setCdLaudo(mCodigoLaudo);
            //validar tipo de cliente
            if (mEdtNomeCliente.getText().toString() != null && !"".equals(mEdtNomeCliente.getText().toString()))
                mClienteModel.setNomeCliente(mEdtNomeCliente.getText().toString());

            if (mEdtPlaca.getText().toString() != null && !"".equals(mEdtPlaca.getText().toString()))
                mClienteModel.setPlaca(mEdtPlaca.getText().toString());

            if (mEdtCidadeVeiculo.getText().toString() != null && !"".equals(mEdtCidadeVeiculo.getText().toString()))
                mClienteModel.setCidade(mEdtCidadeVeiculo.getText().toString());

            if (mSpnUfVeiculo.getSelectedItem().toString() != null)
                mClienteModel.setUf(mSpnUfVeiculo.getSelectedItem().toString());

            if (mEdtMarcaVeiculo.getText().toString() != null && !"".equals(mEdtMarcaVeiculo.getText().toString()))
                mClienteModel.setMarca(mEdtMarcaVeiculo.getText().toString());

            if (mEdtModeloVeiculo.getText().toString() != null && !"".equals(mEdtModeloVeiculo.getText().toString()))
                mClienteModel.setModelo(mEdtModeloVeiculo.getText().toString());

            if (mEdtIdentCambio.getText().toString() != null && !"".equals(mEdtIdentCambio.getText().toString()))
                mClienteModel.setIdentificacaoCambio(mEdtIdentCambio.getText().toString());

            if (mEdtIdentCarroceria.getText().toString() != null && !"".equals(mEdtIdentCarroceria.getText().toString()))
                mClienteModel.setIdentificacaoCarroceria(mEdtIdentCarroceria.getText().toString());

            if (mEdtIdentMotor.getText().toString() != null && !"".equals(mEdtIdentMotor.getText().toString()))
                mClienteModel.setIdentificacaoMotor(mEdtIdentMotor.getText().toString());

            if (mEdtCorVeiculo.getText().toString() != null && !"".equals(mEdtCorVeiculo.getText().toString()))
                mClienteModel.setCor(mEdtCorVeiculo.getText().toString());

            if (mEdtAnoFabricacao.getText().toString() != null && !"".equals(mEdtAnoFabricacao.getText().toString()))
                mClienteModel.setAnoFabricacao(Integer.parseInt(mEdtAnoFabricacao.getText().toString()));

            if (mEdtHodometro.getText().toString() != null && !"".equals(mEdtHodometro.getText().toString()))
                mClienteModel.setHodometro(mEdtHodometro.getText().toString());

            if (mEdtChassi.getText().toString() != null && !"".equals(mEdtChassi.getText().toString()))
                mClienteModel.setChassi(mEdtChassi.getText().toString());

            if (mEdtAnoModelo.getText().toString() != null && !"".equals(mEdtAnoModelo.getText().toString()))
                mClienteModel.setAnoModelo(Integer.parseInt(mEdtAnoModelo.getText().toString()));

            mClienteModel.setTipoLaudo(mTipoLaudo);

            //tipo de cliente
            switch (mSpnTipoCliente.getSelectedItem().toString()) {
                case "Empresa":
                    mClienteModel.setTipoCliente(0);
                    break;
                case "Particular":
                    mClienteModel.setTipoCliente(1);
                    break;
                default:
                    mClienteModel.setTipoCliente(1);
                    break;
            }

            if(mSpnCliente.getSelectedItemPosition() > -1)
                mClienteModel.setCdCliente(mListCliente.get(mSpnCliente.getSelectedItemPosition()).getCdCliente());

            mClienteModel.setIdLaudoItem(mIdLaudo);

            mClienteModel.setCdUsuario(getCodUsuario());

            mClienteModel.setCdVistoriador(getcodVistoriador());

            Intent intent = new Intent(this, ListaLaudoActivity.class);
            if (mLaudoBusiness.insertLaudoInfoCliente(mClienteModel)) {
                intent.putExtra("tipoLaudo", mTipoLaudo);
                intent.putExtra("codigoLaudo", mCodigoLaudo);
                finish();
                startActivity(intent);
            }

        } catch (Exception e) {
            Log.i("Error", e.getMessage());
        }
    }

    public void doConfigurarSpinnerCliente() {
        mSpnCliente.setVisibility(View.VISIBLE);
        List<String> clientes = new ArrayList<String>();

        for (int i = 0; i < mListCliente.size(); i++) {
            clientes.add(mListCliente.get(i).getNomeFantasia().toUpperCase());
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, clientes);
        mSpnCliente.setAdapter(adapter);

        mLinCliente.setVisibility(View.GONE);
    }

    public void ocultaCliente() {
        mSpnCliente.setVisibility(View.GONE);
        mLinCliente.setVisibility(View.VISIBLE);
    }

    public void doClick_cancelar(View view) {

        mLaudoBusiness.removerLaudoGerado(mNovoNumLaudo);

        Intent intent = new Intent(this, ListaLaudoActivity.class);
        //mLaudoBusiness.deleteLaudo((long) (mLaudoBusiness.geraCodeSemIdentificacao()));
        startActivity(intent);
        finish();
    }

    public void doCamposChassis() {

        mEdtPlaca.setVisibility(View.GONE);
        mEdtCidadeVeiculo.setVisibility(View.GONE);
        mEdtMarcaVeiculo.setVisibility(View.GONE);
        mEdtModeloVeiculo.setVisibility(View.GONE);
        mEdtIdentCarroceria.setVisibility(View.GONE);
        mEdtCorVeiculo.setVisibility(View.GONE);
        mEdtAnoFabricacao.setVisibility(View.GONE);
        mEdtHodometro.setVisibility(View.GONE);
        mSpnUfVeiculo.setVisibility(View.GONE);
        mEdtChassi.setVisibility(View.GONE);
        mEdtAnoModelo.setVisibility(View.GONE);
    }

    public void doCamposMotor() {

        mEdtPlaca.setVisibility(View.GONE);
        mEdtCidadeVeiculo.setVisibility(View.GONE);
        mEdtMarcaVeiculo.setVisibility(View.GONE);
        mEdtModeloVeiculo.setVisibility(View.GONE);
        mEdtIdentCarroceria.setVisibility(View.GONE);
        mEdtCorVeiculo.setVisibility(View.GONE);
        mEdtAnoFabricacao.setVisibility(View.GONE);
        mEdtHodometro.setVisibility(View.GONE);
        mSpnUfVeiculo.setVisibility(View.GONE);
        mEdtChassi.setVisibility(View.GONE);
        mEdtAnoModelo.setVisibility(View.GONE);
    }

    public void doCampoCambio() {

        mEdtPlaca.setVisibility(View.GONE);
        mEdtCidadeVeiculo.setVisibility(View.GONE);
        mEdtMarcaVeiculo.setVisibility(View.GONE);
        mEdtModeloVeiculo.setVisibility(View.GONE);
        mEdtIdentCarroceria.setVisibility(View.GONE);
        mEdtCorVeiculo.setVisibility(View.GONE);
        mEdtAnoFabricacao.setVisibility(View.GONE);
        mEdtHodometro.setVisibility(View.GONE);
        mSpnUfVeiculo.setVisibility(View.GONE);
        mEdtChassi.setVisibility(View.GONE);
        mEdtAnoModelo.setVisibility(View.GONE);
    }

}