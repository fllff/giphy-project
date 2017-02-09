package com.example.hn.imagesearchforgiphy.model;

/**
 * Created by hn on 2017-02-08.
 */

public class GiphyItem {
    private String URL;

    public GiphyItem(String URL){
        this.URL = URL;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
