package com.likewater.articleone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.Call;
import okhttp3.Callback;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;

import com.likewater.articleone.Rep;
import com.likewater.articleone.ProService;

import static com.likewater.articleone.ProService.findReps;

public class RepListActivity extends AppCompatActivity {
    public static final String TAG = RepListActivity.class.getSimpleName();
    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.repListView) ListView mListView;

    public ArrayList<Rep> mReps = new ArrayList<>();
//
//    private String[] reps = new String[] {
//           "Ron Wyden", "JeffMerkley", "Suzanne Bonamici", "Greg Walden",
//            "Earl Blumenaur", "Peter DeFazio", "Kurt Schrader"};
//    private String[] types = new String[] {
//            "Senator", "Senator", "Representative", "Representative", "Representative",
//            "Representative", "Representative"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rep_list);
        ButterKnife.bind(this);
//        MyRepsArrayAdapter adapter = new MyRepsArrayAdapter(this, android.R.layout.simple_list_item_1,
//                reps, types);
        //mListView.setAdapter(adapter);
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String rep = ((TextView) view).getText().toString();
//                Toast.makeText(RepListActivity.this, rep, Toast.LENGTH_LONG).show();
//            }
//        });

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mLocationTextView.setText("Here are all the reps near: " + location);

        getReps(location);
    }

    private void getReps(String location) {
        final ProService proService = new ProService();
        findReps(location, new Callback() {

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

//                        mAdapter = new RepListAdapter(getApplicationContext(), mReps);
//                        mRecyclerView.setAdapter(mAdapter);
//                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RepListActivity.this);
//                        mRecyclerView.setLayoutManager(layoutManager);
//                        mRecyclerView.setHasFixedSize(true);



                        String[] repNames = new String[mReps.size()];
                        for (int i = 0; i < repNames.length; i++) {
                            repNames[i] = mReps.get(i).getLastName();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(RepListActivity.this, android.R.layout.simple_list_item_1, repNames);
                        mListView.setAdapter(adapter);

                        for (Rep rep : mReps) {
                            //Log.d(TAG, "Name: " + rep.getFirstName());
                            Log.d(TAG, "Name: " + rep.getLastName());
                        }


                    }
                });
            }
        });

    }}