package org.leanpoker.domain.interactor;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import org.leanpoker.api.NetworkManager;
import org.leanpoker.data.model.UploadedFile;
import org.leanpoker.data.store.TokenStore;
import org.leanpoker.data.store.UserStore;
import org.leanpoker.domain.util.MimeTypeHelper;
import org.leanpoker.domain.util.PathMagic;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by tmolnar on 01/10/15.
 */
public class ImageUploadInteractor extends BaseInteractor {

    private static final String TAG = ImageUploadInteractor.class.getSimpleName();
    private final Uri mUri;
    private final Context mContext;
    private final String mEventId;
    private final boolean mCameraBased;

    public ImageUploadInteractor(final Context context, final Uri uri, final String eventId,
                                 final boolean cameraBased) {
        super();
        mContext = context;
        mUri = uri;
        mEventId = eventId;
        mCameraBased = cameraBased;
    }

    @Override
    protected Observable buildInteractorObservable() {
        String realPath;
        if (mCameraBased) {
            realPath = mUri.getPath();
        } else {
            realPath = PathMagic.getPath(mContext, mUri);
        }
        File file = new File(realPath);
        if (file.canRead()) {
            Log.d(TAG, String.format("%s can be readed", file.getAbsolutePath()));
        } else {
            Log.d(TAG, String.format("%s cannnot be readed", file.getAbsolutePath()));
        }
        String mimeType = MimeTypeHelper.getMimeType(realPath);
        mimeType = mimeType != null ? mimeType : "image/png";
        return NetworkManager
                .getInstance()
                .uploadPhotoToUploadCare(file, mimeType)
                .map(new Function<UploadedFile,String>() {
                    @Override
                    public String apply(final UploadedFile uploadedFile) {
                        return String.format("https://www.ucarecdn.com/%s/", uploadedFile.getUuid());
                    }
                })
                .map(new Function<String, Boolean>() {
                    @Override
                    public Boolean apply(final String imageUrl) {
                        return NetworkManager.getInstance().uploadPhotoToLeanPoker(
                                mEventId,
                                UserStore.getInstance().getUser().getLogin(),
                                TokenStore.getInstance().getAccessToken().getAccessToken(),
                                imageUrl
                        );
                    }
                });
    }
}
