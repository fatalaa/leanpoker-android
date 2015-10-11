package org.leanpoker.leanpokerandroid.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import org.leanpoker.leanpokerandroid.view.BaseView;

/**
 * Created by tbalogh on 06/09/15.
 */
public class BaseFragment extends Fragment implements BaseView {

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	protected void showToastMessage(String message) {
		Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void showError(final String errorMessage) {
		showToastMessage(errorMessage);
	}
}
