package com.example.kkbox_assignment.JsonToGson;

public class GetLatestFeaturedPlaylistsData {
    private GetLatestFeaturedPlaylistsDataOwner owner;
    private GetLatestFeaturedPlaylistsDataImages[] images;
    private String updated_at;
    private String description;
    private String id;
    private String title;
    private String url;

    public GetLatestFeaturedPlaylistsDataOwner getOwner() {
        return this.owner;
    }

    public void setOwner(GetLatestFeaturedPlaylistsDataOwner owner) {
        this.owner = owner;
    }

    public GetLatestFeaturedPlaylistsDataImages[] getImages() {
        return this.images;
    }

    public void setImages(GetLatestFeaturedPlaylistsDataImages[] images) {
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

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
