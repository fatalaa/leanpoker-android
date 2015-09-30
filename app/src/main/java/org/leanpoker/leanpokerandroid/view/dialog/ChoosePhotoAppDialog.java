package org.leanpoker.leanpokerandroid.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ListAdapter;

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

    private ChoosePhotoAppDialogListener    mListener;
    private Context                         mContext;

    public void setListener(final ChoosePhotoAppDialogListener mListener) {
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String [] options = new String[]{"Gallery", "Camera"};
        builder.setTitle("Choose an app");
//        builder.setMessage("You have to choose an app to upload photos!");
//        builder.setPositiveButton("Sign in", this);
        builder.setItems(options, this);
        builder.setNegativeButton("Cancel", this);
        return builder.create();
    }

    @Override
    public void onClick(final DialogInterface dialog, final int which) {
        switch (which) {
            case 1:
                Log.e("ASD", "ADSD");
                break;
        }
    }
}
