package org.leanpoker.githubauthenticatorsample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.airbnb.deeplinkdispatch.DeepLink;

import java.util.UUID;

//@DeepLink("org.leanpoker.githubauthenticatorsample://github-login")
public class MainActivity extends AppCompatActivity {

    public static String OAUTH_URL = "https://github.com/login/oauth/authorize";
    public static String OAUTH_ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";

    public static String CLIENT_ID = "3826c0b84ae756bb1e5f";
    public static String CLIENT_SECRET = "24d2417f9c803bb03fb20190ca439d92d1a5f6d3";
    public static String CALLBACK_URL = "http://localhost";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = OAUTH_URL + "?client_id=" + CLIENT_ID + "&callback_uri=leanpoker://github-login";

        WebView webview = (WebView)findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                String accessTokenFragment = "access_token=";
                String accessCodeFragment = "code=";

                // We hijack the GET request to extract the OAuth parameters

                if (url.contains(accessTokenFragment)) {
                    // the GET request contains directly the token
                    String accessToken = url.substring(url.indexOf(accessTokenFragment));
                    TokenStorer.setAccessToken(accessToken);


                } else if (url.contains(accessCodeFragment)) {
                    Log.d("ASD", url);
                    // the GET request contains an authorization code
                    String accessCode = url.substring(url.indexOf(accessCodeFragment));
                    TokenStorer.setAccessCode(accessCode);


                    String query = "client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&code=" + accessCode;
                    view.postUrl(OAUTH_ACCESS_TOKEN_URL, query.getBytes());
                }
            }
        });
        webview.loadUrl(url);
        Log.d("ASD", url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showToast(String message) {
        Toast.makeText(this, "Deep Link: " + message, Toast.LENGTH_SHORT).show();
    }
}
