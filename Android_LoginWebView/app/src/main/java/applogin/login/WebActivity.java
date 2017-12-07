package applogin.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    private WebView webViewTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webViewTest = (WebView) findViewById(R.id.webView);
        webViewTest.getSettings().setJavaScriptEnabled(true);
        webViewTest.loadUrl("http://www.ufc.br/");
    }


}
