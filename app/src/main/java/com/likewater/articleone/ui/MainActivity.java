package com.likewater.articleone.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.graphics.Typeface;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.likewater.articleone.Constants;
import com.likewater.articleone.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private DatabaseReference mSearchedStateReference;
    private ValueEventListener mSearchedStateReferenceListener;

    @Bind(R.id.findRepsButton) Button mFindRepsButton;
    @Bind(R.id.articleOneTextView) TextView mArticleOneTextView;
    @Bind(R.id.articleOneTextView2) TextView mArticleOneTextView2;
    @Bind(R.id.findAboutPageButton) Button mFindAboutPageButton;
    @Bind(R.id.spinnerHouse) Spinner mSpinnerHouse;
    @Bind(R.id.spinnerState) Spinner mSpinnerState;
    @Bind(R.id.savedRepsButton) Button mSavedRepsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedStateReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_STATE);

        mSearchedStateReferenceListener = mSearchedStateReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
                    String state = locationSnapshot.getValue().toString();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

      mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
      mEditor = mSharedPreferences.edit();

        Typeface openSans = Typeface.createFromAsset(getAssets(),
                "fonts/opensans-regular.ttf");
        mArticleOneTextView.setTypeface(openSans);
        mArticleOneTextView2.setTypeface(openSans);
        mFindRepsButton.setOnClickListener(this);
        mFindAboutPageButton.setOnClickListener(this);
        mSavedRepsButton.setOnClickListener(this);

        Spinner spinnerHouse = (Spinner) findViewById(R.id.spinnerHouse);
        ArrayAdapter<CharSequence> adapterOne = ArrayAdapter.createFromResource(this,
                R.array.congress_array, android.R.layout.simple_spinner_item);
        adapterOne.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHouse.setAdapter(adapterOne);

        Spinner spinnerState = (Spinner) findViewById(R.id.spinnerState);
        ArrayAdapter<CharSequence> adapterTwo = ArrayAdapter.createFromResource(this,
                R.array.states_array, android.R.layout.simple_spinner_item);
        adapterTwo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerState.setAdapter(adapterTwo);
    }

    @Override
    public void onClick(View v) {
        if(v == mFindRepsButton) {
            String congress = mSpinnerHouse.getSelectedItem().toString();
            String state = mSpinnerState.getSelectedItem().toString();
            addToSharedPreferences(congress, state);
            Intent intent = new Intent(MainActivity.this, RepListActivity.class);
            intent.putExtra("congress", congress);
            intent.putExtra("state", state);
            startActivity(intent);
        }

        if (v == mSavedRepsButton) {
            Intent intent = new Intent(MainActivity.this, SavedRepListActivity.class);
            startActivity(intent);
        }

        if(v == mFindAboutPageButton){
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void addToSharedPreferences(String congress, String state) {
        mEditor.putString(Constants.PREFERENCES_CONGRESS_KEY, congress).apply();
        mEditor.putString(Constants.PREFERENCES_STATE_KEY, state).apply();
    }
}
