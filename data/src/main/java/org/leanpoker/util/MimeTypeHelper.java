package org.leanpoker.util;

import android.webkit.MimeTypeMap;

/**
 * Created by tmolnar on 14/09/15.
 */
public class MimeTypeHelper {
    public static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }
}
