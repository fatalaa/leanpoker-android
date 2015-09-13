package org.leanpoker.data.response.submodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tmolnar on 12/09/15.
 */
public class EventResponseModel {
    private String address;

    private String city;

    private String county;

    private String date;

    @SerializedName("event-url")
    private String eventUrl;

    @SerializedName("formated-date")
    private String formattedDate;

    private String host;

    private String id;

    private String latitude;

    private String longitude;

    private String owner;

    @SerializedName("rolling_sum_done_until")
    private String rollingSumDoneUntil;

    private String status;

    private List<TeamResponseModel> teams;

    private List<MediaResponseModel> images;

    private String type;

    @SerializedName("next_game")
    private String nextGame;

    @SerializedName("started_at")
    private String startedAt;

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getDate() {
        return date;
    }

    public String getEventUrl() {
        return eventUrl;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public String getHost() {
        return host;
    }

    public String getId() {
        return id;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getOwner() {
        return owner;
    }

    public String getRollingSumDoneUntil() {
        return rollingSumDoneUntil;
    }

    public String getStatus() {
        return status;
    }

    public List<TeamResponseModel> getTeams() {
        return teams;
    }

    public List<MediaResponseModel> getImages() {
        return images;
    }

    public String getType() {
        return type;
    }

    public String getNextGame() {
        return nextGame;
    }

    public String getStartedAt() {
        return startedAt;
    }
}
