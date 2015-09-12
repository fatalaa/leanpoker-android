package org.leanpoker.leanpokerandroid;

import android.app.Application;
import android.util.Log;

import com.flurry.android.FlurryAgent;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.SaveCallback;

/**
 * Created by tmolnar on 07/09/15.
 */
public class MainApplication extends Application {

    public static final String TAG = MainApplication.class.getSimpleName();

    public void onCreate() {
        super.onCreate();
        FlurryAgent.init(this, "6F94F8GCZZ2Q3NFJ4PB2");
        FlurryAgent.setCaptureUncaughtExceptions(false);
        Parse.initialize(this, "LzrBjiRx1KJeuD6q5kAYVd0JioPEj20ZGhPZ752F", "Tp249eqGUk8p4rqbCjSsKzae7bCS9zKt5laPOc3i");
        ParsePush.subscribeInBackground("default", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Log.d(TAG, "done() called with: " + "e = [" + e + "]");
            }
        });
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
