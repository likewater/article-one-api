package com.likewater.articleone.ui;

/* Creates the spinners on the main activity */

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.likewater.articleone.R;

public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        Spinner spinner = (Spinner) findViewById(R.id.spinnerHouse);
        //Spinners work even though I never mention R.id.spinnerState
        spinner.setOnItemSelectedListener(this);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        //Toast or message to please select an item goes here.
    }
}
