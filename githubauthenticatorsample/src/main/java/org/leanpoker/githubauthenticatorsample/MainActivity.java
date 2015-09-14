package org.leanpoker.githubauthenticatorsample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {

    public static String OAUTH_URL = "https://github.com/login/oauth/authorize";
    public static String OAUTH_ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";

    public static String CLIENT_ID = "3826c0b84ae756bb1e5f";
    public static String CLIENT_SECRET = "24d2417f9c803bb03fb20190ca439d92d1a5f6d3";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = OAUTH_URL + "?client_id=" + CLIENT_ID + "&callback_uri=leanpoker://github-login";

        WebView webview = (WebView)findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                String accessTokenFragment = "access_token=";
//                String accessCodeFragment = "code=";
//
//                // We hijack the GET request to extract the OAuth parameters
//
//                if (url.contains(accessTokenFragment)) {
//                    // the GET request contains directly the token
//                    String accessToken = url.substring(url.indexOf(accessTokenFragment));
//                    TokenStorer.setAccessToken(accessToken);
//
//
//                } else if (url.contains(accessCodeFragment)) {
//                    Log.d("ASD", url);
//                    // the GET request contains an authorization code
//                    String accessCode = url.substring(url.indexOf(accessCodeFragment));
//                    TokenStorer.setAccessCode(accessCode);
//
//
//                    String query = "client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&code=" + accessCode;
//                    view.postUrl(OAUTH_ACCESS_TOKEN_URL, query.getBytes());
//                }
//            }

            @Override
            public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
                if (url.startsWith("leanpoker://github-login")) {
                    String accessCode = url.substring(url.indexOf("code="));
                    TokenStorer.setAccessCode(accessCode);
                    Intent intent = new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(url),
                            getApplicationContext(),
                            DummyActivity.class
                    );
                    startActivity(intent);
//                    String query = "client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&" + accessCode;
//                    view.postUrl(OAUTH_ACCESS_TOKEN_URL, query.getBytes());
                    return true;
                } else if (url.contains("access_token=")) {
                    String accessToken = url.substring(url.indexOf("access_token="));
                    TokenStorer.setAccessToken(accessToken);
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webview.loadUrl(url);
        Log.d("ASD", url);
    }

    private void showToast(String message) {
        Toast.makeText(this, "Deep Link: " + message, Toast.LENGTH_SHORT).show();
    }
}
