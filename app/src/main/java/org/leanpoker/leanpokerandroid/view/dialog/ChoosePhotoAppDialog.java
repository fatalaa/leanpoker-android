package org.leanpoker.leanpokerandroid.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import org.leanpoker.leanpokerandroid.R;

/**
 * Created by tmolnar on 23/09/15.
 */
public class ChoosePhotoAppDialog extends DialogFragment implements Dialog.OnClickListener {

	public interface ChoosePhotoAppDialogListener {
		void onClick(PhotoAppType appType);
	}

	public enum PhotoAppType {
		GALLERY,
		CAMERA
	}

	private ChoosePhotoAppDialogListener mListener;
	private Context                      mContext;

	public void setListener(final ChoosePhotoAppDialogListener mListener) {
		this.mListener = mListener;
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(final Bundle savedInstanceState) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		final String[] options = new String[]{"Gallery", "Camera"};
		final View view = LayoutInflater.from(getContext()).inflate(
				R.layout.title_choose_photo_app_dialog, null, false);
//		builder.setTitle("Choose an app");
		builder.setCustomTitle(view);
		//        builder.setMessage("You have to choose an app to uploadPhotoToUploadCare getPhotos!");
		//        builder.setPositiveButton("Sign in", this);
		builder.setItems(options, this);
		builder.setNegativeButton("Cancel", this);
		return builder.create();
	}

	@Override
	public void onClick(final DialogInterface dialog, final int which) {
		switch (which) {
			case 0:
				mListener.onClick(PhotoAppType.GALLERY);
				break;
			case 1:
				mListener.onClick(PhotoAppType.CAMERA);
				break;
		}
	}
}
