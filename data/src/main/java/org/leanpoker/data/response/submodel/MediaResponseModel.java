package org.leanpoker.data.response.submodel;

import java.net.URL;

/**
 * Created by tmolnar on 12/09/15.
 */
public class MediaResponseModel {
    private String owner;

    private String uploaded;

    private URL url;

    public String getOwner() {
        return owner;
    }

    public String getUploaded() {
        return uploaded;
    }

    public URL getUrl() {
        return url;
    }
}
