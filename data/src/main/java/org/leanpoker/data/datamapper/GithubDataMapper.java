package org.leanpoker.data.datamapper;

import org.leanpoker.data.model.AccessToken;
import org.leanpoker.data.model.GithubUser;
import org.leanpoker.data.response.GithubAccessTokenResponseModel;
import org.leanpoker.data.response.GithubAuthenticatedUserResponseModel;

/**
 * Created by tmolnar on 17/09/15.
 */
public class GithubDataMapper {

    public AccessToken transform(GithubAccessTokenResponseModel tokenResponseModel) {
        return new AccessToken(
                tokenResponseModel.getAccessToken(),
                tokenResponseModel.getScope(),
                tokenResponseModel.getTokenType()
        );
    }

    public GithubUser transform(GithubAuthenticatedUserResponseModel userResponseModel) {
        return new GithubUser(
                userResponseModel.getEmail(),
                userResponseModel.getAvatarUrl(),
                userResponseModel.getLogin(),
                userResponseModel.getName()
        );
    }
}
