package org.leanpoker.leanpokerandroid.view.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.leanpoker.Utils;
import org.leanpoker.leanpokerandroid.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tmolnar on 16/09/15.
 */
public class LoginActivity extends BaseActivity {

    private WebViewClient       mWebViewClient;
    private static final String APP_URI_SCHEME      = "leanpoker://github-login";
    private static final String ACCESS_CODE_KEY     = "code=";
    private static final String ACCESS_TOKEN_KEY    = "access_token=";

    @Bind(R.id.login_webview)
    protected WebView           mWebView;

    public static final String TAG = LoginActivity.class.getSimpleName();

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initWebView();
    }

    private void initWebView() {
        mWebViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
                if (url.startsWith(APP_URI_SCHEME)) {
                    //code=asd19023klas2108asflj2
                    String accessCodeAsKeyValue = url.substring(url.indexOf(ACCESS_CODE_KEY));
                    String accessCode = Utils.getValueFromKeyValuePair(accessCodeAsKeyValue);
                    return true;
                } else if (url.contains(ACCESS_CODE_KEY)) {
                    String accessTokenAsKeyValue = url.substring(url.indexOf(ACCESS_TOKEN_KEY));
                    String accessToken = Utils.getValueFromKeyValuePair(accessTokenAsKeyValue);
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        };
        mWebView.setWebViewClient(mWebViewClient);
    }
}
