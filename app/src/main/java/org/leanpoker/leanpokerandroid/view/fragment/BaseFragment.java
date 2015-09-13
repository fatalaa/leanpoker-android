package org.leanpoker.leanpokerandroid.view.fragment;


import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by tbalogh on 06/09/15.
 */
public class BaseFragment extends Fragment {

	protected void showToastMessage(String message) {
		Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
	}
}
