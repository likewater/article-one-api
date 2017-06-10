package com.likewater.articleone.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.graphics.Typeface;

import com.likewater.articleone.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.findRepsButton) Button mFindRepsButton;
    //@Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.articleOneTextView) TextView mArticleOneTextView;
    @Bind(R.id.articleOneTextView2) TextView mArticleOneTextView2;
    @Bind(R.id.findAboutPageButton) Button mFindAboutPageButton;
    @Bind(R.id.spinnerHouse) Spinner mSpinnerHouse;
    @Bind(R.id.spinnerState) Spinner mSpinnerState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Typeface openSans = Typeface.createFromAsset(getAssets(),
                "fonts/opensans-regular.ttf");
        mArticleOneTextView.setTypeface(openSans);
        mArticleOneTextView2.setTypeface(openSans);
        mFindRepsButton.setOnClickListener(this);
        mFindAboutPageButton.setOnClickListener(this);

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
    public void onClick(View v){
        if(v == mFindRepsButton) {
            String congress = mSpinnerHouse.getSelectedItem().toString();
            String state = mSpinnerState.getSelectedItem().toString();
            Intent intent = new Intent(MainActivity.this, RepListActivity.class);
            intent.putExtra("congress", congress);
            intent.putExtra("state", state);
            startActivity(intent);
        }


        if(v == mFindAboutPageButton){
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }
    }
}
