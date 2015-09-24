package org.leanpoker.data.datamapper;

import org.leanpoker.data.model.AccessToken;
import org.leanpoker.data.model.GithubUser;
import org.leanpoker.data.model.GithubUserEmail;
import org.leanpoker.data.response.GithubAccessTokenResponseModel;
import org.leanpoker.data.response.GithubAuthenticatedUserResponseModel;
import org.leanpoker.data.response.GithubEmailsResponseModel;
import org.leanpoker.data.response.submodel.GithubEmailResponseModel;

import java.util.ArrayList;
import java.util.List;

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
                userResponseModel.getLogin(),
                userResponseModel.getAvatarUrl(),
                userResponseModel.getName()
        );
    }

    public GithubUserEmail transform(GithubEmailResponseModel githubEmailResponseModel) {
        return new GithubUserEmail(
                githubEmailResponseModel.getEmail(),
                githubEmailResponseModel.getVerified(),
                githubEmailResponseModel.getPrimary()
        );
    }

    public List<GithubUserEmail> transform(GithubEmailsResponseModel githubEmailsResponseModel) {
        ArrayList<GithubUserEmail> emails = new ArrayList<>();
        for (GithubEmailResponseModel emailResponseModel : githubEmailsResponseModel) {
            emails.add(transform(emailResponseModel));
        }
        return emails;
    }
}
