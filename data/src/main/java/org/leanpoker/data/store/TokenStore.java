package org.leanpoker.data.store;

import org.leanpoker.data.model.AccessToken;

/**
 * Created by tmolnar on 19/09/15.
 */
public class TokenStore {

    private static TokenStore mInstance = new TokenStore();

    private AccessToken mAccessToken;

    private TokenStore() {}

    public static TokenStore getInstance() {
        return mInstance;
    }

    public AccessToken getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        mAccessToken = accessToken;
    }
}
