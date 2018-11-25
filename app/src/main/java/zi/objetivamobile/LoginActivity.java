package zi.objetivamobile;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import zi.objetivamobile.base.Base;
import zi.objetivamobile.business.LoginBusiness;
import zi.objetivamobile.evalid.Login;
import zi.objetivamobile.evalid.ServiceParam;
import zi.objetivamobile.model.AuthVistoriadorModel;
import zi.objetivamobile.sync.ClienteSync;

public class LoginActivity extends Base {

    private static final int REQUEST_READ_CONTACTS = 0;

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private LoginBusiness mLoginBusiness;

    AuthVistoriadorModel vistoriador = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mLoginBusiness = new LoginBusiness();
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    return true;
                }
                return false;
            }
        });

        getSupportActionBar().hide();

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        Button BtnLogin = (Button) findViewById(R.id.btn_login);
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(ServiceParam.URL_HANDLEBAR.concat(ServiceParam.PORT_HANDLEBAR).concat(ServiceParam.URL_IDENTIFICA_VISTORIADOR), mEmailView.getText().toString(), mPasswordView.getText().toString());
            }
        });

    }

    public void login(String url, String email, String password) {
        vistoriador = mLoginBusiness.authLogin(url, email, password);

        if(vistoriador.getNomeVistoriador() == null) {
            vistoriador.setRetorno(Login.USUARIO_INATIVO);
        }else{
            gravarDadosVistoriador(vistoriador);
        }

        switch (vistoriador.getRetorno()) {

            case Login.USUARIO_OK:
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("codUsuario", vistoriador.getCodUsuario());
                intent.putExtra("nomeUsuario", vistoriador.getNomeVistoriador().concat(" ").concat(vistoriador.getSobrenomeVistoriador()));
                startActivity(intent);
                finish();
                break;

            case Login.USUARIO_SENHA_INVALIDA:
                AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                alert.setTitle(getString(R.string.error_incorrect_title));
                alert.setMessage(getString(R.string.error_incorrect_password));
                alert.setIcon(R.mipmap.ic_emoticon_sad);

                // adding one button
                alert.setPositiveButton(R.string.btn_ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //close alert
                            }
                        });

                alert.show();
                break;
            case Login.USUARIO_SENHA_VAZIO:
                Toast toast = Toast.makeText(this, R.string.error_invalid_empty, Toast.LENGTH_LONG);
                toast.show();
                break;
            case Login.EMAIL_INVALIDO:
                mEmailView.requestFocus();
                mEmailView.setError(getString(R.string.error_invalid_email));
                break;
            case Login.USUARIO_INATIVO:
                mEmailView.requestFocus();
                mPasswordView.setText("");
                mEmailView.setError(getString(R.string.error_invalid_usuario_inativo));
                break;
            default:
                break;
        }
    }
}
