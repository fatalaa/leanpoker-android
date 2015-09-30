package org.leanpoker.leanpokerandroid.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.leanpoker.data.model.Photo;

/**
 * Created by tmolnar on 21/09/15.
 */
public class PhotoModel implements Parcelable {

    private String owner;

    private String uploaded;

    private String url;

    public PhotoModel(final Photo photo) {
        this.owner = photo.getOwner();
        this.uploaded = photo.getUploaded();
        this.url = photo.getUrl();
    }

    protected PhotoModel(Parcel in) {
        owner = in.readString();
        uploaded = in.readString();
        url = in.readString();
    }

    public static final Creator<PhotoModel> CREATOR = new Creator<PhotoModel>() {
        @Override
        public PhotoModel createFromParcel(Parcel in) {
            return new PhotoModel(in);
        }

        @Override
        public PhotoModel[] newArray(int size) {
            return new PhotoModel[size];
        }
    };

    public String getOwner() {
        return owner;
    }

    public String getUploaded() {
        return uploaded;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(owner);
        dest.writeString(uploaded);
        dest.writeString(url);
    }
}
