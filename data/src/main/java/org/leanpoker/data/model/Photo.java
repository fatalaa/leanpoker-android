package org.leanpoker.data.model;

import java.net.URL;

/**
 * Created by tmolnar on 21/09/15.
 */
public class Photo {
    private String owner;

    private String uploaded;

    private String url;

    public Photo(final String owner, final String uploaded, final String url) {
        this.owner = owner;
        this.uploaded = uploaded;
        this.url = url;
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
