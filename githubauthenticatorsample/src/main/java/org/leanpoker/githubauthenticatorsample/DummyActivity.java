package org.leanpoker.githubauthenticatorsample;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by tmolnar on 14/09/15.
 */
public class DummyActivity extends Activity {

    public static final String TAG = DummyActivity.class.getSimpleName();

    public static String OAUTH_ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";

    public static String CLIENT_ID = "3826c0b84ae756bb1e5f";
    public static String CLIENT_SECRET = "24d2417f9c803bb03fb20190ca439d92d1a5f6d3";

    private WebView mWebView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);

        if (getIntent() != null && getIntent().getData() != null) {
            final Uri uri = getIntent().getData();
            String query = uri.getEncodedQuery();

            String accessCode = query.substring(query.indexOf("code="));
            TokenStorer.setAccessCode(accessCode);

            mWebView = (WebView) findViewById(R.id.dummy_webview);
            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
                    if (url.startsWith("leanpoker://")) {
                        Log.d(TAG, "That's something");
                    }
                    return super.shouldOverrideUrlLoading(view, url);
                }
            });

            String queryString = "client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&" + accessCode;
//            mWebView.postUrl(OAUTH_ACCESS_TOKEN_URL, queryString.getBytes());

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(OAUTH_ACCESS_TOKEN_URL+ String.format("?%s", queryString))
                    .header("Accept", "application/json")
                    .post(RequestBody.create(MediaType.parse("text/plain"), ""))
                    .build();
//            Log.d(TAG, request.body())
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(final Request request, final IOException e) {
                    Log.d(TAG, "FAIL");
                }

                @Override
                public void onResponse(final Response response) throws IOException {
                    Log.d(TAG, String.format("SUCCESS: %s", response.body().string()));
                }
            });
        }
    }
}
