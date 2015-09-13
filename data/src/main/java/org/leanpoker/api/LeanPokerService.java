package org.leanpoker.api;

import org.leanpoker.data.Event;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by tmolnar on 12/09/15.
 */
public interface LeanPokerService {

    @GET("/api/tournament")
    Call<List<Event>> events();

}
