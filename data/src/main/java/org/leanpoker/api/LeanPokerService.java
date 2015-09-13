package org.leanpoker.api;

import org.leanpoker.data.response.EventListResponseModel;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by tmolnar on 12/09/15.
 */
public interface LeanPokerService {

    @GET("/api/tournament")
    Call<EventListResponseModel> events();
}
