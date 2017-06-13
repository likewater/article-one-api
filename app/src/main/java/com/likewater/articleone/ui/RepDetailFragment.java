package com.likewater.articleone.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.likewater.articleone.Constants;
import com.likewater.articleone.R;
import com.likewater.articleone.models.Rep;
import com.likewater.articleone.services.ProService;
import okhttp3.Call;
import okhttp3.Callback;

import org.parceler.Parcels;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;

public class RepDetailFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.repNameTextView) TextView mName;
    @Bind(R.id.repRoleTextView) TextView mRole;
    @Bind(R.id.repPartyTextView) TextView mParty;
    @Bind(R.id.repApiUrl) TextView mApiUri;

//    @Bind(R.id.repLastNameTextView) TextView mLastName;
//    @Bind(R.id.repFirstNameTextView) TextView mFirstName;
//    @Bind(R.id.titleTextView) TextView mTitle;
//    @Bind(R.id.stateTextView) TextView mState;
//    @Bind(R.id.districtTextView) TextView mDistrict;
//    @Bind(R.id.partyTextView) TextView mCurrentParty;
//    @Bind(R.id.websiteTextView) TextView mUrl;
//    @Bind(R.id.twitterTextView) TextView mTwitterAccount;
//    @Bind(R.id.facebookTextView) TextView mFacebookAccount;
//    @Bind(R.id.youtubeTextView) TextView mYoutubeAccount;
//    @Bind(R.id.phoneTextView) TextView mPhone;
    @Bind(R.id.saveRepButton) TextView mSaveRepButton;

    private Rep mRep;

    public RepDetailFragment() {
        // Required empty public constructor
    }

    public static RepDetailFragment newInstance(Rep rep) {
        RepDetailFragment repDetailFragment = new RepDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("rep", Parcels.wrap(rep));
        repDetailFragment.setArguments(args);
        return repDetailFragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRep = Parcels.unwrap(getArguments().getParcelable("rep"));

//        String apiUrl = mRep.getApiUri();
//        Log.d("api", mRep.getApiUri());

        //ProService.findRepDetail("https://api.propublica.org/congress/v1/members/K000388.json", new Callback(){

//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//            }
//        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //I need to get this variable - mRep.getApiUri(){}
        //I need to pass it to: findRepDetail

//        String apiUrl = mRep.getApiUri();
//        Log.d("api", mRep.getApiUri());

        View view = inflater.inflate(R.layout.fragment_rep_detail, container, false);
        ButterKnife.bind(this, view);

        mName.setText(mRep.getName());
        mRole.setText(mRep.getRole());
        mParty.setText(mRep.getParty());
        mApiUri.setText(mRep.getApiUri());
        mSaveRepButton.setOnClickListener(this);

        String apiUrl = mRep.getApiUri();
        Log.d("api", mRep.getApiUri());

//        mFirstName.setText(mRepDetail.getFirstName());
//        mLastName.setText(mRepDetail.getLastName());
//        mTitle.setText(mRepDetail.getTitle());
//        mState.setText(mRepDetail.getState());
//        mDistrict.setText(mRepDetail.getDistrict());
//        mCurrentParty.setText(mRepDetail.getCurrentParty());
//        mUrl.setText(mRepDetail.getUrl());
//        mTwitterAccount.setText(mRepDetail.getTwitterAccount());
//        mFacebookAccount.setText(mRepDetail.getFacebookAccount());
//        mYoutubeAccount.setText(mRepDetail.getYoutubeAccount());
//        mPhone.setText(mRepDetail.getPhone());

        return view;
    }

    @Override
    public void onClick(View v) {

        if (v == mSaveRepButton) {
            DatabaseReference restaurantRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_REPS);
            restaurantRef.push().setValue(mRep);
            Log.d("api", mRep.getApiUri());
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }

}
