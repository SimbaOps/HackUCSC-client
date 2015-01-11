package com.sccommute.commute;

/**
 * Created by simba on 1/11/15.
 */
public class BusSched {
    private String start;
    private String mid;
    private String end;

    public BusSched(String start, String mid, String end) {
        this.start = start;
        this.mid = mid;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public String getMid() {
        return mid;
    }

    public String getEnd() {
        return end;
    }
}
