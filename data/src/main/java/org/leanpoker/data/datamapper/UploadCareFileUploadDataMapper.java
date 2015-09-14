package org.leanpoker.data.datamapper;

import org.leanpoker.data.model.UploadedFile;
import org.leanpoker.data.response.UploadCareFileUploadResponseModel;

/**
 * Created by tmolnar on 14/09/15.
 */
public class UploadCareFileUploadDataMapper {

    public UploadedFile transform(UploadCareFileUploadResponseModel model) {
        return new UploadedFile(model.getFile());
    }
}
