package com.example.kkbox_assignment.JsonToGson;

public class GetLatestFeaturedPlaylistsDataOwner {
    private GetLatestFeaturedPlaylistsDataOwnerImages[] images;
    private String name;
    private String description;
    private String id;
    private String url;

    public GetLatestFeaturedPlaylistsDataOwnerImages[] getImages() {
        return this.images;
    }

    public void setImages(GetLatestFeaturedPlaylistsDataOwnerImages[] images) {
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
