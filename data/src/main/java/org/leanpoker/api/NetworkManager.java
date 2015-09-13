package org.leanpoker.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import org.leanpoker.data.datamapper.EventDataMapper;
import org.leanpoker.data.model.Event;
import org.leanpoker.data.response.EventListResponseModel;

import java.io.IOException;
import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

/**
 * Created by tmolnar on 12/09/15.
 */
public class NetworkManager implements LeanPokerApi {

	private static final String LEANPOKER_BASE_URL = "http://live.leanpoker.org";

	private static NetworkManager mInstance = new NetworkManager();

	private final Retrofit         mRetrofit;
	private final LeanPokerService mLeanPokerService;

	private NetworkManager() {
		final Retrofit.Builder builder = new Retrofit.Builder();
		builder.baseUrl(LEANPOKER_BASE_URL);
		builder.client(new OkHttpClient());
		final Gson gson = new GsonBuilder().setPrettyPrinting().create();
		builder.addConverterFactory(GsonConverterFactory.create(gson));

		mRetrofit = builder.build();

		mLeanPokerService = mRetrofit.create(LeanPokerService.class);
	}

	public static NetworkManager getInstance() {
		return mInstance;
	}

	@Override
	public Observable<List<Event>> events() {
		Observable<List<Event>> myObservable = Observable.create(new OnSubscribe<List<Event>>() {
			@Override
			public void call(final Subscriber<? super List<Event>> subscriber) {
				try {
					final EventListResponseModel eventResponseModels = mLeanPokerService.events()
					                                                                    .execute()
					                                                                    .body();
					final List<Event> events = new EventDataMapper().transformDate(
							eventResponseModels);
					subscriber.onNext(events);
				} catch (IOException e) {
					subscriber.onError(e);
				}
			}
		});
		return myObservable;
	}

	@Override
	public Observable<Event> event(final long id) {
		throw new RuntimeException("Not implmeneted yet");
	}
}
