package org.leanpoker.leanpokerandroid.view.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by tmolnar on 23/09/15.
 */
public class LoginDialog extends AlertDialog {

    private Context             mContext;
    private LoginDialogListener mListener;

    protected LoginDialog(final Context context) {
        super(context);
    }

    protected LoginDialog(final Context context, final int theme) {
        super(context, theme);
    }

    protected LoginDialog(final Context context, final boolean cancelable, final OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static LoginDialog createDialog(final Context context, final LoginDialogListener loginDialogListener) {
        LoginDialog dialog = new LoginDialog(context);
        dialog.setListener(loginDialogListener);
        return dialog;
    }

    public void setListener(final LoginDialogListener listener) {
        this.mListener = listener;
    }

    public interface LoginDialogListener {
        void onSuccess();
    }
}
