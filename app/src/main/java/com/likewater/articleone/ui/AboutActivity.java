package com.likewater.articleone.ui;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.likewater.articleone.R;
import butterknife.Bind;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "url";
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

   @Override
    public void onClick(View v) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.propublica.org/datastore/api/propublica-congress-api"));
            startActivity(webIntent);
    }
}