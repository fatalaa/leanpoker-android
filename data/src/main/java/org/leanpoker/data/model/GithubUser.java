package org.leanpoker.data.model;

/**
 * Created by tmolnar on 23/09/15.
 */
public class GithubUser {
    private String login;

    private String avatarUrl;

    private String name;

    private String email;

    public GithubUser(final String login, final String avatarUrl, final String name,
                      final String email) {
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.name = name;
        this.email = email;
    }
}
