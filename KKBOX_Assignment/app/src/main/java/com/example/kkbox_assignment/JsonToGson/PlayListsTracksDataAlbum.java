package com.example.kkbox_assignment.JsonToGson;

public class PlayListsTracksDataAlbum {
    private PlayListsTracksDataAlbumImages[] images;
    private String release_date;
    private PlayListsTracksDataAlbumArtist artist;
    private String name;
    private String[] available_territories;
    private boolean explicitness;
    private String id;
    private String url;

    public PlayListsTracksDataAlbumImages[] getImages() {
        return this.images;
    }

    public void setImages(PlayListsTracksDataAlbumImages[] images) {
        this.images = images;
    }

    public String getRelease_date() {
        return this.release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public PlayListsTracksDataAlbumArtist getArtist() {
        return this.artist;
    }

    public void setArtist(PlayListsTracksDataAlbumArtist artist) {
        this.artist = artist;
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
