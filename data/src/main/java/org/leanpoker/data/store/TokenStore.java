package org.leanpoker.data.store;

import android.content.Context;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;

import org.leanpoker.data.model.AccessToken;

/**
 * Created by tmolnar on 19/09/15.
 */
public class TokenStore {

    private static TokenStore mInstance;
    public static final String TOKEN_STORAGE_KEY = "gh_access_token";

    private AccessToken mAccessToken;

    private TokenStore(Context context) {
        Hawk.init(context)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setLogLevel(LogLevel.FULL)
                .setStorage(HawkBuilder.newSharedPrefStorage(context))
                .build();
    }

    public static TokenStore getInstance(Context context) {
        if (mInstance == null) {
            synchronized (TokenStore.class) {
                if (mInstance == null) {
                    mInstance = new TokenStore(context);
                }
            }
        }
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
