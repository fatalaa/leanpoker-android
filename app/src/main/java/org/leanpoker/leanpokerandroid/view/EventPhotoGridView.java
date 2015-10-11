package org.leanpoker.leanpokerandroid.view;

import org.leanpoker.leanpokerandroid.model.PhotoModel;

import java.util.List;

/**
 * Created by tmolnar on 21/09/15.
 */
public interface EventPhotoGridView extends LoadDataView, LoginDialogHostView,
		ChoosePhotoAppDialogHostView {
	void renderPhotoList(List<PhotoModel> photoModelList);
}
