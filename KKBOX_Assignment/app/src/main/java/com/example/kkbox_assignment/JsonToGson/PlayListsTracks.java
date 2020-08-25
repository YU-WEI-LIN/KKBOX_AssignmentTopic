package com.example.kkbox_assignment.JsonToGson;

public class PlayListsTracks {
    private PlayListsTracksSummary summary;
    private PlayListsTracksData[] data;
    private PlayListsTracksPaging paging;

    public PlayListsTracksSummary getSummary() {
        return this.summary;
    }

    public void setSummary(PlayListsTracksSummary summary) {
        this.summary = summary;
    }

    public PlayListsTracksData[] getData() {
        return this.data;
    }

    public void setData(PlayListsTracksData[] data) {
        this.data = data;
    }

    public PlayListsTracksPaging getPaging() {
        return this.paging;
    }

    public void setPaging(PlayListsTracksPaging paging) {
        this.paging = paging;
    }
}
