package com.ponkanlab.ponkangithubclient.model;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

/**
 * Created by diegoy on 06/04/15.
 */
public class GistItem {
    private String description;
    private String url;

    public static List<GistItem> buildList(String json) {
        Gson gson = new Gson();
        GistItem[] items = gson.fromJson(json, GistItem[].class);
        return Arrays.asList(items);
    }

    @Override
    public String toString() {
        return description;
    }
}
