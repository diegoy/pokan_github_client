package com.ponkanlab.ponkangithubclient.io;

import android.util.Log;

import com.ponkanlab.ponkangithubclient.model.GistItem;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

/**
 * Created by diegoy on 01/04/15.
 */
public class GithubConnector {

    private final OkHttpClient client = new OkHttpClient();

    public List<GistItem> doGet(String path) throws Exception {
        Request request = new Request.Builder()
                .url("https://api.github.com/users/" + path)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        Headers responseHeaders = response.headers();
        for (int i = 0; i < responseHeaders.size(); i++) {
            Log.d("githubClient", responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }

        String body = response.body().string();
        List<GistItem> result = GistItem.buildList(body);;

        return result ;
    }
}
