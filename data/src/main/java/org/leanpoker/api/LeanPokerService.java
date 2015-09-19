package org.leanpoker.api;

import org.leanpoker.api.constants.LeanPokerConstants;
import org.leanpoker.data.response.EventListResponseModel;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by tmolnar on 12/09/15.
 */
public interface LeanPokerService {

    @GET(LeanPokerConstants.TOURNAMENTS_ENDPOINT)
    Call<EventListResponseModel> events();
}
