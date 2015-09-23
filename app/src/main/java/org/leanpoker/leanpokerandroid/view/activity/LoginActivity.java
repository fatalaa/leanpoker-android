package org.leanpoker.leanpokerandroid.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.leanpoker.leanpokerandroid.R;

/**
 * Created by tmolnar on 16/09/15.
 */
public class LoginActivity extends BaseActivity {

    public static final String TAG = LoginActivity.class.getSimpleName();

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

	public static Intent createIntent(final Context context) {
		return new Intent(context, LoginActivity.class);
	}
}
