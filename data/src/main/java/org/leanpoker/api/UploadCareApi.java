package org.leanpoker.api;

import org.leanpoker.data.model.UploadedFile;

import java.io.File;

import io.reactivex.Observable;

/**
 * Created by tmolnar on 14/09/15.
 */
public interface UploadCareApi {
    Observable<UploadedFile> uploadPhotoToUploadCare(final File file, final String mimeType);
}
