package org.leanpoker.leanpokerandroid;

import android.app.Application;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.flurry.android.FlurryAgent;
import com.onesignal.OneSignal;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;

import org.json.JSONObject;
import org.leanpoker.UserManager;
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
        OneSignal
                .startInit(this)
                .setNotificationOpenedHandler(new OneSignal.NotificationOpenedHandler() {
                    @Override
                    public void notificationOpened(String message,
                                                   JSONObject additionalData,
                                                   boolean isActive) {
                        try {
                            if (additionalData != null) {
                                if (additionalData.has("actionSelected"))
                                    Log.d("OneSignalExample", "OneSignal notification button with id " + additionalData.getString("actionSelected") + " pressed");

                                Log.d("OneSignalExample", "Full additionalData:\n" + additionalData.toString());
                            }
                        } catch (Throwable t) {
                            t.printStackTrace();
                        }
                    }
                })
                .init();
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.DEBUG, OneSignal.LOG_LEVEL.DEBUG);
    }

    private void initAnalytics() {
        Fabric.with(this, new Crashlytics());
        FlurryAgent.init(this, "6F94F8GCZZ2Q3NFJ4PB2");
        FlurryAgent.setCaptureUncaughtExceptions(false);
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
    }

    private void initImageLoader() {
        ImageLoader.getInstance().init(this);
    }
}
