package org.leanpoker.data.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tmolnar on 17/09/15.
 */
public class GithubAccessTokenResponseModel {
    @SerializedName("access_token")
    private String accessToken;

    private String scope;

    @SerializedName("token_type")
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public String getScope() {
        return scope;
    }

    public String getTokenType() {
        return tokenType;
    }
}
