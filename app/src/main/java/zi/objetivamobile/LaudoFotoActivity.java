package zi.objetivamobile;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import zi.objetivamobile.adapter.FotoAdapter;
import zi.objetivamobile.base.Base;
import zi.objetivamobile.business.CategoriaBusiness;
import zi.objetivamobile.business.FotoBusiness;
import zi.objetivamobile.data.ClienteLaudoData;
import zi.objetivamobile.data.FotoData;
import zi.objetivamobile.evalid.FotoValid;
import zi.objetivamobile.model.CategoriaAuxModel;
import zi.objetivamobile.model.ClienteLaudoModel;
import zi.objetivamobile.model.FotoModel;
import zi.objetivamobile.sync.CategoriaSync;
import zi.objetivamobile.util.Image64Util;

/**
 * author : celso henrique lete
 * descrição : classe reservada apenas para o processamento de imagens
 */
public class LaudoFotoActivity extends Base {

    final int REQUEST_PERMISSIONS_CODE = 128;
    private String diretorioImageLaudos = null;
    private static final int TIRAR_FOTO = 0;
    private Image64Util base64Util = null;
    private Bundle mBundle = null;
    private Button mBtnCheckList = null;
    private CategoriaSync mSync = null;

    private ImageView mDisplay_foto = null;

    private Long mNumLaudo;
    private String mTitulo_foto;
    private String mTipoLaudo;
    private int numCategoria;
    private Long mCodigoLaudo;

    //adapter
    FotoAdapter fotoAdapter = null;

    //passar para business
    private FotoData fotoData = null;
    private FotoModel mFotoModel = null;
    private List<FotoModel> mListFotosLaudo = null;

    private List<Bitmap> mListBitmapLaudos = null;
    private GridView mGridViewImage = null;

    private CategoriaBusiness mCategoriaBusiness = null;
    private FotoBusiness mFotoBusiness = null;

    private Uri imageToUploadUri;

    private static String mDirFoto="/laudos_objetiva/";
    private static String mDirFotoApp="/uploads/laudo/";

    //declaracao temporaria
    ClienteLaudoData clienteLaudoData = null;

    public LaudoFotoActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_laudo_foto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAdicionaItemFoto(view);
            }
        });

        fotoData = new FotoData(this);
        mFotoBusiness = new FotoBusiness(this);
        mCategoriaBusiness = new CategoriaBusiness(this);

        base64Util = new Image64Util();

        diretorioImageLaudos = "laudos_objetiva";
        mGridViewImage = findViewById(R.id.grid_laudos);
        mBtnCheckList = findViewById(R.id.btn_check_list);

        mBtnCheckList.setBackgroundResource(R.color.colorObjetivaRedBg);

        mBundle = getIntent().getExtras();

        if (mBundle.get("pNumLaudo") != null) {
            mNumLaudo = mBundle.getLong("pNumLaudo");
            mTipoLaudo = mBundle.getString("tipoLaudo");
            mCodigoLaudo = mBundle.getLong("codigoLaudo");

        }

        doCarregaFoto(mNumLaudo);

        clienteLaudoData = new ClienteLaudoData(this);

        onPermissao();
    }

    String nomeFotoLaudo = null;
    public void tirarFoto() {

        //permissão para android 8.0
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        Date data = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HHmmss");
        Intent chooserIntent = null;
        nomeFotoLaudo = mNumLaudo.toString().concat("-").concat(sdf.format(data).toString().concat("-").concat(getTipoLaudo(mNumLaudo)).concat(".jpg"));
        File diretorioLaudos = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), diretorioImageLaudos);
        diretorioLaudos.canWrite();
        boolean dir = diretorioLaudos.mkdir();

        if (dir || diretorioLaudos.exists()) {

            File f = new File(diretorioLaudos, nomeFotoLaudo);

            //para versão abaixo 6.0
            chooserIntent = new Intent("android.media.action.IMAGE_CAPTURE");
            //necessario inclusão para versão acima da 6.0
            chooserIntent.putExtra(MediaStore.ACTION_IMAGE_CAPTURE,Uri.fromFile(f));
            chooserIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));

            imageToUploadUri = Uri.fromFile(f);
            startActivityForResult(chooserIntent, TIRAR_FOTO);
        } else {
            //erro caso nao crie a pasta
            Toast.makeText(getApplicationContext(), getString(R.string.err_cria_diretorio), Toast.LENGTH_LONG).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFotoModel = new FotoModel();
        String image = null;
        doCarregaFoto(mNumLaudo);

        if (requestCode == TIRAR_FOTO) {
            if (resultCode == RESULT_OK) {

                if (imageToUploadUri != null) {
                    for (int i = 0; i < mListFotosLaudo.size(); i++) {
                        if ("S".equals(mListFotosLaudo.get(i).getStatus())) {

                            mFotoModel.setStatus("N");
                            mFotoModel.setCodLaudo(mCodigoLaudo);
                            mFotoModel.setIdLaudo(mNumLaudo);
                            mFotoModel.setNomeFoto(nomeFotoLaudo);
                            mFotoModel.setFotoArquivo(image);
                            mFotoModel.setDescricao(mTitulo_foto);
                            mFotoModel.setTmpFoto(imageToUploadUri.toString());
                            mFotoModel.setPathArquivo(mDirFotoApp);
                            mFotoModel.setTipoLaudo(mTipoLaudo);
                            fotoData.updateFoto(mFotoModel, mListFotosLaudo.get(i).getIdCliente());

                            doCarregaFoto(mNumLaudo);
                            break;
                        }
                        mGridViewImage.invalidateViews();
                    }
                }
            } else if (requestCode == RESULT_CANCELED) {
                Toast.makeText(getBaseContext(), "A captura foi cancelada", Toast.LENGTH_SHORT);
            } else {
                Toast.makeText(getBaseContext(), "A câmera foi fechada", Toast.LENGTH_SHORT);
            }
        }
    }

    public void doCarregaFoto(final Long mNumLaudo) {

        mListFotosLaudo = new ArrayList<FotoModel>();
        mListBitmapLaudos = new ArrayList<Bitmap>();

        mListFotosLaudo = fotoData.getFoto(mNumLaudo);
        fotoAdapter = new FotoAdapter(this, mListFotosLaudo);
        mGridViewImage.setAdapter(fotoAdapter);

        //long click event
        mGridViewImage.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                dialogRemoverFoto(mNumLaudo, mListFotosLaudo.get(pos).getNomeFoto());
                return true;
            }
        });

        //click fast event
        mGridViewImage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int pos, long id) {
                if ("N".equals(mListFotosLaudo.get(pos).getStatus())) {
                    doCarregaFotoDisplay(pos, mListFotosLaudo.get(pos).getDescricao());
                    Log.v("long clicked", "pos: " + pos);
                } else {
                    tirarFoto();
                }
            }
        });

        tipoCheckList();
    }

    public void dialogRemoverFoto(final Long mNumLaudo, final String foto){
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setCancelable(false);
        alert.setMessage(getString(R.string.alert_remover_foto));
        alert.setPositiveButton(getString(R.string.alert_btn_sim), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                removerFoto(mNumLaudo, foto);
                tipoCheckList();
            }
        });
        alert.setNegativeButton(getString(R.string.alert_btn_nao), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.show();
    }

    public void removerFoto(Long mNumLaudo, String foto){
        mFotoBusiness.removerFoto(mNumLaudo, foto);
        doCarregaFoto(mNumLaudo);
    }

    public void tipoCheckList() {
        String tipoLaudo = getTipoLaudo(mNumLaudo);
        switch (tipoLaudo) {
            case "placa":
                onLoadHabilitaCheckList(mListFotosLaudo.size(), FotoValid.QTDE_HABI_PLACA);
                break;
            case "cambio":
                onLoadHabilitaCheckList(mListFotosLaudo.size(), FotoValid.QTDE_HABI_CAMBIO);
                break;
            case "motor":
                onLoadHabilitaCheckList(mListFotosLaudo.size(), FotoValid.QTDE_HABI_MOTOR);
                break;
        }
    }


    public void doAdicionaItemFoto(View view) {
        Bitmap mNovaFoto = null;
        boolean isAddFoto = false;
        mFotoModel = new FotoModel();
        mNovaFoto = BitmapFactory.decodeResource(getResources(),R.mipmap.camera);
        //informacoes sobre o laudo
        mFotoModel.setIdLaudo(mNumLaudo);
        mTitulo_foto = doRegistraNomeFotoPlaca(mListFotosLaudo.size());
        mFotoModel.setDescricao(mTitulo_foto);

        if (mListFotosLaudo.size() > 0) {
            for (FotoModel model : mListFotosLaudo) {
                if ("N".equals(model.getStatus())) {
                    isAddFoto = true;
                } else if ("S".equals(model.getStatus())) {
                    isAddFoto = false;
                }
            }
        } else {
            isAddFoto = true;
        }

        if (isAddFoto) {
            mListBitmapLaudos.add(mNovaFoto);
            mFotoModel.setStatus("S");
            mFotoModel.setNomeFoto(base64Util.encodeImageBase64Str(String.valueOf(new Date())));
            mListFotosLaudo.add(mFotoModel);
            fotoData.insert(mFotoModel);
        }

        doCarregaFoto(mNumLaudo);
        mGridViewImage.invalidateViews();
    }

    //cria visualizacao de foto
    public void doCarregaFotoDisplay(int pos, String desc) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View alertView = inflater.inflate(R.layout.alert_content_display_foto, null);

        alert.setTitle(mListFotosLaudo.get(pos).getDescricao());
        alert.setView(alertView);
        alert.setCancelable(false);
        //carrega foto para apresentacao
        mDisplay_foto = (ImageView) alertView.findViewById(R.id.imgv_foto_laudo);
        mDisplay_foto.setImageBitmap(base64Util.decodeFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()
                .concat(mDirFoto)
                .concat(mListFotosLaudo.get(pos).getNomeFoto()),"view"));

        alert.setNegativeButton(R.string.alert_btn_ok, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();
    }

    public void onPermissao() {
        // If request is cancelled, the result arrays are empty.
        if (ContextCompat.checkSelfPermission(LaudoFotoActivity.this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(LaudoFotoActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSIONS_CODE);
            ActivityCompat.requestPermissions(LaudoFotoActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSIONS_CODE);
            ActivityCompat.requestPermissions(LaudoFotoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        } else {
            ActivityCompat.requestPermissions(LaudoFotoActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSIONS_CODE);
            ActivityCompat.requestPermissions(LaudoFotoActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSIONS_CODE);
            ActivityCompat.requestPermissions(LaudoFotoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    public void onLoadHabilitaCheckList(int qtdeAtivar, int tipoCheckList) {
        boolean habilita = false;
        //determinar via parametro a quantidade de fotos para habilitar o botao de checklist
        if (qtdeAtivar >= tipoCheckList) {
            mBtnCheckList.setBackgroundResource(R.color.colorObjetivaVerde);
            mBtnCheckList.setEnabled(true);
        } else {
            mBtnCheckList.setBackgroundResource(R.color.colorObjetivaRedBg);
            mBtnCheckList.setEnabled(false);
        }
    }


    public String doRegistraNomeFotoPlaca(int numFoto) {

        String descricao = null;

        if ("placa".equals(getTipoLaudo(mNumLaudo))) {

            switch (numFoto) {
                case FotoValid.CRVCRLV:
                    descricao = "CRV/CRLV";
                    break;
                case FotoValid.PLACA:
                    descricao = "PLACA";
                    break;
                case FotoValid.TRASEIRA:
                    descricao = "TRASEIRA";
                    break;
                case FotoValid.CHASSIS:
                    descricao = "CHASSIS";
                    break;
                case FotoValid.MOTOR:
                    descricao = "MOTOR";
                    break;
                case FotoValid.CAMBIO:
                    descricao = "CAMBIO";
                    break;
                case FotoValid.FRENTE:
                    descricao = "FRENTE";
                    break;
                default:
                    descricao = "OUTRAS ( " + numFoto + " )";
                    break;
            }

        } else if ("motor".equals(getTipoLaudo(mNumLaudo))) {
            descricao = "MOTOR ( " + numFoto + " )";
        } else if ("cambio".equals(getTipoLaudo(mNumLaudo))) {
            descricao = "CAMBIO ( " + numFoto + " )";
        }

        return descricao;
    }

    public void doCheckList(View view) {

        List<CategoriaAuxModel> mListPergutasCategoria = null;
        numCategoria = mCategoriaBusiness.getNumCategoria(getTipoLaudo(mNumLaudo));

        mListPergutasCategoria = mCategoriaBusiness.getPerguntasCategoria(numCategoria);
        final Intent intentHome = new Intent(this, HomeActivity.class);

        final Intent intent = new Intent(this, CheckListActivity.class);
        intent.putExtra("descVistoria", getTipoLaudo(mNumLaudo));
        intent.putExtra("pNumLaudo", mNumLaudo);
        intent.putExtra("numCategoria", numCategoria);
        intent.putExtra("pCodigoLaudo", mCodigoLaudo);

        if (mListPergutasCategoria.size() > 0) {
            finish();
            startActivity(intent);
        } else {
            final AlertDialog.Builder alert = new AlertDialog.Builder(this);

            alert.setMessage(getString(R.string.alert_categoria_inst_sync));
            alert.setNegativeButton(R.string.alert_btn_ok, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                    startActivity(intentHome);
                }
            }).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,ListaLaudoActivity.class);
        startActivity(intent);
    }

}
