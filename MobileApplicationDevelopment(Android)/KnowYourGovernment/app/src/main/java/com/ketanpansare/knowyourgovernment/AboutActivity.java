package com.ketanpansare.knowyourgovernment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

/**
 * Created by Ketan on 3/20/2020.
 */

public class AboutActivity extends AppCompatActivity {

    //SET PARENT ACTIVITY IN THE MANIFEST

    private static final String TAG = "About_Activity";

    private TextView titleView;
    private TextView copyrightView;
    private TextView versionView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        titleView = findViewById(R.id.titleID);
        copyrightView = findViewById(R.id.copyrightID);
        versionView = findViewById(R.id.versionID);
        textView = findViewById(R.id.textView);
        textView.setText(Html.fromHtml(" <a href=\"https://developers.google.com/civic-information\">Google Civic Information API</a>"));
        textView.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());

        titleView.setText("Know Your Government");
        copyrightView.setText("© 2020, Shweta Metkar");
        versionView.setText("Version 1.0");
    }
}
