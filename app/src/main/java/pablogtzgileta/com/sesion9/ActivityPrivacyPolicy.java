package pablogtzgileta.com.sesion9;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class ActivityPrivacyPolicy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        WebView mWebView = (WebView) findViewById(R.id.activity_privacy_policy_webView);
        WebSettings webSettings = mWebView.getSettings();

        mWebView.loadUrl("file:///android_asset/PrivacyPolicy.html");

        webSettings.setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
    }

    public class WebAppInterface {
        Context mContext;
        WebAppInterface(Context c) {
            mContext = c;
        }
        /** Show a toast from the web page */
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
    }
}
