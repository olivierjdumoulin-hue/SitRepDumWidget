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
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);

        webView.loadUrl(
            "content://com.lcg.Xplore.FileContent/uid/file%3A%2F%2F%2Fstorage%2Femulated%2F0%2FObsidian%2FObsiDum%2F_INDEX%2FSitRepDum%2FSitRepDum_V6.html"
        );

        setContentView(webView);
    }
}
