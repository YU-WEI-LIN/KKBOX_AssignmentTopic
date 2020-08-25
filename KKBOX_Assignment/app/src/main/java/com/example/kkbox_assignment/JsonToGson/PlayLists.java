package com.example.kkbox_assignment.JsonToGson;

public class PlayLists {
    private PlayListsOwner owner;
    private PlayListsImages[] images;
    private String updated_at;
    private String description;
    private String id;
    private String title;
    private PlayListsTracks tracks;
    private String url;

    public PlayListsOwner getOwner() {
        return this.owner;
    }

    public void setOwner(PlayListsOwner owner) {
        this.owner = owner;
    }

    public PlayListsImages[] getImages() {
        return this.images;
    }

    public void setImages(PlayListsImages[] images) {
        this.images = images;
    }

    public String getUpdated_at() {
        return this.updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PlayListsTracks getTracks() {
        return this.tracks;
    }

    public void setTracks(PlayListsTracks tracks) {
        this.tracks = tracks;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
