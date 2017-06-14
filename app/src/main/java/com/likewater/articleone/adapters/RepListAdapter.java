package com.likewater.articleone.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.likewater.articleone.R;
import com.likewater.articleone.models.Rep;
import com.likewater.articleone.ui.RepDetailActivity;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepListAdapter extends RecyclerView.Adapter<RepListAdapter.RepViewHolder> {
    private ArrayList<Rep> mReps = new ArrayList<>();
    private Context mContext;

    public RepListAdapter(Context context, ArrayList<Rep> reps) {
        mContext = context;
        mReps = reps;
    }

    @Override
    public RepListAdapter.RepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rep_list_item, parent, false);
        RepViewHolder viewHolder = new RepViewHolder(view);
        return viewHolder;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(RepListAdapter.RepViewHolder holder, int position) {
        holder.bindRep(mReps.get(position));

    }

    @Override
    public int getItemCount() {
        return mReps.size();
    }

    public class RepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.legislatorImageView) ImageView mLegislatorImageView;
        @Bind(R.id.legislatorNameTextView) TextView mLegislatorNameTextView;
        @Bind(R.id.roleNameTextView) TextView mRoleNameTextView;
        @Bind(R.id.partyTextView) TextView mPartyTextView;

        private Context mContext;

        public RepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
            public void onClick(View v) {
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, RepDetailActivity.class);

                intent.putExtra("position", itemPosition);
                intent.putExtra("reps", Parcels.wrap(mReps));
                mContext.startActivity(intent);
            }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public void bindRep(Rep rep) {
            mLegislatorNameTextView.setText(rep.getName());
            mRoleNameTextView.setText(rep.getRole());
            mPartyTextView.setText(rep.getParty());
            String party = rep.getParty();

            if (Objects.equals(party, "R")) {
                mLegislatorImageView.setImageResource(R.drawable.partyiconrep);
            } else if (Objects.equals(party, "I")) {
                mLegislatorImageView.setImageResource(R.drawable.partyiconind);
            } else {
                mLegislatorImageView.setImageResource(R.drawable.partyicondem);
            }
        }
    }

}
