package org.leanpoker.leanpokerandroid.view.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by tmolnar on 23/09/15.
 */
public class ChoosePhotoAppDialog extends AlertDialog {

    public enum PhotoAppType {
        GALLERY,
        CAMERA
    }

    protected ChoosePhotoAppDialog(final Context context) {
        super(context);
    }

    protected ChoosePhotoAppDialog(final Context context, final int theme) {
        super(context, theme);
    }

    protected ChoosePhotoAppDialog(final Context context, final boolean cancelable, final OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public interface ChoosePhotoAppDialogListener {
        void onSelectedAppType(PhotoAppType type);
    }
}
