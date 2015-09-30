package org.leanpoker.leanpokerandroid.view;

import org.leanpoker.leanpokerandroid.model.PhotoModel;

import java.util.List;

/**
 * Created by tmolnar on 29/09/15.
 */
public interface FullScreenPhotoView extends LoadDataView {
    void renderPhoto(final PhotoModel photoModel);
}
