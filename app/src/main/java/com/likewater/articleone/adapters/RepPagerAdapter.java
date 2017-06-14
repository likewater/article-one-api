package com.likewater.articleone.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.likewater.articleone.R;
import com.likewater.articleone.models.Rep;
import com.likewater.articleone.ui.RepDetailFragment;

import java.util.ArrayList;
import java.util.Objects;

public class RepPagerAdapter extends FragmentPagerAdapter{
    private ArrayList<Rep> mRep;

    public RepPagerAdapter(FragmentManager fm, ArrayList<Rep> reps){
        super(fm);
        mRep = reps;
    }

    @Override
    public Fragment getItem(int position){
        return RepDetailFragment.newInstance(mRep.get(position));
    }

    @Override
    public int getCount(){
        return mRep.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return mRep.get(position).getName();
    }



}