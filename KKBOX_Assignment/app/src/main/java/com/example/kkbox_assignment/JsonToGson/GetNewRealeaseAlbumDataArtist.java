package com.example.kkbox_assignment.JsonToGson;

public class GetNewRealeaseAlbumDataArtist {
    private GetNewRealeaseAlbumDataArtistImages[] images;
    private String name;
    private String id;
    private String url;

    public GetNewRealeaseAlbumDataArtistImages[] getImages() {
        return this.images;
    }

    public void setImages(GetNewRealeaseAlbumDataArtistImages[] images) {
        this.images = images;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
