package org.leanpoker.leanpokerandroid.view;

import android.app.Activity;
import android.content.Context;

/**
 * Created by tbalogh on 11/10/15.
 */
public interface BaseView {
	void showError(final String errorMessage);

	Context getContext();

	Activity getActivity();
}
