package fr.olivierdumoulin.sitrepdum;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends Activity {

    private static final String SITREP_FILE =
            "file:///storage/emulated/0/Obsidian/ObsiDum/_INDEX/SitRepDum/SitRepDum_V6.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!Environment.isExternalStorageManager()) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        }

        WebView webView = new WebView(this);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);

        webView.loadUrl(SITREP_FILE);

        setContentView(webView);
    }
}
