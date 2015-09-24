package org.leanpoker.data.store;

import com.orhanobut.hawk.Hawk;

import org.leanpoker.data.model.GithubUser;

/**
 * Created by tmolnar on 24/09/15.
 */
public class UserStore {

    private static UserStore mInstance = new UserStore();
    private static final String GITHUB_USER_KEY = "gh_user";

    private GithubUser mGithubUser;

    private UserStore() {
    }


    public static UserStore getInstance() {
        return mInstance;
    }

    public GithubUser getUser() {
        mGithubUser = Hawk.get(GITHUB_USER_KEY);
        return mGithubUser;
    }

    public void setUser(GithubUser accessToken) {
        Hawk.put(GITHUB_USER_KEY, accessToken);
        mGithubUser = accessToken;
    }
}
