package org.leanpoker.data.model;

/**
 * Created by tmolnar on 17/09/15.
 */
public class AccessToken {
    private String accessToken;

    private String scopes;

    private String tokenType;

    public AccessToken(final String accessToken, final String scopes, final String tokenType) {
        this.accessToken = accessToken;
        this.scopes = scopes;
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getScopes() {
        return scopes;
    }

    public String getTokenType() {
        return tokenType;
    }
}
