package zi.objetivamobile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zi.objetivamobile.adapter.CheckListAdapter;
import zi.objetivamobile.base.Base;
import zi.objetivamobile.business.CategoriaBusiness;
import zi.objetivamobile.business.CheckListBusiness;
import zi.objetivamobile.data.FotoData;
import zi.objetivamobile.model.CategoriaAuxModel;
import zi.objetivamobile.model.CheckListModel;
import zi.objetivamobile.model.ClienteLaudoModel;
import zi.objetivamobile.model.FotoModel;

public class CheckListActivity extends Base  {

    private String mDescVistoria;
    private String observacao;
    private String observacaoAdicional;
    private Long mPIdLaudo;
    private Long mCodigoLaudo;
    private Integer numCategoria;
    private Bundle mBundle = null;
    private CategoriaBusiness mCategoriaBusiness = null;
    private CheckListBusiness mCheckListBusiness = null;

    private List<CategoriaAuxModel> mListPergutasCategoria = null;
    private CheckListAdapter mCheckListAdapter = null;
    private ListView mListCheck = null;
    private CheckBox mChkResposta = null;

    private TextView mTxtTitulo = null;
    private TextView mTxtSubTitulo = null;

    private Button mProximo = null;
    private Button mAnterior = null;
    private Button mBtnConcluir = null;
    private Button mBtnCancelar = null;
    private EditText mObservacao = null;
    private LinearLayout mLinObservacao = null;
    private LinearLayout mLinCheckList = null;

    private final List<CategoriaAuxModel> mListSelected = new ArrayList<CategoriaAuxModel>();

    int proximo = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);

        mListCheck = findViewById(R.id.check_list_item);
        mTxtTitulo = findViewById(R.id.txt_titulo);
        mTxtSubTitulo = findViewById(R.id.txt_subtitulo);
        mProximo = findViewById(R.id.btn_proximo);
        mAnterior = findViewById(R.id.btn_anterior);
        mBtnConcluir = findViewById(R.id.btn_concluir);
        mObservacao = findViewById(R.id.edt_observacao);

        mLinObservacao = findViewById(R.id.ln_observacao);
        mLinCheckList = findViewById(R.id.ln_check_item);

        mCategoriaBusiness = new CategoriaBusiness(this);
        mCheckListBusiness = new CheckListBusiness(this);

        mListPergutasCategoria = new ArrayList<CategoriaAuxModel>();

        mBundle = getIntent().getExtras();

        if(mBundle !=null){
            mDescVistoria = mBundle.getString("descVistoria");
            mPIdLaudo = mBundle.getLong("pNumLaudo");
            numCategoria = mBundle.getInt("numCategoria");
            mCodigoLaudo = mBundle.getLong("pCodigoLaudo");
        }

        mListPergutasCategoria = mCategoriaBusiness.getPerguntasCategoria(numCategoria);
        carregaSelecionado();

        tipoLaudo();
    }

    public void getControle() {
        doMontaTitulo();
        try {
            doMontaResposta();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mObservacao.setVisibility(View.GONE);
        mLinObservacao.setVisibility(View.GONE);
        if (proximo == 1)
            mAnterior.setVisibility(View.GONE);
    }


    public void doMontaTitulo() {
        try {
            mTxtTitulo.setText(mCategoriaBusiness.getCheckListTitulo().get(0).getDescricao());
            mTxtSubTitulo.setText(mCategoriaBusiness.getCheckListTitulo().get(1).getDescricao());
        } catch (Exception e) {
            final AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("ERRO" + e.getMessage());
        }
    }

    public void doMontaResposta() {

        mListPergutasCategoria.remove(0);

        mCheckListAdapter = new CheckListAdapter(this, R.layout.content_check_list, mListPergutasCategoria);
        mListCheck.setAdapter(mCheckListAdapter);
        mListCheck.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (l > 0) {
                    CheckListModel mCheckList = new CheckListModel();

                    CategoriaAuxModel model = (CategoriaAuxModel) adapterView.getItemAtPosition(i);
                    mChkResposta = view.findViewById(R.id.chk_resposta);

                    if (!model.isSelected()) {
                        model.setSelected(true);
                        mChkResposta.setChecked(true);
                        mListSelected.add(model);

                        //cria checklist
                        mCheckList.setIdLaudo(mPIdLaudo);
                        mCheckList.setCdLaudo(mCodigoLaudo);//troca de identificacao chave primaria para id de laudo_categoria ref
                        mCheckList.setCategoria(mDescVistoria);
                        mCheckList.setDescricao(model.getDescricao());
                        mCheckList.setIdResposta(model.getIdResposta());
                        mCheckList.setIdSubTitulo(model.getIdSubTitulo());
                        mCheckList.setIdTitulo(model.getIdTitulo());
                        mCheckList.setCdCategoria(model.getCd_categoria());
                        mCheckList.setIdUsuario(getCodUsuario());

                        mCheckList.setStatus("AT");

                        mCheckListBusiness.doGravaLaudo(mCheckList);

                    } else {
                        model.setSelected(false);
                        mChkResposta.setChecked(false);
                        mListSelected.remove(model);

                        mCheckListBusiness.doRemoverLaudo(mPIdLaudo, model.getIdTitulo(), model.getIdSubTitulo(), model.getIdResposta());

                    }
                }
            }
        });
    }


    public void nextCheck(View view) {

        itemSelecionados();

        if ("placa".equals(mDescVistoria)) {
            mListCheck.postInvalidate();
            mListPergutasCategoria = mCategoriaBusiness.getPerguntasCategoria(proximo);
            carregaSelecionado();
        }
        controleBtnProximo();
    }


    public void previousCheck(View view) {
        itemSelecionados();
        controleBtnAnterior();

        if ("placa".equals(mDescVistoria)) {
            mListCheck.postInvalidate();
            mListPergutasCategoria = mCategoriaBusiness.getPerguntasCategoria(proximo);
            carregaSelecionado();
        }
        mTxtSubTitulo.setVisibility(View.VISIBLE);
        mLinCheckList.setVisibility(View.VISIBLE);
        mListCheck.setVisibility(View.VISIBLE);
    }

    public void controleBtnProximo() {

        if (proximo == 13) {
            mProximo.setVisibility(View.GONE);
            mBtnConcluir.setVisibility(View.VISIBLE);
            mTxtTitulo.setText(getString(R.string.title_activity_lista_laudo_conclusao));
            mTxtSubTitulo.setVisibility(View.GONE);
            mLinCheckList.setVisibility(View.GONE);
            mListCheck.setVisibility(View.GONE);
            mLinObservacao.setVisibility(View.VISIBLE);
            mObservacao.setVisibility(View.VISIBLE);
            proximo = proximo + 1;
        } else if (proximo < 14) {
            mAnterior.setVisibility(View.VISIBLE);
            proximo = proximo + 1;
            mListPergutasCategoria = mCategoriaBusiness.getPerguntasCategoria(proximo);
            carregaSelecionado();
        } else {
            mLinObservacao.setVisibility(View.VISIBLE);
        }

    }

    public void controleBtnAnterior() {

        if (proximo != 1) {
            if (proximo == 14) {
                mBtnConcluir.setVisibility(View.GONE);
                mProximo.setVisibility(View.VISIBLE);
                proximo = proximo - 1;
            } else {
                proximo = proximo - 1;
            }
        }
        itemSelecionados();
    }

    public void confirmarConclusao(View view) {
        final Intent intent = new Intent(this, ListaLaudoActivity.class);
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

        doConcluirLaudo();
    }

    public boolean itemSelecionados() {

        if (mListSelected.size() != 0) {
            loadCheckList();
            return true;
        }else{
            return false;
        }
    }

    public void loadCheckList() {
        int index = 0;

        for (CategoriaAuxModel model : mListPergutasCategoria) {
            if (mCheckListBusiness.doPreviousLaudo(mPIdLaudo, model)) {
                mListPergutasCategoria.get(index).setSelected(true);
                mCheckListAdapter.notifyDataSetInvalidated();
            }
            index++;
        }
    }

    public void carregaSelecionado() {
        try {
            Thread.sleep(200);
            getControle();
            loadCheckList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, LaudoFotoActivity.class);
        intent.putExtra("pNumLaudo", mPIdLaudo);
        startActivity(intent);
    }

    public void tipoLaudo(){
        if (!"placa".equals(mDescVistoria)) {
            mBtnConcluir.setVisibility(View.VISIBLE);
            mAnterior.setVisibility(View.GONE);
            mProximo.setVisibility(View.GONE);
        }else{
            mBtnConcluir.setVisibility(View.GONE);
            mAnterior.setVisibility(View.VISIBLE);
            mProximo.setVisibility(View.VISIBLE);
        }
    }

    public void onClick_aprovado(View view){
        Intent intent = new Intent(this,ListaLaudoActivity.class);
        observacao = String.valueOf(mObservacao.getText());
        concluir(mPIdLaudo, 1, observacao);
        finish();
        startActivity(intent);
    }

    public void onClick_restricao(View view){
        Intent intent = new Intent(this,ListaLaudoActivity.class);
        observacao = String.valueOf(mObservacao.getText());
        concluir(mPIdLaudo, 2, observacao);
        finish();
        startActivity(intent);
    }

    public void onClick_reprovado(View view){
        Intent intent = new Intent(this,ListaLaudoActivity.class);
        observacao = String.valueOf(mObservacao.getText());
        concluir(mPIdLaudo, 3, observacao);
        finish();
        startActivity(intent);
    }

    public void concluir(Long numLaudo, int conclusao, String observacao){
        ClienteLaudoModel cliente = new ClienteLaudoModel();
        FotoData fotosLaudo = new FotoData(this);
        List<FotoModel> listaFotosLaudo = new ArrayList<>();

        cliente.setNumLaudo(numLaudo);
        cliente.setStatus(conclusao);
        cliente.setObservacao(observacao);
        //altera status para ser sincronizado
        listaFotosLaudo = fotosLaudo.getFoto(numLaudo);
        for (FotoModel model :listaFotosLaudo) {
            model.setCodLaudo(mCodigoLaudo);
            fotosLaudo.updateFotoLaudoConcluido(model);
        }

        mCheckListBusiness.concluirLaudo(mCodigoLaudo,observacao);
        mCheckListBusiness.concluir(cliente);
    }

    public void doConcluirLaudo() {
        final Intent intent = new Intent(this, ListaLaudoActivity.class);
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View alertView = inflater.inflate(R.layout.alert_content_conclusao, null);
        mBtnCancelar = alertView.findViewById(R.id.btn_conclusao_cancelar);

        alert.setTitle(getString(R.string.alert_title_conclusao));
        alert.setView(alertView);
        alert.setCancelable(false);

        final EditText mTititulo = (EditText) alertView.findViewById(R.id.edit_foto);

        mBtnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("pNumLaudo", mPIdLaudo);
                finish();
                startActivity(intent);
            }
        });

        alert.show();

    }

}