package org.leanpoker.api;

import org.leanpoker.api.constants.GithubConstants;
import org.leanpoker.data.response.GithubAuthenticatedUserResponseModel;
import org.leanpoker.data.response.GithubEmailsResponseModel;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Header;

/**
 * Created by tmolnar on 17/09/15.
 */
public interface GithubService {

    @GET(GithubConstants.AUTHENTICATED_USER_ENDPOINT)
    Call<GithubAuthenticatedUserResponseModel> getUser(
            @Header(GithubConstants.ACCEPT_HEADER) String format,
            @Header(GithubConstants.AUTHORIZATION_HEADER) String accessTokenWithFormat
    );

    @GET(GithubConstants.EMAILS_ENDPOINT)
    Call<GithubEmailsResponseModel> getEmails(
            @Header(GithubConstants.ACCEPT_HEADER) String format,
            @Header(GithubConstants.AUTHORIZATION_HEADER) String accessTokenWithFormat
    );
}
