package com.easytechhub.helloeyakub.researchhub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import com.easytechhub.helloeyakub.researchhub.TagHandler.MyTagHandler;

public class ImageSlidingActivity extends AppCompatActivity {

    TextView imageSlidingDetails, appBarTitle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_sliding);

        imageSlidingDetails = (TextView) findViewById(R.id.image_sliding_click_details);
        appBarTitle = (TextView) findViewById(R.id.toolbar_title);

        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        Intent intent = getIntent();
        //storing details about click event
        String output = intent.getStringExtra("value");
        //storing app title
        String receiveAppTitle = intent.getStringExtra("appTitle");
        imageSlidingDetails.setTextSize((float) 14.0);
        imageSlidingDetails.setLineSpacing(5, (float) 1.4);
        //setting up the title
        appBarTitle.setText(receiveAppTitle);
        //setting up the details of event
        imageSlidingDetails.setText(Html.fromHtml(
                output,
                null,
                new MyTagHandler()),
                TextView.BufferType.SPANNABLE);
        //webView.loadDataWithBaseURL(null, output, "text/html", "utf-8", null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
