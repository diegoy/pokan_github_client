package com.ponkanlab.ponkangithubclient.io;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by diegoy on 01/04/15.
 */
public class GithubConnector extends AsyncTask<String, Integer, String> {

    private final OkHttpClient client = new OkHttpClient();

    private final TextView console;

    public GithubConnector(TextView console) {
        this.console = console;
    }

    private String run() throws Exception {
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        Headers responseHeaders = response.headers();
        for (int i = 0; i < responseHeaders.size(); i++) {
            Log.d("githubClient", responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }

        return response.body().string();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            return run();
        } catch (Exception e) {
            Log.e("githubClient", "ihh", e);
        }
        return "";
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("githubClient", result);
        console.append(result);
    }
}
