package org.leanpoker.api;

import com.squareup.okhttp.RequestBody;

import org.leanpoker.api.constants.UploadCareConstants;
import org.leanpoker.data.response.UploadCareFileUploadResponseModel;

import retrofit.Call;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;

/**
 * Created by tmolnar on 13/09/15.
 */
public interface UploadCareService {

    @Multipart
    @POST(UploadCareConstants.UPLOAD_ENDPOINT)
    Call<UploadCareFileUploadResponseModel> upload(
            @Part(UploadCareConstants.PUBLIC_KEY_PARAM) String uploadCarePublicKey,
            @Part(UploadCareConstants.UPLOADCARE_STORE) int store,
            @Part(UploadCareConstants.FILE_KEY) RequestBody file
    );
}
