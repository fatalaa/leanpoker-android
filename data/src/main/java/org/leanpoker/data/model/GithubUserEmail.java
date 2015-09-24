package org.leanpoker.data.model;

/**
 * Created by tmolnar on 24/09/15.
 */
public class GithubUserEmail {
    private String email;

    private Boolean verified;

    private Boolean primary;

    public GithubUserEmail(final String email, final Boolean verified, final Boolean primary) {
        this.email = email;
        this.verified = verified;
        this.primary = primary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(final Boolean verified) {
        this.verified = verified;
    }

    public Boolean getPrimary() {
        return primary;
    }

    public void setPrimary(final Boolean primary) {
        this.primary = primary;
    }
}
