package org.leanpoker.util;

/**
 * Created by tmolnar on 19/09/15.
 */
public class GithubUtils {
    public static final String APP_URI_SCHEME      = "leanpoker://github-login";
    public static final String ACCESS_CODE_KEY     = "code=";
    public static final String ACCESS_TOKEN_KEY    = "access_token=";

    public static final String        BASE_OAUTH_URL = "https://github.com/login/oauth/authorize";
    public static final String        BASE_OAUTH_ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";

    public static final String        CLIENT_ID = "3826c0b84ae756bb1e5f";
    public static final String        CLIENT_SECRET = "24d2417f9c803bb03fb20190ca439d92d1a5f6d3";
    public static final String ACCEPT_HEADER_VALUE = "application/json";

    public static final String GITHUB_OAUTH_URL = BASE_OAUTH_URL + "?client_id=" + CLIENT_ID +
            "&callback_uri=" + APP_URI_SCHEME;
}
