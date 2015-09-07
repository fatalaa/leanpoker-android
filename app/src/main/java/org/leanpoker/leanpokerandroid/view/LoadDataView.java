package org.leanpoker.leanpokerandroid.view;

import android.content.Context;

/**
 * Created by tbalogh on 06/09/15.
 */
public interface LoadDataView {
	void showLoading();
	void hideLoading();
	void showError(String message);
	Context getContext();
}
