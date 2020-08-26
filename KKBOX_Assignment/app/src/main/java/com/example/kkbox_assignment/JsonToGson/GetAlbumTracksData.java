package com.example.kkbox_assignment.JsonToGson;

public class GetAlbumTracksData {
    private int duration;
    private String name;
    private String[] available_territories;
    private String isrc;
    private int track_number;
    private boolean explicitness;
    private String id;
    private String url;

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getAvailable_territories() {
        return this.available_territories;
    }

    public void setAvailable_territories(String[] available_territories) {
        this.available_territories = available_territories;
    }

    public String getIsrc() {
        return this.isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    public int getTrack_number() {
        return this.track_number;
    }

    public void setTrack_number(int track_number) {
        this.track_number = track_number;
    }

    public boolean getExplicitness() {
        return this.explicitness;
    }

    public void setExplicitness(boolean explicitness) {
        this.explicitness = explicitness;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
