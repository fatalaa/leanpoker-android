package org.leanpoker.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;

import org.leanpoker.EventsCache;
import org.leanpoker.EventsCache.ValidationStrategy;
import org.leanpoker.data.datamapper.EventDataMapper;
import org.leanpoker.data.datamapper.UploadCareFileUploadDataMapper;
import org.leanpoker.data.model.Event;
import org.leanpoker.data.model.UploadedFile;
import org.leanpoker.data.response.EventListResponseModel;
import org.leanpoker.data.response.UploadCareFileUploadResponseModel;

import java.io.File;
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
public class NetworkManager implements LeanPokerApi, UploadCareApi {

	private static final String LEANPOKER_BASE_URL    = "http://live.leanpoker.org";
	private static final String UPLOADCARE_BASE_URL   = "https://upload.uploadcare.com";
	private static final String UPLOADCARE_PUBLIC_KEY = "6d77b63f7f9937dc3383";

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
					if (EventsCache.getInstance().isValid()) {
						subscriber.onNext(EventsCache.getInstance().getEvents());
					} else {
						final EventListResponseModel eventResponseModels = mLeanPokerService
								.events().execute().body();
						final List<Event> events = new EventDataMapper().transform(
								eventResponseModels);
						EventsCache.getInstance().cacheEvents(events,
						                                      ValidationStrategy.NEVER_INVALIDATE);
						subscriber.onNext(events);
					}
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

	@Override
	public Observable<UploadedFile> upload(final File file, final String mimeType) {
		Observable<UploadedFile> fileUploadObservable = Observable.create(
				new OnSubscribe<UploadedFile>() {
					@Override
					public void call(final Subscriber<? super UploadedFile> subscriber) {
						MediaType mediaType = MediaType.parse(mimeType);
						try {
							final UploadCareFileUploadResponseModel fileUploadResponse = mUploadCareService
									.upload(UPLOADCARE_PUBLIC_KEY, UploadCareService.STORE_FILES,
									        RequestBody.create(mediaType, file)).execute().body();

							UploadedFile uploadedFile = new UploadCareFileUploadDataMapper()
									.transform(fileUploadResponse);
							subscriber.onNext(uploadedFile);
						} catch (IOException e) {
							subscriber.onError(e);
						}
					}
				});
		return fileUploadObservable;
	}
}
