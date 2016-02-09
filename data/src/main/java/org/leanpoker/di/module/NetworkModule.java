package org.leanpoker.di.module;

import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import org.leanpoker.JsonMapper;
import org.leanpoker.api.GithubService;
import org.leanpoker.api.LeanPokerService;
import org.leanpoker.api.UploadCareService;
import org.leanpoker.api.constants.GithubAuthService;
import org.leanpoker.api.constants.GithubConstants;
import org.leanpoker.api.constants.LeanPokerConstants;
import org.leanpoker.api.constants.UploadCareConstants;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by tmolnar on 07/02/16.
 */
@Module
public class NetworkModule {

    @Provides
    public GsonConverterFactory provideConverterFactory() {
        return GsonConverterFactory.create(JsonMapper.GSON);
    }

    @Provides
    public OkHttpClient provideHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    public LeanPokerService provideLeanPokerService(GsonConverterFactory converterFactory,
                                                    OkHttpClient httpClient) {
        return this.provideGenericRetrofitBuilder(
                converterFactory,
                httpClient,
                LeanPokerConstants.BASE_URL
        )
                .build()
                .create(LeanPokerService.class);
    }

    @Provides
    public GithubService provideGithubService(GsonConverterFactory converterFactory,
                                                 OkHttpClient httpClient) {
        return this.provideGenericRetrofitBuilder(
                converterFactory,
                httpClient,
                GithubConstants.GITHUB_API_BASE_URL
        )
                .build()
                .create(GithubService.class);
    }

    @Provides
    public GithubAuthService provideGithubAuthService(GsonConverterFactory converterFactory,
                                                  OkHttpClient httpClient) {
        return this.provideGenericRetrofitBuilder(
                converterFactory,
                httpClient,
                GithubConstants.GITHUB_OAUTH_API_BASE_URL
        )
                .build()
                .create(GithubAuthService.class);
    }

    @Provides
    public UploadCareService provideUploadCareService(GsonConverterFactory converterFactory,
                                                  OkHttpClient httpClient) {
        return this.provideGenericRetrofitBuilder(
                converterFactory,
                httpClient,
                UploadCareConstants.BASE_URL
        )
                .build()
                .create(UploadCareService.class);
    }

    private Retrofit.Builder provideGenericRetrofitBuilder(GsonConverterFactory converterFactory,
                                                           OkHttpClient httpClient,
                                                           String baseUrl) {
        final Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseUrl);
        builder.client(httpClient);
        builder.addConverterFactory(converterFactory);
        return builder;
    }
}
