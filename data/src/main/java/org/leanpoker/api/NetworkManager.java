package org.leanpoker.api;

import org.leanpoker.EventsCache;
import org.leanpoker.EventsCache.ValidationStrategy;
import org.leanpoker.JsonMapper;
import org.leanpoker.api.constants.GithubAuthService;
import org.leanpoker.api.constants.GithubConstants;
import org.leanpoker.api.constants.LeanPokerConstants;
import org.leanpoker.api.constants.UploadCareConstants;
import org.leanpoker.data.datamapper.EventDataMapper;
import org.leanpoker.data.datamapper.GithubDataMapper;
import org.leanpoker.data.datamapper.UploadCareFileUploadDataMapper;
import org.leanpoker.data.model.AccessToken;
import org.leanpoker.data.model.Event;
import org.leanpoker.data.model.GithubUser;
import org.leanpoker.data.model.GithubUserEmail;
import org.leanpoker.data.model.Photo;
import org.leanpoker.data.model.UploadedFile;
import org.leanpoker.data.response.EventListResponseModel;
import org.leanpoker.data.response.GithubAccessTokenResponseModel;
import org.leanpoker.data.response.GithubAuthenticatedUserResponseModel;
import org.leanpoker.data.response.GithubEmailsResponseModel;
import org.leanpoker.data.response.UploadCareFileUploadResponseModel;
import org.leanpoker.data.store.TokenStore;
import org.leanpoker.data.store.UserStore;
import org.leanpoker.di.DaggerNetworkComponent;
import org.leanpoker.di.NetworkComponent;
import org.leanpoker.di.module.NetworkModule;
import org.reactivestreams.Subscriber;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by tmolnar on 12/09/15.
 */
public class NetworkManager implements LeanPokerApi, UploadCareApi, GithubApi {

	private static final String         TAG       = NetworkManager.class.getSimpleName();
	private static       NetworkManager mInstance = new NetworkManager();

	@Inject
	protected LeanPokerService mLeanPokerService;

	@Inject
	protected GithubAuthService mGithubOauthService;

	@Inject
	protected GithubService    mGithubApiService;

	@Inject
	protected UploadCareService mUploadCareService;

	@Inject
	protected OkHttpClient mHttpClient;

	private NetworkManager() {

		NetworkComponent networkComponent =
				DaggerNetworkComponent.builder()
						.networkModule(new NetworkModule())
						.build();
		networkComponent.inject(this);
	}

	public static NetworkManager getInstance() {
		return mInstance;
	}

	@Override
	public Observable<List<Event>> getEvents() {
		Observable<List<Event>> myObservable = Observable.create(new ObservableOnSubscribe<List<Event>>() {
			@Override
			public void subscribe(ObservableEmitter<List<Event>> emitter) throws Exception {
				try {
					if (EventsCache.getInstance().isValid()) {
						emitter.onNext(EventsCache.getInstance().getEvents());
					} else {
						final EventListResponseModel eventResponseModels = mLeanPokerService
								.events().execute().body();
						final List<Event> events = new EventDataMapper().transform(
								eventResponseModels);
						EventsCache.getInstance().cacheEvents(events,
								ValidationStrategy.NEVER_INVALIDATE);
						emitter.onNext(events);
						emitter.onComplete();
					}
				} catch (IOException e) {
					emitter.onError(e);
				}
			}
		});
		return myObservable;
	}

	@Override
	public Observable<Event> getEvent(final String eventId) {
		Observable<Event> myObservable = Observable.create(new ObservableOnSubscribe<Event>() {
			@Override
			public void subscribe(ObservableEmitter<Event> emitter) throws Exception {
				try {
					if (EventsCache.getInstance().isValid()) {
						emitter.onNext(EventsCache.getInstance().getEvent(eventId));
					} else {
						final EventListResponseModel eventResponseModels = mLeanPokerService
								.events().execute().body();
						final List<Event> events = new EventDataMapper().transform(
								eventResponseModels);
						EventsCache.getInstance().cacheEvents(events,
								ValidationStrategy.NEVER_INVALIDATE);
						emitter.onNext(EventsCache.getInstance().getEvent(eventId));
						// TODO(tb): 19/09/15  Retrieve and cache only the specific getEvent with the given id instead of retrieving all the getEvents.
					}
				} catch (final IOException ex) {
					emitter.onError(ex);
				}
			}
		});
		return myObservable;
	}

	@Override
	public Observable<List<Photo>> getPhotos(final String eventId) {
		Observable<List<Photo>> myObservable = Observable.create(new ObservableOnSubscribe<List<Photo>>() {

			@Override
			public void subscribe(ObservableEmitter<List<Photo>> emitter) throws Exception {
				try {
					if (EventsCache.getInstance().isValid()) {
						emitter.onNext(EventsCache.getInstance().getEvent(eventId).getPhotos());
					} else {
						final EventListResponseModel eventResponseModels = mLeanPokerService
								.events().execute().body();
						final List<Event> events = new EventDataMapper().transform(
								eventResponseModels);
						EventsCache.getInstance().cacheEvents(events,
								ValidationStrategy.NEVER_INVALIDATE);
						emitter.onNext(EventsCache.getInstance().getEvent(eventId).getPhotos());
						// TODO(tb): 19/09/15  Retrieve and cache only the specific getEvent with the given id instead of retrieving all the getEvents.
					}
				} catch (final IOException ex) {
					emitter.onError(ex);
				}
			}
		});
		return myObservable;
	}

	@Override
	public Boolean uploadPhotoToLeanPoker(final String tournamentId, final String login,
	                                      final String accessToken, final String uploadedImageUrl) {
		String url = String.format("%s%s%s%s", LeanPokerConstants.BASE_URL, "/api/tournament/",
		                           tournamentId, "/image");
		RequestBody requestBody = new FormBody.Builder().add(LeanPokerConstants.LOGIN_FIELD_KEY,
		                                                        login).add(
				LeanPokerConstants.TOKEN_FIELD_KEY, accessToken).add(
				LeanPokerConstants.IMAGE_FIELD_KEY, uploadedImageUrl).build();
		Request request = new Request.Builder().url(url).post(requestBody).build();
		try {
			Response response = mHttpClient.newCall(request).execute();
			return response.isSuccessful();
		} catch (IOException e) {
			return false;
		}
	}


	@Override
	public Observable<UploadedFile> uploadPhotoToUploadCare(final File file,
	                                                        final String mimeType) {
		Observable<UploadedFile> fileUploadObservable = Observable.create(
				new ObservableOnSubscribe<UploadedFile>() {

					@Override
					public void subscribe(ObservableEmitter<UploadedFile> emitter) throws Exception {
						MediaType mediaType = MediaType.parse(mimeType);
						try {
							RequestBody requestBody = new MultipartBody.Builder()
									.addFormDataPart(UploadCareConstants.PUBLIC_KEY_PARAM, UploadCareConstants.PUBLIC_KEY)
									.addFormDataPart(UploadCareConstants.UPLOADCARE_STORE, String.valueOf(UploadCareConstants.STORE_FILES))
									.addFormDataPart("file", file.getName(), RequestBody.create(mediaType, file))
									.build();
							Request request = new Request.Builder().post(requestBody).url(
									UploadCareConstants.BASE_URL +
											UploadCareConstants.UPLOAD_ENDPOINT).build();
							Response response = mHttpClient.newCall(request)
									.execute();
							if (response.isSuccessful()) {
								UploadCareFileUploadResponseModel uploadResponse = JsonMapper.GSON
										.fromJson(response.body().string(),
												UploadCareFileUploadResponseModel.class);
								emitter.onNext(new UploadCareFileUploadDataMapper().transform(
										uploadResponse));
							} else {
								emitter.onError(new Exception(
										"Unsuccessful uploadPhotoToUploadCare request"));
							}
						} catch (IOException e) {
							emitter.onError(e);
						}
					}
				});
		return fileUploadObservable;
	}

	@Override
	public Observable<AccessToken> getToken(final String accessCode, final String state) {
		Observable<AccessToken> accessTokenObservable = Observable.create(
				new ObservableOnSubscribe<AccessToken>() {

					@Override
					public void subscribe(ObservableEmitter<AccessToken> emitter) throws Exception {
						try {
							final GithubAccessTokenResponseModel accessTokenResponseModel = mGithubOauthService
									.getToken(GithubConstants.ACCEPT_HEADER_VALUE,
											GithubConstants.CLIENT_ID,
											GithubConstants.CLIENT_SECRET, accessCode, state)
									.execute().body();
							final AccessToken accessToken = new GithubDataMapper().transform(
									accessTokenResponseModel);
							emitter.onNext(accessToken);
						} catch (IOException e) {
							emitter.onError(e);
						}
					}
				});
		return accessTokenObservable;
	}

	@Override
	public Observable<GithubUser> getUser(final boolean fetchEmailsOnly) {
		Observable<GithubUser> githubUserObservable = Observable.create(
				new ObservableOnSubscribe<GithubUser>() {
					@Override
					public void subscribe(ObservableEmitter<GithubUser> emitter) throws Exception {
						try {
							GithubUser user = getUserFromStore();
							if (user == null) {
								user = getUserFromNetwork();
							}
							if (fetchEmailsOnly) {
								List<GithubUserEmail> emails = getEmailsFromNetwork();
								user.updateEmails(emails);
							} else {
								user = getUserFromNetwork();
								List<GithubUserEmail> emails = getEmailsFromNetwork();
								user.updateEmails(emails);
							}
							emitter.onNext(user);
						} catch (IOException e) {
							emitter.onError(e);
						}
					}
				});
		return githubUserObservable;
	}

	private String getAccessToken() {
		AccessToken accessToken = TokenStore.getInstance().getAccessToken();
		String token = String.format("%s %s", "token", accessToken.getAccessToken());
		return token;
	}

	private GithubUser getUserFromNetwork() throws IOException {
		GithubDataMapper dataMapper = new GithubDataMapper();
		GithubAuthenticatedUserResponseModel userResponse = mGithubApiService.getUser(
				GithubConstants.ACCEPT_HEADER_VALUE, getAccessToken()).execute().body();
		GithubUser githubUser = dataMapper.transform(userResponse);
		return githubUser;
	}

	private List<GithubUserEmail> getEmailsFromNetwork() throws IOException {
		GithubDataMapper dataMapper = new GithubDataMapper();
		GithubEmailsResponseModel emailsResponseModel = mGithubApiService.getEmails(
				GithubConstants.ACCEPT_HEADER_VALUE, getAccessToken()).execute().body();
		return dataMapper.transform(emailsResponseModel);
	}

	private GithubUser getUserFromStore() {
		return UserStore.getInstance().getUser();
	}
}
