package com.likewater.articleone.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.likewater.articleone.Constants;
import com.likewater.articleone.R;
import com.likewater.articleone.adapters.FirebaseRepViewHolder;
import com.likewater.articleone.models.Rep;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedRepListActivity extends AppCompatActivity {
    private DatabaseReference mRepReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rep_list);
        ButterKnife.bind(this);

        mRepReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_REPS);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Rep, FirebaseRepViewHolder>
                (Rep.class, R.layout.rep_list_item, FirebaseRepViewHolder.class, mRepReference) {

            @Override
            protected void populateViewHolder(FirebaseRepViewHolder viewHolder, Rep model, int position) {
                viewHolder.bindRep(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
