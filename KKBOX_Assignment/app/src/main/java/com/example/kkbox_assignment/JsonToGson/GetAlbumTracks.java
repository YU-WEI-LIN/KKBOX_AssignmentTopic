package com.example.kkbox_assignment.JsonToGson;

public class GetAlbumTracks {
    private GetAlbumTracksSummary summary;
    private GetAlbumTracksData[] data;
    private GetAlbumTracksPaging paging;

    public GetAlbumTracksSummary getSummary() {
        return this.summary;
    }

    public void setSummary(GetAlbumTracksSummary summary) {
        this.summary = summary;
    }

    public GetAlbumTracksData[] getData() {
        return this.data;
    }

    public void setData(GetAlbumTracksData[] data) {
        this.data = data;
    }

    public GetAlbumTracksPaging getPaging() {
        return this.paging;
    }

    public void setPaging(GetAlbumTracksPaging paging) {
        this.paging = paging;
    }
}
