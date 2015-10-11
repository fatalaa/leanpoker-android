package org.leanpoker.leanpokerandroid.view;

import org.leanpoker.leanpokerandroid.model.PhotoModel;

/**
 * Created by tmolnar on 29/09/15.
 */
public interface FullScreenPhotoView extends LoadDataView {
	void renderPhoto(final PhotoModel photoModel);

	void showOverlay(boolean showOverlay);
}
