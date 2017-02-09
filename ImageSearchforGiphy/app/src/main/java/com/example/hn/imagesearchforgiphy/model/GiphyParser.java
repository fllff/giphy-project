package com.example.hn.imagesearchforgiphy.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hn on 2017-02-08.
 */

public class GiphyParser {

    public GiphyParser() {
    }

    /**
     * Paseing JSON
     */
    public List<GiphyItem> parse(String s){
        ArrayList<GiphyItem> giphyList = new ArrayList<GiphyItem>();
        String url;
        try {
            JSONObject json = new JSONObject(s);
            JSONArray dataArr = json.getJSONArray("data");

            for (int i = 0; i < dataArr.length(); i++) {
                json = dataArr.getJSONObject(i);
                json = json.getJSONObject("images");
                json = json.getJSONObject("fixed_width_downsampled");
                url = json.getString("url");

                giphyList.add(new GiphyItem(url));
            }
        } catch (Exception e) {

        }

        return giphyList;
    }
}
