package com.likewater.articleone.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MyRepsArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mReps;
    private String[] mTypes;

    public MyRepsArrayAdapter(Context mContext, int resource, String[] mReps, String[] mTypes){
        super(mContext, resource);
        this.mContext = mContext;
        this.mReps = mReps;
        this.mTypes = mTypes;
    }

    @Override
    public Object getItem(int position){
        String rep = mReps[position];
        String type  = mTypes[position];
        return String.format("%s \nYour %s", rep, type);
    }

    @Override
    public int getCount(){
        return mReps.length;
    }
}
