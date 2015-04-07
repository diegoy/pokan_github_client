package com.ponkanlab.ponkangithubclient.io;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ponkanlab.ponkangithubclient.model.GistItem;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by diegoy on 01/04/15.
 */
public class GithubConnector extends AsyncTask<String, Integer, List<GistItem>> {

    private final OkHttpClient client = new OkHttpClient();

    private final ListView gistListView;
    private final Context context;

    public GithubConnector(Context context, ListView gistListView) {
        this.context = context;
        this.gistListView = gistListView;
    }

    private List<GistItem> run() throws Exception {
        Request request = new Request.Builder()
                .url("https://api.github.com/users/diegoy/gists")
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

    @Override
    protected List<GistItem> doInBackground(String... params) {
        try {
            return run();
        } catch (Exception e) {
            Log.e("githubClient", "ihh", e);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    protected void onPostExecute(List<GistItem> result) {
        Log.d("githubClient", result.toString());
        ArrayAdapter<GistItem> arrayAdapter = new ArrayAdapter<GistItem>(context,
                android.R.layout.simple_list_item_1,
                result);

        gistListView.setAdapter(arrayAdapter);
    }
}
