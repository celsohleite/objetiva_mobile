package zi.objetivamobile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zi.objetivamobile.adapter.MenuAdapter;
import zi.objetivamobile.base.Base;
import zi.objetivamobile.business.CategoriaBusiness;
import zi.objetivamobile.business.LaudoBusiness;
import zi.objetivamobile.data.CheckListData;
import zi.objetivamobile.data.FotoData;
import zi.objetivamobile.evalid.ServiceParam;
import zi.objetivamobile.helper.EnvioFotoHelper;
import zi.objetivamobile.imobile.IActionsListener;
import zi.objetivamobile.model.CheckListModel;
import zi.objetivamobile.model.ClienteLaudoModel;
import zi.objetivamobile.model.FotoModel;
import zi.objetivamobile.model.MenuModel;
import zi.objetivamobile.sync.ClienteSync;
import zi.objetivamobile.sync.EnvLaudoSync;
import zi.objetivamobile.util.Image64Util;

public class HomeActivity extends Base
        implements NavigationView.OnNavigationItemSelectedListener,
        IActionsListener<EnvioFotoHelper.ErrorType, EnvioFotoHelper.ActionsType> {

    private NavigationView mMenuHeader;
    private TextView mTitleVistoriador;
    private TextView mTitleEmailVistoriador;
    private ImageView mImageVistoriador;

    private List<FotoModel> mListFotoModel;
    private FotoData mFotoData = null;

    private Context mContext;
    private FotoData fotoData;
    private Image64Util base64Util;
    private static String mDirFoto = "/laudos_objetiva/";
    RecyclerView mRecyclerView;

    private MenuAdapter mMenuAdapter;
    private List<MenuModel> mMenu;

    //Sync
    private EnvLaudoSync mLaudoSync = null;
    private ClienteSync mClienteSync = null;

    private CheckListData mCheckListData = null;
    private List<CheckListModel> mListCheckList = null;

    private LaudoBusiness mLaudoBusiness;
    private CategoriaBusiness mCategoriaBusiness;

    private ProgressDialog mProgressDialog;

    private NavigationView navigationView;

    private Bundle mBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTop(2);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        mMenuHeader = findViewById(R.id.nav_view);
        mTitleVistoriador = mMenuHeader.getHeaderView(0).findViewById(R.id.nome_vistoriador);
        mTitleVistoriador.setText(getNomeUsuario());

        mTitleEmailVistoriador = mMenuHeader.getHeaderView(0).findViewById(R.id.email_vistoriador);
        mTitleEmailVistoriador.setText(getEmailVistoriador());

        mImageVistoriador = mMenuHeader.getHeaderView(0).findViewById(R.id.image_vistoriador);
        mImageVistoriador.setImageResource(R.mipmap.splash_objetiva);
        mImageVistoriador.setAdjustViewBounds(true);

        mCheckListData = new CheckListData(this);

        fotoData = new FotoData(this);
        base64Util = new Image64Util();

        mLaudoBusiness = new LaudoBusiness(this);
        mCategoriaBusiness = new CategoriaBusiness(this);

        mClienteSync = new ClienteSync(this);

        mProgressDialog = new ProgressDialog(HomeActivity.this);

        navigationView = findViewById(R.id.nav_view);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setupDrawerContent(navigationView);

        mFotoData = new FotoData(this);
        mListFotoModel = mListFotoModel = new ArrayList<FotoModel>();
        mListFotoModel = this.mFotoData.getFoto(null);

        setPermissaoAndroid_40();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_laudo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_dados:
                doRecebeDados();
                //sendDadosLaudo();
                break;
            case R.id.politica_privacidade:
                Intent intentPrivacidade = new Intent(this, PoliticaPrivacidadeActivity.class);
                startActivity(intentPrivacidade);
                break;
            case R.id.action_logout:
                Intent intent = new Intent(this, LoginActivity.class);
                finish();
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void sincronizarDados(View view) {
        if(mListFotoModel.size() > 0) {
            loading(true);
            //envio dos dados do cliente
            syncDadosCliente();
            //envio das informações dos laudos
            sendDadosLaudo();
            //envio das fotos
            disparaEnvioSync();

        }
    }

    private void syncDadosCliente() {
        List<ClienteLaudoModel> mClienteLaudo;
        mClienteLaudo = new ArrayList<ClienteLaudoModel>();

        mClienteLaudo = mLaudoBusiness.getLaudoInfoCliente(null, getcodVistoriador(), 1); // caso o laudo for realizado o valor da tela recebe o valor 1

        //é possivel controlar o tempo de resposta
        for (ClienteLaudoModel model : mClienteLaudo) {
            EnvLaudoSync.sendLaudo(ServiceParam.URL_HANDLEBAR.concat(ServiceParam.PORT_HANDLEBAR).concat(ServiceParam.URL_LAUDO_ENVIO), model);
        }
    }

    private void sendDadosLaudo() {
        mListCheckList = new ArrayList<CheckListModel>();
        mListCheckList = mCheckListData.getCheckListItens(getCodUsuario());

        for (CheckListModel model : mListCheckList) {
            EnvLaudoSync.sendCategoria(ServiceParam.URL_HANDLEBAR.concat(ServiceParam.PORT_HANDLEBAR).concat(ServiceParam.URL_CATEGORIA_ENVIO), model);
        }
    }

    private List<MenuModel> getListMenu() {
        List<MenuModel> itemMenu = new ArrayList<MenuModel>();
        MenuModel itemFoto = new MenuModel(1l, "foto", 111);
        itemMenu.add(itemFoto);

        return itemMenu;
    }

    private void setupRecycle() {
        //configurando o layout
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mMenuAdapter = new MenuAdapter(getListMenu(), this);
        mRecyclerView.setAdapter(mMenuAdapter);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL));

    }

    public void onFotoLaudo(View view) {
        Intent intent = new Intent(this, ListaLaudoActivity.class);
        intent.putExtra("nomeUsuario", getNomeUsuario());
        startActivity(intent);
    }

    public void onLaudoRealizado(View view) {
        Intent intent = new Intent(this, LaudoRealizadoActivity.class);
        startActivity(intent);
    }

    public void onLaudoDelivery(View view) {
        Intent intent = new Intent(this, LaudoDeliveryActivity.class);
        startActivity(intent);
    }

    private void doRecebeDados(){
        mClienteSync.syncCliente(getCodUsuario());
        mCategoriaBusiness.syncCategorias();

    }

    private void disparaEnvioSync() {
            if(mListFotoModel.size() > 0) {
                new EnvioFotoHelper(this, this).execute();
            }
    }

    private void loading(boolean exibir){

        if(exibir) {
            mProgressDialog.setMax(100);
            mProgressDialog.setTitle(R.string.title_loading_envia_fotos);
            mProgressDialog.setMessage(getString(R.string.title_loading_aguarde_envia_dados));
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        } else {
            mProgressDialog.dismiss();
        }
    }

    private void setPermissaoAndroid_40(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_politica_privacidade:
                Intent intent = new Intent(this,PoliticaPrivacidadeActivity.class);
                startActivity(intent);
                break;
        }

        return true;
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);

                        return true;
                    }
                });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onErrorBusiness(EnvioFotoHelper.ErrorType type, String errorMessage) {}

    @Override
    public void onErrorBusiness(Throwable error) {}

    @Override
    public void doResultBusiness(EnvioFotoHelper.ActionsType type, Object object) {
        switch (type){
            case onSartLoading:
                loading(true);
                break;
            case onStopLoading:
                loading(false);
                break;
        }
    }
}