package com.likewater.articleone.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.likewater.articleone.R;
import com.likewater.articleone.models.Rep;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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

    @Override
    public void onBindViewHolder(RepListAdapter.RepViewHolder holder, int position) {
        holder.bindRep(mReps.get(position));
    }

    @Override
    public int getItemCount() {
        return mReps.size();
    }

    public class RepViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.legislatorImageView) ImageView mLegislatorImageView;
        @Bind(R.id.legislatorNameTextView) TextView mLegislatorNameTextView;
        @Bind(R.id.roleNameTextView) TextView mRoleNameTextView;
        @Bind(R.id.partyTextView) TextView mPartyTextView;

        private Context mContext;

        public RepViewHolder(View itemView) {
            super(itemView);
            try {
                ButterKnife.bind(this, itemView);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mContext = itemView.getContext();
        }

        public void bindRep(Rep rep) {
            //Picasso.with(mContext).load(rep.getImageUrl()).into(mLegislatorImageView);
            mLegislatorNameTextView.setText(rep.getName());
            mRoleNameTextView.setText(rep.getRole());
            mPartyTextView.setText(rep.getParty());
        }
    }

}
