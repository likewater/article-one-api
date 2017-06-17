package com.likewater.articleone.adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.likewater.articleone.R;
import com.likewater.articleone.models.Rep;
import com.likewater.articleone.util.ItemTouchHelperViewHolder;
import java.util.Objects;

public class FirebaseRepViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    View mView;
    Context mContext;
    public ImageView mLegislatorImageView;

    public FirebaseRepViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
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
    }
}
