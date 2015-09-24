package org.leanpoker.data.store;

import com.orhanobut.hawk.Hawk;

import org.leanpoker.data.model.AccessToken;

/**
 * Created by tmolnar on 19/09/15.
 */
public class TokenStore {

    private static TokenStore mInstance = new TokenStore();
    public static final String TOKEN_STORAGE_KEY = "gh_access_token";

    private AccessToken mAccessToken;

    private TokenStore () {
    }

    public static TokenStore getInstance() {
        return mInstance;
    }

    public AccessToken getAccessToken() {
        mAccessToken = Hawk.get(TOKEN_STORAGE_KEY);
        return mAccessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        Hawk.put(TOKEN_STORAGE_KEY, accessToken);
        mAccessToken = accessToken;
    }
}
