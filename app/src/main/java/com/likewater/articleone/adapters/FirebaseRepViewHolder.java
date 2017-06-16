package com.likewater.articleone.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.likewater.articleone.Constants;
import com.likewater.articleone.R;
import com.likewater.articleone.models.Rep;
import com.likewater.articleone.ui.RepDetailActivity;
import com.likewater.articleone.util.ItemTouchHelperViewHolder;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FirebaseRepViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    //private static final int MAX_WIDTH = 200;
    //private static final int MAX_HEIGHT = 200;
    //@Bind(R.id.legislatorImageView) ImageView mLegislatorImageView;



    View mView;
    Context mContext;
    public ImageView mLegislatorImageView;

    public FirebaseRepViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        //itemView.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void bindRep(Rep rep) {
        TextView nameTextView = (TextView) mView.findViewById(R.id.legislatorNameTextView);
        TextView roleTextView = (TextView) mView.findViewById(R.id.roleNameTextView);
        TextView partyTextView = (TextView) mView.findViewById(R.id.partyTextView);
        mLegislatorImageView = (ImageView) mView.findViewById(R.id.legislatorImageView);

        nameTextView.setText(rep.getName());
        roleTextView.setText(rep.getRole());
        partyTextView.setText(rep.getParty());

        String party = rep.getParty();

        if (Objects.equals(party, "R")) {
            mLegislatorImageView.setImageResource(R.drawable.partyiconrep);
        } else if (Objects.equals(party, "I")) {
            mLegislatorImageView.setImageResource(R.drawable.partyiconind);
        } else {
            mLegislatorImageView.setImageResource(R.drawable.partyicondem);
        }


    }

    @Override
    public void onItemSelected() {
        Log.d("Animation", "onItemSelected");
        // we will add animations here
        itemView.animate()
                .alpha(0.7f)
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(500);
    }

    @Override
    public void onItemClear() {
        Log.d("Animation", "onItemClear");
        itemView.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f);
        // we will add animations here
    }

//    @Override
//    public void onClick(View view) {
//        final ArrayList<Rep> reps = new ArrayList<>();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_REPS);
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    reps.add(snapshot.getValue(Rep.class));
//                }
//
//                int itemPosition = getLayoutPosition();
//
//                Intent intent = new Intent(mContext, RepDetailActivity.class);
//                intent.putExtra("position", itemPosition + "");
//                intent.putExtra("reps", Parcels.wrap(reps));
//
//                mContext.startActivity(intent);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//    }

}
