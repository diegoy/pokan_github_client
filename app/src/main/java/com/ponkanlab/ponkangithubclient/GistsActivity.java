package com.ponkanlab.ponkangithubclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.ponkanlab.ponkangithubclient.io.ListGists;


public class GistsActivity extends Activity {

    private ListView gistListView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gists);
        gistListView = (ListView) findViewById(R.id.gistListView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        new ListGists(this, gistListView, progressBar).execute(user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gists, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
