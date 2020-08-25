package com.example.kkbox_assignment.JsonToGson;

public class GetLatestFeaturedPlaylists {
    private GetLatestFeaturedPlaylistsSummary summary;
    private GetLatestFeaturedPlaylistsData[] data;
    private GetLatestFeaturedPlaylistsPaging paging;

    public GetLatestFeaturedPlaylistsSummary getSummary() {
        return this.summary;
    }

    public void setSummary(GetLatestFeaturedPlaylistsSummary summary) {
        this.summary = summary;
    }

    public GetLatestFeaturedPlaylistsData[] getData() {
        return this.data;
    }

    public void setData(GetLatestFeaturedPlaylistsData[] data) {
        this.data = data;
    }

    public GetLatestFeaturedPlaylistsPaging getPaging() {
        return this.paging;
    }

    public void setPaging(GetLatestFeaturedPlaylistsPaging paging) {
        this.paging = paging;
    }
}
