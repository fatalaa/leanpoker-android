package org.leanpoker.data.datamapper;

import org.leanpoker.data.model.AccessToken;
import org.leanpoker.data.response.GithubAccessTokenResponseModel;

/**
 * Created by tmolnar on 17/09/15.
 */
public class AccessTokenDataMapper {

    public AccessToken transform(GithubAccessTokenResponseModel tokenResponseModel) {
        return new AccessToken(
                tokenResponseModel.getAccessToken(),
                tokenResponseModel.getScope(),
                tokenResponseModel.getTokenType()
        );
    }
}
