package org.leanpoker.domain.interactor;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import org.leanpoker.api.NetworkManager;
import org.leanpoker.data.model.GithubUser;
import org.leanpoker.data.model.UploadedFile;
import org.leanpoker.data.store.TokenStore;
import org.leanpoker.data.store.UserStore;
import org.leanpoker.domain.util.MimeTypeHelper;
import org.leanpoker.domain.util.PathMagic;

import java.io.File;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by tmolnar on 01/10/15.
 */
public class ImageUploadInteractor extends BaseInteractor {

    private static final String TAG = ImageUploadInteractor.class.getSimpleName();
    private final Uri mUri;
    private final Context mContext;
    private final String mEventId;

    public ImageUploadInteractor(final Context context, final Uri uri, final String eventId) {
        super();
        mContext = context;
        mUri = uri;
        mEventId = eventId;
    }

    @Override
    protected Observable buildInteractorObservable() {
        String realPath = PathMagic.getRealPathFromUri(mContext, mUri);
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
                .map(new Func1<UploadedFile,String>() {
                    @Override
                    public String call(final UploadedFile uploadedFile) {
                        return String.format("https://ucarecdn.com/%s/nth/", uploadedFile.getUuid());
                    }
                })
                .map(new Func1<String, Observable>() {
                    @Override
                    public Observable call(final String imageUrl) {
                        GithubUser githubUser = UserStore.getInstance().getUser();
                        return NetworkManager.getInstance().uploadPhotoToLeanPoker(
                                mEventId,
                                githubUser.getPrimaryEmail().getEmail(),
                                TokenStore.getInstance().getAccessToken().getAccessToken(),
                                imageUrl
                        );
                    }
                });
    }
}
