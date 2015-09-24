package org.leanpoker.data.response.submodel;

/**
 * Created by tmolnar on 24/09/15.
 */
public class GithubEmailResponseModel {
    private String email;

    private Boolean verified;

    private Boolean primary;

    public String getEmail() {
        return email;
    }

    public Boolean getVerified() {
        return verified;
    }

    public Boolean getPrimary() {
        return primary;
    }
}
