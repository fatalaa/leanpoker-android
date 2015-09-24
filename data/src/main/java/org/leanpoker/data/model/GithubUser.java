package org.leanpoker.data.model;

import java.util.List;

/**
 * Created by tmolnar on 23/09/15.
 */
public class GithubUser {
    private String login;

    private String avatarUrl;

    private String name;

    private List<GithubUserEmail> emails;

    public GithubUser(final String login, final String avatarUrl, final String name) {
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.name = name;
    }

    public void updateEmails(List<GithubUserEmail> emails) {
        this.emails = emails;
    }
}
