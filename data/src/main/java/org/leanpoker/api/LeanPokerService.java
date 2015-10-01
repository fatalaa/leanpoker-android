package org.leanpoker.api;

import org.leanpoker.api.constants.LeanPokerConstants;
import org.leanpoker.data.response.EventListResponseModel;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by tmolnar on 12/09/15.
 */
public interface LeanPokerService {

    @GET(LeanPokerConstants.TOURNAMENTS_ENDPOINT)
    Call<EventListResponseModel> events();

    @FormUrlEncoded
    @POST(LeanPokerConstants.IMAGES_ENDPOINT)
    Call<Object> upload(
            @Path(LeanPokerConstants.TOURNAMENT_ID_PATH_KEY) String tournamentId,
            @Field(LeanPokerConstants.LOGIN_FIELD_KEY) String email,
            @Field(LeanPokerConstants.TOKEN_FIELD_KEY) String githubAccessToken
    );
}
