package com.likewater.articleone.adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.likewater.articleone.models.Rep;
import com.likewater.articleone.util.ItemTouchHelperAdapter;
import com.likewater.articleone.util.OnStartDragListener;

public class FirebaseRepListAdapter extends FirebaseRecyclerAdapter<Rep, FirebaseRepViewHolder>
        implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseRepListAdapter(Class<Rep> modelClass, int modelLayout,
                                         Class<FirebaseRepViewHolder> viewHolderClass,
                                         Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void populateViewHolder(final FirebaseRepViewHolder viewHolder, Rep model, int position) {
        viewHolder.bindRep(model);
        viewHolder.mLegislatorImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }
}