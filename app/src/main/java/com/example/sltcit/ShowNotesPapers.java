package com.example.sltcit;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class ShowNotesPapers extends AppCompatActivity {

    WebView wwv1;
    String url=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_np);

        wwv1=(WebView) findViewById(R.id.aaaa);

        String data = null;
        Bundle extras = getIntent().getExtras();

        if(extras.getString("SUB") == null) {

            Toast.makeText(getApplicationContext(),"Nodata",Toast.LENGTH_SHORT).show();
        }
        else {
            data= extras.getString("SUB");

        }

        if(data.equals("Sub1N"))
        {
            url="https://drive.google.com/drive/folders/1RNm_0JBhDXrvgQJ5DCEjzWH4fyL2wUDL?usp=sharing";
            Toast.makeText(getApplicationContext(),"Please Wait!",Toast.LENGTH_SHORT).show();
        }
        if(data.equals("Sub1P"))
        {
            url="https://drive.google.com/drive/folders/1zLT_AzIk4gEJJgxvfntl9lC2oxN0mabD?usp=sharing";
            Toast.makeText(getApplicationContext(),"Please Wait!",Toast.LENGTH_SHORT).show();
        }
        if(data.equals("Sub2N"))
        {
            url="https://drive.google.com/drive/folders/1KoB9P2my5UIoju-P2f8B_q-AMRQU2kUu?usp=sharing";
            Toast.makeText(getApplicationContext(),"Please Wait!",Toast.LENGTH_SHORT).show();
        }
        if(data.equals("Sub2P"))
        {
            url="https://drive.google.com/drive/folders/1V29XcJ9Ueb1bUepQrvrK-5ekD3kSs720?usp=sharing";
            Toast.makeText(getApplicationContext(),"Please Wait!",Toast.LENGTH_SHORT).show();
        }
        
        Load();
    }

    static class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            return true;
        }
    }

    private void Load()
    {

        //Runtime External storage permission for saving download files
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED) {
                Log.d("permission", "permission denied to WRITE_EXTERNAL_STORAGE - requesting it");
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, 1);
            }
        }

        //String url="https://lms.sltc.ac.lk/login/index.php?username="+userid+"&rememberusername=0";
        //String url="https://lms.nibmworldwide.com/login/index.php/";

        wwv1.setWebViewClient(new MyBrowser());
        wwv1.getSettings().setLoadsImagesAutomatically(true);
        wwv1.getSettings().setJavaScriptEnabled(true);
        wwv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wwv1.loadUrl(url);


        //wv1.loadUrl("javascript:document.getElementsById('username').value = 'username'");
        //wv1.loadUrl("javascript:document.getElementsByName('password').value = 'xyz'");
        //wv1.loadUrl("javascript:document.forms['Login'].submit()");

        /*wv1.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });*/

        wwv1.setDownloadListener(new DownloadListener()
        {
            @Override
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimeType,
                                        long contentLength) {
                DownloadManager.Request request = new DownloadManager.Request(
                        Uri.parse(url));
                request.setMimeType(mimeType);
                String cookies = CookieManager.getInstance().getCookie(url);
                request.addRequestHeader("cookie", cookies);
                request.addRequestHeader("User-Agent", userAgent);
                request.setDescription("Downloading File...");
                request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimeType));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(
                        Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(
                                url, contentDisposition, mimeType));
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Toast.makeText(getApplicationContext(), "Downloading File", Toast.LENGTH_LONG).show();
            }});



    }


    /*@Override
    public void onBackPressed() {

        if(wv1.canGoBack())
        {
            wv1.goBack();
        }
        else
        {
            finish();
        }

    }*/
}

