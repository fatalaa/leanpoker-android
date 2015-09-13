package org.leanpoker.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import org.leanpoker.data.datamapper.EventDataMapper;
import org.leanpoker.data.model.Event;
import org.leanpoker.data.response.EventListResponseModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Observer;
import java.util.concurrent.Executor;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.observers.Observers;

/**
 * Created by tmolnar on 12/09/15.
 */
public class NetworkManager implements LeanPokerApi, UploadCareApi {

	private static final String LEANPOKER_BASE_URL  = "http://live.leanpoker.org";
	private static final String UPLOADCARE_BASE_URL = "https://upload.uploadcare.com";

	private static NetworkManager mInstance = new NetworkManager();

	private final LeanPokerService  mLeanPokerService;
	private final UploadCareService mUploadCareService;

	private NetworkManager() {

		final Gson gson = new GsonBuilder().setPrettyPrinting().create();
		final GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

		final Retrofit.Builder leanPokerBuilder = new Retrofit.Builder();
		leanPokerBuilder.baseUrl(LEANPOKER_BASE_URL);
		leanPokerBuilder.client(new OkHttpClient());
		leanPokerBuilder.addConverterFactory(gsonConverterFactory);

		mLeanPokerService = leanPokerBuilder.build().create(LeanPokerService.class);

		final Retrofit.Builder uploadCareBuilder = new Retrofit.Builder();
		uploadCareBuilder.baseUrl(UPLOADCARE_BASE_URL);
		uploadCareBuilder.client(new OkHttpClient());
		uploadCareBuilder.addConverterFactory(gsonConverterFactory);
		uploadCareBuilder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());

		mUploadCareService = uploadCareBuilder.build().create(UploadCareService.class);
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
