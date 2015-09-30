package org.leanpoker.leanpokerandroid;

import android.app.Application;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.flurry.android.FlurryAgent;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.SaveCallback;

import org.leanpoker.UserManager;
import org.leanpoker.api.NetworkManager;
import org.leanpoker.leanpokerandroid.view.image.ImageLoader;

import io.fabric.sdk.android.Fabric;

/**
 * Created by tmolnar on 07/09/15.
 */
public class MainApplication extends Application {

    public static final String TAG = MainApplication.class.getSimpleName();

    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        initAnalytics();
        initManagers();
        initHawk();
        initImageLoader();
    }

    private void initAnalytics() {
        Fabric.with(this, new Crashlytics());
        FlurryAgent.init(this, "6F94F8GCZZ2Q3NFJ4PB2");
        FlurryAgent.setCaptureUncaughtExceptions(false);
        Parse.initialize(this, "LzrBjiRx1KJeuD6q5kAYVd0JioPEj20ZGhPZ752F",
                "Tp249eqGUk8p4rqbCjSsKzae7bCS9zKt5laPOc3i");
        ParsePush.subscribeInBackground("default", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Log.d(TAG, "done() called with: " + "e = [" + e + "]");
            }
        });
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

    private void initHawk() {
        Hawk.init(this)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setLogLevel(LogLevel.FULL)
                .setStorage(HawkBuilder.newSharedPrefStorage(this))
                .build();
    }

    private void initManagers() {
        UserManager.getInstance().init(this);
        NetworkManager.getInstance().init(this);
    }

    private void initImageLoader() {
        ImageLoader.getInstance().init(this);
    }
}
