package org.leanpoker.api.constants;

/**
 * Created by tmolnar on 19/09/15.
 */
public class GithubConstants {
    private static final String BASE_OAUTH_URL              =
            "https://github.com/login/oauth/authorize";

    public static final String APP_URI_SCHEME               = "leanpoker://github-login";
    public static final String ACCESS_CODE_KEY              = "code=";

    public static final String CLIENT_ID                    = "3826c0b84ae756bb1e5f";
    public static final String CLIENT_SECRET                = "24d2417f9c803bb03fb20190ca439d92d1a5f6d3";
    public static final String ACCEPT_HEADER_VALUE          = "application/json";

    public static final String OAUTH_URL                    = BASE_OAUTH_URL + "?client_id=" + CLIENT_ID +
                                                                "&callback_uri=" + APP_URI_SCHEME;
    public static final String GITHUB_API_BASE_URL          = "https://github.com";

    //ENDPOINTS
    public static final String ACCESS_TOKEN_ENDPOINT        = "/login/oauth/access_token";
    public static final String AUTHENTICATED_USER_ENDPOINT  = "/user";

    //PARAMETER KEYS
    public static final String CLIENT_ID_PARAM              = "client_id";
    public static final String CLIENT_SECRET_PARAM          = "client_secret";
    public static final String ACCESS_CODE_PARAM            = "code";
    public static final String STATE_PARAM                  = "state";

    //HEADER KEYS
    public static final String ACCEPT_HEADER                = "Accept";
    public static final String AUTHORIZATION_HEADER         = "Authorization";
}
