package org.leanpoker.api;

import org.leanpoker.data.model.UploadedFile;

import java.io.File;

import rx.Observable;

/**
 * Created by tmolnar on 14/09/15.
 */
public interface UploadCareApi {
    Observable<UploadedFile> upload(File file);
}
