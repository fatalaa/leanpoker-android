package org.leanpoker.leanpokerandroid;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;

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
        if (BuildConfig.DEBUG) {
            initAnalytics();
        }
        initManagers();
        initHawk();
        initImageLoader();
    }

    private void initAnalytics() {
        Fabric.with(this, new Crashlytics());
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
