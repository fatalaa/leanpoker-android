package org.leanpoker.api;

import org.leanpoker.data.model.AccessToken;
import org.leanpoker.data.model.GithubUser;

import rx.Observable;

/**
 * Created by tmolnar on 17/09/15.
 */
public interface GithubApi {
    Observable<AccessToken> getToken(String accessCode, String state);
    Observable<GithubUser> getUser(String accessToken);
}
