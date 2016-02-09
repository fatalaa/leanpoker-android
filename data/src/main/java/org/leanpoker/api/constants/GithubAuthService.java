package org.leanpoker.api.constants;

import org.leanpoker.data.response.GithubAccessTokenResponseModel;

import retrofit.Call;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by tmolnar on 07/02/16.
 */
public interface GithubAuthService {

    @POST(GithubConstants.ACCESS_TOKEN_ENDPOINT)
    Call<GithubAccessTokenResponseModel> getToken(
            @Header(GithubConstants.ACCEPT_HEADER) String format,
            @Query(GithubConstants.CLIENT_ID_PARAM) String clientId,
            @Query(GithubConstants.CLIENT_SECRET_PARAM) String clientSecret,
            @Query(GithubConstants.ACCESS_CODE_PARAM) String accessCode,
            @Query(GithubConstants.STATE_PARAM) String state
    );
}
