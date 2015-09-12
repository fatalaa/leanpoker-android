package org.leanpoker.api;

import com.squareup.okhttp.OkHttpClient;

import org.leanpoker.data.Event;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by tmolnar on 12/09/15.
 */
public class NetworkManager implements LeanPokerApi{

    private static final String LEANPOKER_BASE_URL = "http://live.leanpoker.org";

    private static NetworkManager mInstance = new NetworkManager();

    private final Retrofit mRetrofit;
    private LeanPokerApiListener mApiListener;
    private final LeanPokerService mLeanPokerService;

    private NetworkManager() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(LEANPOKER_BASE_URL)
                .client(new OkHttpClient())
                .build();

        mLeanPokerService = mRetrofit.create(LeanPokerService.class);
    }

    public static NetworkManager getInstance() {
        return mInstance;
    }

    public LeanPokerApiListener getApiListener() {
        return mApiListener;
    }

    public void setApiListener(final LeanPokerApiListener mApiListener) {
        this.mApiListener = mApiListener;
    }

    @Override
    public void loadEvents() {
        mLeanPokerService.events().enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(final Response<List<Event>> response) {
                mApiListener.notifyEventsLoaded(response.body());
            }

            @Override
            public void onFailure(final Throwable t) {
                mApiListener.notifyEventsLoadingFailure(t);
            }
        });
    }
}
