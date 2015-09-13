package org.leanpoker.data.response.submodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tmolnar on 12/09/15.
 */
public class TeamResponseModel {
    private String language;

    @SerializedName("language_id")
    private String languageId;

    private String name;

    private String owner;

    private String points;

    private String status;

    private List<String> members;

    public String getLanguage() {
        return language;
    }

    public String getLanguageId() {
        return languageId;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getPoints() {
        return points;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getMembers() {
        return members;
    }
}
