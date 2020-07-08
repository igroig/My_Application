package com.example.myapplication;

import android.graphics.Bitmap;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.webkit.WebViewClientCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends DrawerToolbarActivity {

    private WebView myWebView;

    private static final String TAG = "MainActivity";
    public final String MAIN_URL="https://alta.ge/";
    public final String CONTACT_URL="https://alta.ge/contacts.html/";

    private String url;

    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        url=MAIN_URL;


        progressBar = (ProgressBar)findViewById(R.id.progressbar);

        myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new MyWebViewClient());

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        myWebView.loadUrl(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    protected boolean onNavigationItemClick(MenuItem menuItem) {
        navigateTo(menuItem.getItemId());
        return true;
    }


    private void navigateTo(int id) {
//        navigationView.setCheckedItem(id);
        switch (id) {
            case R.id.nav_home:
                if (!url.equals(MAIN_URL)) {
                    url=MAIN_URL;
                    myWebView.loadUrl(url);
                }
                break;
            case R.id.nav_contact:
                if (!url.equals(CONTACT_URL)) {
                    url=CONTACT_URL;
                    myWebView.loadUrl(url);
                }
                break;
            case R.id.nav_logout:
                break;
            //....
        }
    }

    @Override
    protected void onCreateNavigationMenu(Menu navigationMenu) {
        for (int i = 0; i < navigationMenu.size(); i++) {
            MenuItem item = navigationMenu.getItem(i);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (url.equals(CONTACT_URL)){  // ეს ნაწილი შესაცვლელია
            url=MAIN_URL;
            myWebView.loadUrl(url);
        }
        else if (myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private class MyWebViewClient extends WebViewClientCompat {


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.d(TAG, "onPageStarted: ");
            if (progressBar.getVisibility()!= View.VISIBLE) {
                progressBar.setVisibility(View.VISIBLE);
            }
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Log.d(TAG, "onPageFinished: ");
            progressBar.setVisibility(View.GONE);
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Log.d(TAG, "onReceivedError: ");
            progressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, "Error:" + description, Toast.LENGTH_SHORT).show();

        }
    }
}