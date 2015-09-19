package org.leanpoker.api;

import org.leanpoker.data.response.GithubAccessTokenResponseModel;

import retrofit.Call;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by tmolnar on 17/09/15.
 */
public interface GithubService {

    @POST("/login/oauth/access_token")
    Call<GithubAccessTokenResponseModel> getToken(
            @Header("Accept") String format,
            @Query("client_id") String clientId,
            @Query("client_secret") String clientSecret,
            @Query("code") String accessCode,
            @Query("state") String state
    );
}
