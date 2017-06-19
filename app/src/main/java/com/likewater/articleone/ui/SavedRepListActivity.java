package com.likewater.articleone.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.likewater.articleone.Constants;
import com.likewater.articleone.R;
import com.likewater.articleone.adapters.FirebaseRepListAdapter;
import com.likewater.articleone.adapters.FirebaseRepViewHolder;
import com.likewater.articleone.models.Rep;
import com.likewater.articleone.util.OnStartDragListener;
import com.likewater.articleone.util.SimpleItemTouchHelperCallback;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedRepListActivity extends AppCompatActivity implements OnStartDragListener {
    private DatabaseReference mRepReference;
    private FirebaseRepListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rep_list);
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mRepReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_REPS)
                .child(uid);
        Log.d("47", uid);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        String uid = user.getUid();

        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_REPS)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);


        mFirebaseAdapter = new FirebaseRepListAdapter
               (Rep.class, R.layout.rep_list_item_drag, FirebaseRepViewHolder.class, query, this, this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new

            LinearLayoutManager(this));

        mRecyclerView.setAdapter(mFirebaseAdapter);
        Log.d("69", String.valueOf(mRepReference));

            ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
            mItemTouchHelper =new

            ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
