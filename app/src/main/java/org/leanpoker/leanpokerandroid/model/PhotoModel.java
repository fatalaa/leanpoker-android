package org.leanpoker.leanpokerandroid.model;

import org.leanpoker.data.model.Photo;

/**
 * Created by tmolnar on 21/09/15.
 */
public class PhotoModel {

    private String owner;

    private String uploaded;

    private String url;

    public PhotoModel(final Photo photo) {
        this.owner = photo.getOwner();
        this.uploaded = photo.getUploaded();
        this.url = photo.getUrl();
    }

    public String getOwner() {
        return owner;
    }

    public String getUploaded() {
        return uploaded;
    }

    public String getUrl() {
        return url;
    }
}
