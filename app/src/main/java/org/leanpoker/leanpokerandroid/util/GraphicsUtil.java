package org.leanpoker.leanpokerandroid.util;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import org.leanpoker.leanpokerandroid.BuildConfig;

import java.io.File;

/**
 * Created by tmolnar on 30/09/15.
 */
public class GraphicsUtil {

    private static final String TAG = GraphicsUtil.class.getSimpleName();

    /**
     * Create a new file in the public pictures directory
     * @return The file
     */
    public static File createImageFile() {
        // Get the directory for the app's private pictures directory.
        File dir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), BuildConfig.FLAVOR);
        if (!dir.exists() && !dir.mkdirs()) {
            Log.e(TAG, "Could not create album directory: " + dir.toString());
        }
        return new File(dir, String.valueOf(System.currentTimeMillis()) + ".jpg");
    }

    public static Uri createImageUri() {
        final File file = createImageFile();
        return Uri.fromFile(file);
    }
}
