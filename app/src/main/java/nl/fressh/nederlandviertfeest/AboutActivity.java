package nl.fressh.nederlandviertfeest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Maikel on 15-11-16.
 */

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        WebView aboutWebsite = (WebView) findViewById(R.id.webView);
        aboutWebsite.getSettings().setJavaScriptEnabled(true);
        aboutWebsite.loadUrl("https://www.google.nl");
    }

}