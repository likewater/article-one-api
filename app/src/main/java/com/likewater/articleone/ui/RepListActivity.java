package com.likewater.articleone.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import okhttp3.Call;
import okhttp3.Callback;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;

import com.likewater.articleone.R;
import com.likewater.articleone.adapters.RepListAdapter;
import com.likewater.articleone.models.Rep;
import com.likewater.articleone.services.ProService;

import static com.likewater.articleone.services.ProService.findReps;

public class RepListActivity extends AppCompatActivity {
    public static final String TAG = RepListActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private RepListAdapter mAdapter;
//    @Bind(R.id.locationTextView) TextView mLocationTextView;
    //@Bind(R.id.repListView) ListView mListView;

    public ArrayList<Rep> mReps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rep_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String congress = intent.getStringExtra("congress");
        String state = intent.getStringExtra("state");
//        mLocationTextView.setText("Here Are Your " + state + " Reps");

        getReps(congress, state);
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


//                        String[] repNames = new String[mReps.size()];
//                        for (int i = 0; i < repNames.length; i++) {
//                            repNames[i] = mReps.get(i).getName() + ", " + mReps.get(i).getRole();

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

    }}