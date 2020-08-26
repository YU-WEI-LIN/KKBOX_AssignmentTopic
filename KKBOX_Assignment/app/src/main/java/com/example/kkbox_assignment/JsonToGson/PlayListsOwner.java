package com.example.kkbox_assignment.JsonToGson;

public class PlayListsOwner {
    private PlayListsOwnerImages[] images;
    private String name;
    private String description;
    private String id;
    private String url;

    public PlayListsOwnerImages[] getImages() {
        return this.images;
    }

    public void setImages(PlayListsOwnerImages[] images) {
        this.images = images;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
