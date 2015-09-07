package org.leanpoker.leanpokerandroid.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.leanpoker.leanpokerandroid.R;

/**
 * Created by tbalogh on 06/09/15.
 */
public class EventsActivity extends BaseActivity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actvity_event_list);
	}

	public static Intent createIntent(final Context context) {
		return new Intent(context, EventsActivity.class);
	}
}
