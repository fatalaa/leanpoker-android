package org.leanpoker.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;

import org.leanpoker.EventsCache;
import org.leanpoker.EventsCache.ValidationStrategy;
import org.leanpoker.api.constants.LeanPokerConstants;
import org.leanpoker.api.constants.UploadCareConstants;
import org.leanpoker.data.datamapper.GithubDataMapper;
import org.leanpoker.data.datamapper.EventDataMapper;
import org.leanpoker.data.datamapper.UploadCareFileUploadDataMapper;
import org.leanpoker.data.model.AccessToken;
import org.leanpoker.data.model.Event;
import org.leanpoker.data.model.GithubUser;
import org.leanpoker.data.model.Photo;
import org.leanpoker.data.model.UploadedFile;
import org.leanpoker.data.response.EventListResponseModel;
import org.leanpoker.data.response.GithubAccessTokenResponseModel;
import org.leanpoker.data.response.GithubAuthenticatedUserResponseModel;
import org.leanpoker.data.response.UploadCareFileUploadResponseModel;
import org.leanpoker.api.constants.GithubConstants;
import org.leanpoker.data.store.TokenStore;

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
public class NetworkManager implements LeanPokerApi, UploadCareApi, GithubApi {

	private static NetworkManager mInstance 		= new NetworkManager();
	private final OkHttpClient mHttpClient;

	private final LeanPokerService  mLeanPokerService;
	private final UploadCareService mUploadCareService;
	private final GithubService		mGithubService;
	private Context					mContext;

	private NetworkManager() {

		mHttpClient 	= new OkHttpClient();
		final Gson gson = new GsonBuilder().setPrettyPrinting().create();
		final GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

		final Retrofit.Builder leanPokerBuilder = new Retrofit.Builder();
		leanPokerBuilder.baseUrl(LeanPokerConstants.BASE_URL);
		leanPokerBuilder.client(mHttpClient);
		leanPokerBuilder.addConverterFactory(gsonConverterFactory);

		mLeanPokerService = leanPokerBuilder.build().create(LeanPokerService.class);

		final Retrofit.Builder uploadCareBuilder = new Retrofit.Builder();
		uploadCareBuilder.baseUrl(UploadCareConstants.BASE_URL);
		uploadCareBuilder.client(mHttpClient);
		uploadCareBuilder.addConverterFactory(gsonConverterFactory);

		mUploadCareService = uploadCareBuilder.build().create(UploadCareService.class);

		final Retrofit.Builder githubBuilder = new Retrofit.Builder();
		githubBuilder.baseUrl(GithubConstants.GITHUB_API_BASE_URL);
		githubBuilder.client(mHttpClient);
		githubBuilder.addConverterFactory(gsonConverterFactory);

		mGithubService = githubBuilder.build().create(GithubService.class);
	}

	public void init(final Context context) {
		mContext = context;
	}

	private Context getContextOrThrow() {
		if (mContext == null) {
			throw new IllegalStateException("Trying to access NetworkManager in non-initialized state");
		}
		return mContext;
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
	public Observable<Event> event(final String eventId) {
		Observable<Event> myObservable = Observable.create(new OnSubscribe<Event>() {
			@Override
			public void call(final Subscriber<? super Event> subscriber) {
				try {
					if (EventsCache.getInstance().isValid()) {
						subscriber.onNext(EventsCache.getInstance().getEvent(eventId));
					} else {
						final EventListResponseModel eventResponseModels = mLeanPokerService
								.events().execute().body();
						final List<Event> events = new EventDataMapper().transform(
								eventResponseModels);
						EventsCache.getInstance().cacheEvents(events,
						                                      ValidationStrategy.NEVER_INVALIDATE);
						subscriber.onNext(EventsCache.getInstance().getEvent(eventId));
						// TODO(tb): 19/09/15  Retrieve and cache only the specific event with the given id instead of retrieving all the events.
					}
				} catch (final IOException ex){
					subscriber.onError(ex);
				}
			}
		});
		return myObservable;
	}

	@Override
	public Observable<List<Photo>> photos(final String eventId) {
		Observable<List<Photo>> myObservable = Observable.create(new OnSubscribe<List<Photo>>() {
			@Override
			public void call(final Subscriber<? super List<Photo>> subscriber) {
				try {
					if (EventsCache.getInstance().isValid()) {
						subscriber.onNext(EventsCache.getInstance().getEvent(eventId).getPhotos());
					} else {
						final EventListResponseModel eventResponseModels = mLeanPokerService
								.events().execute().body();
						final List<Event> events = new EventDataMapper().transform(
								eventResponseModels);
						EventsCache.getInstance().cacheEvents(events,
								ValidationStrategy.NEVER_INVALIDATE);
						subscriber.onNext(EventsCache.getInstance().getEvent(eventId).getPhotos());
						// TODO(tb): 19/09/15  Retrieve and cache only the specific event with the given id instead of retrieving all the events.
					}
				} catch (final IOException ex){
					subscriber.onError(ex);
				}
			}
		});
		return myObservable;
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
									.upload(
											UploadCareConstants.PUBLIC_KEY,
											UploadCareConstants.STORE_FILES,
									        RequestBody.create(mediaType, file)
									).execute().body();

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

	@Override
	public Observable<AccessToken> getToken(final String accessCode, final String state) {
		Observable<AccessToken> accessTokenObservable = Observable.create(
				new OnSubscribe<AccessToken>() {
					@Override
					public void call(final Subscriber<? super AccessToken> subscriber) {
						try {
							final GithubAccessTokenResponseModel accessTokenResponseModel =
									mGithubService.getToken(
											GithubConstants.ACCEPT_HEADER_VALUE,
											GithubConstants.CLIENT_ID,
											GithubConstants.CLIENT_SECRET,
											accessCode,
											state
									).execute().body();
							final AccessToken accessToken = new GithubDataMapper()
									.transform(accessTokenResponseModel);
							subscriber.onNext(accessToken);
						} catch (IOException e) {
							subscriber.onError(e);
						}
					}
				}
		);
		return accessTokenObservable;
	}

	@Override
	public Observable<GithubUser> getUser(final String accessToken) {
		Observable<GithubUser> githubUserObservable = Observable.create(new OnSubscribe<GithubUser>() {
			@Override
			public void call(final Subscriber<? super GithubUser> subscriber) {
				String token = String.format("%s %s", "token", accessToken);
				try {
					GithubAuthenticatedUserResponseModel userResponse =
                            mGithubService.getUser(GithubConstants.ACCEPT_HEADER_VALUE, token)
                                    .execute()
                                    .body();
					GithubUser githubUser = new GithubDataMapper().transform(userResponse);
					subscriber.onNext(githubUser);
				} catch (IOException e) {
					subscriber.onError(e);
				}
			}
		});
		return githubUserObservable;
	}
}
