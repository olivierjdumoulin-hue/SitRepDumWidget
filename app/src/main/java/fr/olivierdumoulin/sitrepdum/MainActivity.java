package fr.olivierdumoulin.sitrepdum;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView webView = new WebView(this);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);

        webView.loadUrl(
            "file:///storage/emulated/0/Obsidian/ObsiDum/_INDEX/SitRepDum/SitRepDum_V6.html"
        );

        setContentView(webView);
    }
}
