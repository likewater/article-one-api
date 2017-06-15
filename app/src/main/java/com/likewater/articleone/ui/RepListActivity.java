package com.likewater.articleone.ui;

/* RepListActivity creates an arraylist of reps and feeds Recycler view */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import okhttp3.Call;
import okhttp3.Callback;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;

import com.likewater.articleone.Constants;
import com.likewater.articleone.R;
import com.likewater.articleone.adapters.RepListAdapter;
import com.likewater.articleone.models.Rep;
import com.likewater.articleone.services.ProService;

import static com.likewater.articleone.services.ProService.findReps;

public class RepListActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentCongress;
    private String mRecentState;

    public static final String TAG = RepListActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private RepListAdapter mAdapter;

    public ArrayList<Rep> mReps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rep_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String congress = intent.getStringExtra("congress");
        String state = intent.getStringExtra("state");
//      mLocationTextView.setText("Here Are Your " + state + " Reps");

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentCongress = mSharedPreferences.getString(Constants.PREFERENCES_CONGRESS_KEY, null);
        mRecentState = mSharedPreferences.getString(Constants.PREFERENCES_STATE_KEY, null);
        if (mRecentCongress != null && mRecentState != null) {
            getReps(mRecentCongress, mRecentState);
        }
        getReps(congress, state);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

           @RequiresApi(api = Build.VERSION_CODES.KITKAT)
           @Override
            public boolean onQueryTextSubmit(String query) {

               addToSharedPreferences(query);
               getReps(mRecentCongress, query);
               return false;

//               if(Objects.equals(query, mRecentCongress)) {
//                   addToSharedPreferences(query, mRecentState);
//                   getReps(query, mRecentState);
//                   Log.d("congress", mRecentCongress);
//                   //addToSharedPreferences(query, mRecentState);
//               }
//               else if (Objects.equals(query, mRecentState)) {
//                   addToSharedPreferences(mRecentCongress, query);
//                   getReps(mRecentCongress, query);
//                   //addToSharedPreferences(mRecentCongress, query);
//               }
               //return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void getReps(String congress, String state) {
        final ProService proService = new ProService();
        findReps(congress, state, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response)  {
                mReps = proService.processResults(response);

                RepListActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        mAdapter = new RepListAdapter(getApplicationContext(), mReps);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(RepListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                      }

                        //ArrayAdapter adapter = new ArrayAdapter(RepListActivity.this, android.R.layout.simple_list_item_1, repNames);
                        //mListView.setAdapter(adapter);

//                        for (Rep rep : mReps) {
//                            Log.d(TAG, "Name: " + rep.getName());
//                            Log.d(TAG, "Role: " + rep.getRole());
//                        }

                   // }
                });
            }
        });

    }

    private void addToSharedPreferences(String state) {
        //mEditor.putString(Constants.PREFERENCES_CONGRESS_KEY, congress).apply();
        mEditor.putString(Constants.PREFERENCES_STATE_KEY, state).apply();
    }
}