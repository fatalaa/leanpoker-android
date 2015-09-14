package org.leanpoker.api;

import com.squareup.okhttp.RequestBody;

import org.leanpoker.data.response.UploadCareFileUploadResponseModel;

import retrofit.Call;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;

/**
 * Created by tmolnar on 13/09/15.
 */
public interface UploadCareService {
    String UPLOADCARE_PUB_KEY   = "UPLOADCARE_PUB_KEY";
    String UPLOADCARE_STORE     = "UPLOADCARE_STORE";
    int STORE_FILES             = 1;
    int DONT_STORE_FILES        = 0;

    @Multipart
    @POST("/base/")
    Call<UploadCareFileUploadResponseModel> upload(
            @Part(UPLOADCARE_PUB_KEY) String uploadCarePublicKey,
            @Part(UPLOADCARE_STORE) int store,
            @Part("file") RequestBody file
    );
}
