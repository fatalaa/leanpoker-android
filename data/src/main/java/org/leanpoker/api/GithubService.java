package org.leanpoker.api;

import org.leanpoker.data.response.GithubAccessTokenResponseModel;
import org.leanpoker.api.constants.GithubConstants;
import org.leanpoker.data.response.GithubAuthenticatedUserResponseModel;
import org.leanpoker.data.response.GithubEmailsResponseModel;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by tmolnar on 17/09/15.
 */
public interface GithubService {

    @POST(GithubConstants.ACCESS_TOKEN_ENDPOINT)
    Call<GithubAccessTokenResponseModel> getToken(
            @Header(GithubConstants.ACCEPT_HEADER) String format,
            @Query(GithubConstants.CLIENT_ID_PARAM) String clientId,
            @Query(GithubConstants.CLIENT_SECRET_PARAM) String clientSecret,
            @Query(GithubConstants.ACCESS_CODE_PARAM) String accessCode,
            @Query(GithubConstants.STATE_PARAM) String state
    );

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
