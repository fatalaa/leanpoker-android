package org.leanpoker.di.module;


import com.orhanobut.hawk.LogLevel;

import org.leanpoker.JsonMapper;
import org.leanpoker.api.GithubService;
import org.leanpoker.api.LeanPokerService;
import org.leanpoker.api.UploadCareService;
import org.leanpoker.api.constants.GithubAuthService;
import org.leanpoker.api.constants.GithubConstants;
import org.leanpoker.api.constants.LeanPokerConstants;
import org.leanpoker.api.constants.UploadCareConstants;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
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
