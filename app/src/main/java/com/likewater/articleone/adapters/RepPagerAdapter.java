package com.likewater.articleone.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.likewater.articleone.models.RepDetail;
import com.likewater.articleone.ui.RepDetailFragment;

import java.util.ArrayList;

public class RepPagerAdapter extends FragmentPagerAdapter{
    private ArrayList<RepDetail> mRepDetail;

    public RepPagerAdapter(FragmentManager fm, ArrayList<RepDetail> reps){
        super(fm);
        mRepDetail = reps;
    }

    @Override
    public Fragment getItem(int position){
        return RepDetailFragment.newInstance(mRepDetail.get(position));
    }

    @Override
    public int getCount(){
        return mRepDetail.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return mRepDetail.get(position).getLastName();
    }


}
