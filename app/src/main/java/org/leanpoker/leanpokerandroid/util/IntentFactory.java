package org.leanpoker.leanpokerandroid.util;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.provider.MediaStore;

import org.leanpoker.leanpokerandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmolnar on 30/09/15.
 */
public class IntentFactory {

    /**
     * Create an intent for images
     *
     * @param imageUri The response image uri
     * @return The intent
     */
    public static Intent createImageChooserIntent(Context c, Uri imageUri) {
        Intent galleryIntent = createGalleryIntent();
        List<Intent> cameraIntents = createCameraIntents(c, imageUri);
        String message = c.getResources().getString(R.string.title_choose_message);
        Intent chooserIntent = Intent.createChooser(cameraIntents.get(0), message); // galleryintent
        chooserIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        cameraIntents.remove(0);
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
                cameraIntents.toArray(new Parcelable[cameraIntents.size()]));
        return chooserIntent;
    }

    /**
     * Create an intent for image capturing
     *
     * @param file The file to store the image
     * @return The intent
     */
    public static List<Intent> createCameraIntents(Context c, Uri file) {
        List<Intent> cameraIntents = new ArrayList<>();
        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        PackageManager packageManager = c.getPackageManager();

        final List<ResolveInfo> captureActivities = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : captureActivities) {
            String packageName = res.activityInfo.packageName;
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(packageName);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            cameraIntents.add(intent);
        }
        return cameraIntents;
    }

    public static Intent createCameraIntent(Context c, Uri file) {
        return createCameraIntents(c, file).get(0);
    }

    /**
     * Create an intent for image galleries
     *
     * @return The intent
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static Intent createGalleryIntent() {
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        galleryIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        return galleryIntent;
    }
}
