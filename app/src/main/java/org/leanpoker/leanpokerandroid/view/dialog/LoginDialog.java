package org.leanpoker.leanpokerandroid.view.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;


/**
 * Created by tbalogh on 22/09/15.
 */
public class LoginDialog extends DialogFragment implements Dialog.OnClickListener {
	public interface LoginDialogListener {
		void onSignUpClicked();
	}

	private LoginDialogListener mListener;
	private Context             mContext;

	public void setLoginDialogListener(final LoginDialogListener listener) {
		mListener = listener;
	}

	@Override
	public Dialog onCreateDialog(final Bundle savedInstanceState) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		builder.setTitle("Login");
		builder.setMessage("You have to be signed in to uploadPhotoToUploadCare photos.");
		builder.setPositiveButton("Sign in", this);
		builder.setNegativeButton("Cancel", this);
		return builder.create();
	}

	@Override
	public void onAttach(final Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onClick(final DialogInterface dialog, final int which) {
		switch (which) {
			case DialogInterface.BUTTON_POSITIVE:
				if (mListener != null) {
					mListener.onSignUpClicked();
				}
				break;
			default:
				break;
		}
	}
}
