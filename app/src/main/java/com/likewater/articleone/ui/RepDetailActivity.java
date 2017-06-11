package com.likewater.articleone.ui;

import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import org.parceler.Parcels;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.likewater.articleone.R;
import com.likewater.articleone.models.Rep;
import com.likewater.articleone.models.RepDetail;
import com.likewater.articleone.adapters.RepPagerAdapter;

public class RepDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private RepPagerAdapter adapterViewPager;
    ArrayList<Rep> mRep = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rep_detail);
        ButterKnife.bind(this);

        mRep = Parcels.unwrap(getIntent().getParcelableExtra("reps"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new RepPagerAdapter(getSupportFragmentManager(), mRep);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
