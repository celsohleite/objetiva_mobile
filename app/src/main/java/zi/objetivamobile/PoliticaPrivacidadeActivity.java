package zi.objetivamobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PoliticaPrivacidadeActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politica_privacidade);

        webView = findViewById(R.id.wv_politica_privacidade);
        webView.loadUrl("http://www.govf.com.br/politica_privacidade.html");
    }
}
