package org.leanpoker.data.model;

/**
 * Created by tmolnar on 14/09/15.
 */
public class UploadedFile {
    private String uuid;

    public UploadedFile(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
