package org.leanpoker.leanpokerandroid.view;

/**
 * Created by tbalogh on 06/09/15.
 */
public interface LoadDataView {
	void showLoading();

	void hideLoading();

	void showLoadingError(String message);
}
