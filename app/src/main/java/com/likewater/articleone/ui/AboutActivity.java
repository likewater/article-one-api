package com.likewater.articleone.ui;
import android.net.Uri;
import android.os.Bundle;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;


        import com.likewater.articleone.R;
        import butterknife.Bind;
        import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity {
    //@Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    public String phoneNumber = "2125551212";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        //mWebsiteLabel.setOnClickListener(this);
        dialPhoneNumber(phoneNumber);

    }
    //Intent intent = getIntent();

//    Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.propublica.org/datastore/api/propublica-congress-api"));
//    startActivity(webIntent);






    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}