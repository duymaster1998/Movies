package edu.xda.abc.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import edu.xda.abc.R;


//cái nào mở đi
public class MoviePlayActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
//        getActionBar().hide();
        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        String uri ="<iframe width=\"100%\" height=\"100%\" src=\"" +
                "https://www.youtube.com/embed/"+getIntent().getExtras().getString("urimovie")+"\"frameborder=\"0\" " +
                "allowfullscreen></iframe>";
        webView.loadData(uri,"text/html","utf-8");
    }
}
