package com.ponkanlab.ponkangithubclient.io;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.ponkanlab.ponkangithubclient.model.GistItem;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.Collections;
import java.util.List;

/**
 * Created by diegoy on 13/04/15.
 */
public class ListGists extends AsyncTask<String, Integer, List<GistItem>> {

    private Context context;
    private ListView gistListView;
    private ProgressBar progressBar;

    public ListGists(Context context, ListView gistListView, ProgressBar progressBar) {
        this.context = context;
        this.gistListView = gistListView;
        this.progressBar = progressBar;
    }

    @Override
    protected List<GistItem> doInBackground(String... params) {
        try {
            return new GithubConnector().doGet(params[0]+"/gists");
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
        progressBar.setVisibility(View.GONE);
    }
}
