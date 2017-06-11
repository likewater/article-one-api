package com.likewater.articleone.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.likewater.articleone.R;
import com.likewater.articleone.models.RepDetail;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepDetailFragment extends Fragment {

    @Bind(R.id.repLastNameTextView) TextView mLastName;
    @Bind(R.id.repFirstNameTextView) TextView mFirstName;
    @Bind(R.id.titleTextView) TextView mTitle;
    @Bind(R.id.stateTextView) TextView mState;
    @Bind(R.id.districtTextView) TextView mDistrict;
    @Bind(R.id.partyTextView) TextView mCurrentParty;
    @Bind(R.id.websiteTextView) TextView mUrl;
    @Bind(R.id.twitterTextView) TextView mTwitterAccount;
    @Bind(R.id.facebookTextView) TextView mFacebookAccount;
    @Bind(R.id.youtubeTextView) TextView mYoutubeAccount;
    @Bind(R.id.phoneTextView) TextView mPhone;
    @Bind(R.id.saveRepButton) TextView mSaveRepButton;

    private RepDetail mRepDetail;

    public RepDetailFragment() {
        // Required empty public constructor
    }

    public static RepDetailFragment newInstance(RepDetail repDetail) {
        RepDetailFragment repDetailFragment = new RepDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("rep", Parcels.wrap(repDetail));
        repDetailFragment.setArguments(args);
        return repDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepDetail = Parcels.unwrap(getArguments().getParcelable("rep"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // I need to get this variable - mRep.getApiUri(){}
        //I need to pass it to: findRepDetail

        View view = inflater.inflate(R.layout.fragment_rep_detail, container, false);
        ButterKnife.bind(this, view);

        mFirstName.setText(mRepDetail.getFirstName());
        mLastName.setText(mRepDetail.getLastName());
        mTitle.setText(mRepDetail.getTitle());
        mState.setText(mRepDetail.getState());
        mDistrict.setText(mRepDetail.getDistrict());
        mCurrentParty.setText(mRepDetail.getCurrentParty());
        mUrl.setText(mRepDetail.getUrl());
        mTwitterAccount.setText(mRepDetail.getTwitterAccount());
        mFacebookAccount.setText(mRepDetail.getFacebookAccount());
        mYoutubeAccount.setText(mRepDetail.getYoutubeAccount());
        mPhone.setText(mRepDetail.getPhone());

        return view;
    }

}
