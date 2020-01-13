package edu.xda.abc.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import edu.xda.abc.R;


public class MainActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.webview);
        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        String html ="<iframe width=\"100%\" height=\"100%\" src=\"" +
                "https://www.youtube.com/embed/8Lq3HyBCuAA\"frameborder=\"0\" " +
                "allowfullscreen></iframe>";
        webView.loadData( html , "text/html",  "UTF-8");
        webView.getSettings().setJavaScriptEnabled(true);
    }
    void setFullScreen(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
