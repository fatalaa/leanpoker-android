package org.leanpoker.api;

import android.util.Log;

import com.google.gson.Gson;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.leanpoker.EventsCache;
import org.leanpoker.EventsCache.ValidationStrategy;
import org.leanpoker.JsonMapper;
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

import java.io.File;
import java.io.IOException;
import java.util.List;

import okio.Buffer;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

/**
 * Created by tmolnar on 12/09/15.
 */
public class NetworkManager implements LeanPokerApi, UploadCareApi, GithubApi {

	private static final String TAG = NetworkManager.class.getSimpleName();
	private static NetworkManager mInstance 		= new NetworkManager();
	private final OkHttpClient mHttpClient;

	private final LeanPokerService  mLeanPokerService;
	private final GithubService 	mGithubOauthService;
	private final GithubService		mGithubApiService;

	private static String bodyToString(final Request request){

		try {
			final Request copy = request.newBuilder().build();
			final Buffer buffer = new Buffer();
			copy.body().writeTo(buffer);
			return buffer.readUtf8();
		} catch (final IOException e) {
			return "did not work";
		}
	}

	private NetworkManager() {

		mHttpClient 	= new OkHttpClient();
		mHttpClient.interceptors().add(new Interceptor() {
			@Override
			public Response intercept(final Chain chain) throws IOException {
//				Log.d(TAG, bodyToString(chain.request()));
				return chain.proceed(chain.request());
			}
		});
		final Gson gson = JsonMapper.GSON;
		final GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

		final Retrofit.Builder leanPokerBuilder = new Retrofit.Builder();
		leanPokerBuilder.baseUrl(LeanPokerConstants.BASE_URL);
		leanPokerBuilder.client(mHttpClient);
		leanPokerBuilder.addConverterFactory(gsonConverterFactory);

		mLeanPokerService = leanPokerBuilder.build().create(LeanPokerService.class);

		final Retrofit.Builder githubBuilder = new Retrofit.Builder();
		githubBuilder.baseUrl(GithubConstants.GITHUB_OAUTH_API_BASE_URL);
		githubBuilder.client(mHttpClient);
		githubBuilder.addConverterFactory(gsonConverterFactory);

		mGithubOauthService = githubBuilder.build().create(GithubService.class);

		final Retrofit.Builder githubApiBuilder = new Retrofit.Builder();
		githubApiBuilder.baseUrl(GithubConstants.GITHUB_API_BASE_URL);
		githubApiBuilder.client(mHttpClient);
		githubApiBuilder.addConverterFactory(gsonConverterFactory);

		mGithubApiService = githubApiBuilder.build().create(GithubService.class);
	}

	public static NetworkManager getInstance() {
		return mInstance;
	}

	@Override
	public Observable<List<Event>> getEvents() {
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
						subscriber.onCompleted();
					}
				} catch (IOException e) {
					subscriber.onError(e);
				}
			}
		});
		return myObservable;
	}

	@Override
	public Observable<Event> getEvent(final String eventId) {
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
						// TODO(tb): 19/09/15  Retrieve and cache only the specific getEvent with the given id instead of retrieving all the getEvents.
					}
				} catch (final IOException ex){
					subscriber.onError(ex);
				}
			}
		});
		return myObservable;
	}

	@Override
	public Observable<List<Photo>> getPhotos(final String eventId) {
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
						// TODO(tb): 19/09/15  Retrieve and cache only the specific getEvent with the given id instead of retrieving all the getEvents.
					}
				} catch (final IOException ex){
					subscriber.onError(ex);
				}
			}
		});
		return myObservable;
	}

	@Override
	public Boolean uploadPhotoToLeanPoker(final String tournamentId,
										  final String login,
										  final String accessToken,
										  final String uploadedImageUrl) {
		String url = String.format(
				"%s%s%s%s",
				LeanPokerConstants.BASE_URL,
				"/api/tournament/",
				tournamentId,
				"/image"
		);
		RequestBody requestBody = new FormEncodingBuilder()
				.add(LeanPokerConstants.LOGIN_FIELD_KEY, login)
				.add(LeanPokerConstants.TOKEN_FIELD_KEY, accessToken)
				.add(LeanPokerConstants.IMAGE_FIELD_KEY, uploadedImageUrl)
				.build();
		Request request = new Request.Builder()
				.url(url)
				.post(requestBody)
				.build();
		try {
			Response response = mHttpClient.newCall(request).execute();
			return response.isSuccessful();
		} catch (IOException e) {
			return false;
		}
	}


	@Override
	public Observable<UploadedFile> uploadPhotoToUploadCare(final File file, final String mimeType) {
		Observable<UploadedFile> fileUploadObservable = Observable.create(
				new OnSubscribe<UploadedFile>() {
					@Override
					public void call(final Subscriber<? super UploadedFile> subscriber) {
						MediaType mediaType = MediaType.parse(mimeType);
						try {
							RequestBody requestBody = new MultipartBuilder()
									.type(MultipartBuilder.FORM)
									.addFormDataPart(
											UploadCareConstants.PUBLIC_KEY_PARAM,
											UploadCareConstants.PUBLIC_KEY)
									.addFormDataPart(
											UploadCareConstants.UPLOADCARE_STORE,
											String.valueOf(UploadCareConstants.STORE_FILES))
									.addFormDataPart(
											"file",
											file.getName(),
											RequestBody.create(mediaType, file)
									)
									.build();
							Request request = new Request.Builder()
									.post(requestBody)
									.url(UploadCareConstants.BASE_URL + UploadCareConstants.UPLOAD_ENDPOINT)
									.build();
							com.squareup.okhttp.Response response =
									mHttpClient.newCall(request).execute();
							if (response.isSuccessful()) {
								UploadCareFileUploadResponseModel uploadResponse =
										JsonMapper.GSON.fromJson(
												response.body().string(),
												UploadCareFileUploadResponseModel.class);
								subscriber.onNext(
										new UploadCareFileUploadDataMapper()
												.transform(uploadResponse)
								);
							} else {
								subscriber.onError(new Exception("Unsuccessful uploadPhotoToUploadCare request"));
							}
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
									mGithubOauthService.getToken(
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
	public Observable<GithubUser> getUser(final boolean fetchEmailsOnly) {
		Observable<GithubUser> githubUserObservable =
				Observable.create(new OnSubscribe<GithubUser>() {
			@Override
			public void call(final Subscriber<? super GithubUser> subscriber) {
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
					subscriber.onNext(user);
				} catch (IOException e) {
					subscriber.onError(e);
				}
			}
		});
		return githubUserObservable;
	}

	private String getAccessToken() {
		AccessToken accessToken =
				TokenStore.getInstance().getAccessToken();
		String token = String.format("%s %s", "token", accessToken.getAccessToken());
		return token;
	}

	private GithubUser getUserFromNetwork() throws IOException {
		GithubDataMapper dataMapper = new GithubDataMapper();
		GithubAuthenticatedUserResponseModel userResponse =
				mGithubApiService
						.getUser(GithubConstants.ACCEPT_HEADER_VALUE, getAccessToken())
						.execute()
						.body();
		GithubUser githubUser = dataMapper.transform(userResponse);
		return githubUser;
	}

	private List<GithubUserEmail> getEmailsFromNetwork() throws IOException {
		GithubDataMapper dataMapper = new GithubDataMapper();
		GithubEmailsResponseModel emailsResponseModel = mGithubApiService
				.getEmails(GithubConstants.ACCEPT_HEADER_VALUE, getAccessToken())
				.execute()
				.body();
		List<GithubUserEmail> emails = dataMapper.transform(emailsResponseModel);
		return emails;
	}

	private GithubUser getUserFromStore() {
		return UserStore.getInstance().getUser();
	}
}
