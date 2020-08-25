package com.example.kkbox_assignment.JsonToGson;

public class GetNewRealeaseAlbum {
    private GetNewRealeaseAlbumSummary summary;
    private GetNewRealeaseAlbumData[] data;
    private GetNewRealeaseAlbumPaging paging;

    public GetNewRealeaseAlbumSummary getSummary() {
        return this.summary;
    }

    public void setSummary(GetNewRealeaseAlbumSummary summary) {
        this.summary = summary;
    }

    public GetNewRealeaseAlbumData[] getData() {
        return this.data;
    }

    public void setData(GetNewRealeaseAlbumData[] data) {
        this.data = data;
    }

    public GetNewRealeaseAlbumPaging getPaging() {
        return this.paging;
    }

    public void setPaging(GetNewRealeaseAlbumPaging paging) {
        this.paging = paging;
    }
}
