package com.example.kkbox_assignment.JsonToGson;

public class PlayListsTracksPaging {
    private Object next;
    private int offset;
    private Object previous;
    private int limit;

    public Object getNext() {
        return this.next;
    }

    public void setNext(Object next) {
        this.next = next;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Object getPrevious() {
        return this.previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
