package org.leanpoker.leanpokerandroid.navigator;

import android.app.Activity;
import android.content.Context;

import org.leanpoker.leanpokerandroid.view.activity.EventActivity;
import org.leanpoker.leanpokerandroid.view.activity.EventListActivity;

/**
 * Created by tmolnar on 07/09/15.
 */
public class Navigator {

    private static Navigator mInstance = new Navigator();

    private Navigator() {}

    public static Navigator getInstance() {
        return mInstance;
    }

    public void naviateToEventListActivity(Activity from) {
        if (from != null) {
            from.startActivity(EventListActivity.createIntent(from));
            from.finish();
        }
    }

	public void navigateToEventActivity(final Context context, final String eventId) {
		if (context == null || eventId == null) {
			return;
		}
		context.startActivity(EventActivity.createIntent(context, eventId));
	}
}
