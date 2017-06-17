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

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepDetailFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.repNameTextView) TextView mName;
    @Bind(R.id.repRoleTextView) TextView mRole;
    @Bind(R.id.repPartyTextView) TextView mParty;
    @Bind(R.id.repApiUrl) TextView mApiUri;
    @Bind(R.id.districtTextView) TextView mDistrict;
    @Bind(R.id.saveRepButton) TextView mSaveRepButton;

    private Rep mRep;

    public RepDetailFragment() {
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
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rep_detail, container, false);
        ButterKnife.bind(this, view);

        mName.setText(mRep.getName());
        mRole.setText(mRep.getRole());
        mParty.setText(mRep.getParty());
        mApiUri.setText(mRep.getApiUri());
        mSaveRepButton.setOnClickListener(this);

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