package org.leanpoker.leanpokerandroid.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;

/**
 * Created by tbalogh on 23/09/15.
 */
public class DialogFactory {
	private DialogFactory() {
		// Util class
	}

	public static AlertDialog createLoginDialog(final Context context,
	                                            final OnClickListener onClickListener) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Login");
		builder.setMessage("To upload pictures you have to be signed in!");
		builder.setPositiveButton("Sign in", onClickListener);
		builder.setNegativeButton(context.getString(android.R.string.cancel), onClickListener);
		return builder.create();
	}
}
