package nl.fressh.nederlandviertfeest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

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
        aboutWebsite.loadUrl("http://app.veldhovenviertfeest.nl/contact.php");

        aboutWebsite.setWebChromeClient(new WebChromeClient() {});
        WebSettings settings = aboutWebsite.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        aboutWebsite.addJavascriptInterface(new JavaScriptInterface(this), "Android");
    }

    public class JavaScriptInterface {
        Context mContext;
        JavaScriptInterface(Context c) {
            mContext = c;
        }
        @JavascriptInterface
        public String getFromAndroid() {
            String str = android.os.Build.MODEL + " " + android.os.Build.MANUFACTURER + " " + BuildConfig.VERSION_NAME + " " + BuildConfig.VERSION_CODE;
            return str;
        }
    }

}