package org.leanpoker.data.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tmolnar on 23/09/15.
 */
public class GithubAuthenticatedUserResponseModel {

    private String login;

    @SerializedName("avatar_url")
    private String avatarUrl;

    private String name;

    private String email;

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
