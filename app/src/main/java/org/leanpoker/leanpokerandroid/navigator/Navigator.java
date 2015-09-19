package org.leanpoker.leanpokerandroid.navigator;

import android.app.Activity;

import org.leanpoker.leanpokerandroid.view.activity.EventsActivity;

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
            from.startActivity(EventsActivity.createIntent(from));
            from.finish();
        }
    }
}
