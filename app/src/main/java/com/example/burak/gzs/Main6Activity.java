package com.example.burak.gzs;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Main6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);


        WebView webview = (WebView) findViewById(R.id.web_gaz);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("http://api.thingspeak.com/channels/385862/charts/3?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=60&type=line&update=15");

        final ProgressDialog progress = ProgressDialog.show(this, "Sıcaklık Nem Gaz Durumu", "Yükleniyor....", true);
        progress.show();
        webview.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Toast.makeText(getApplicationContext(), "Sayfa yüklendi", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getApplicationContext(), "Bir hata oluştu", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });
    }
}
