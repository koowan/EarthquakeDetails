package com.example.earthquakedetails;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ViewListItem extends AppCompatActivity {

    private static final String TAG = "ViewListItem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_item);

        //get intent that was passed from the viewlist activity and displays selected item
        getPassedIntent();
    }

    //get data from the intent passed from viewlist activity
    private void getPassedIntent(){
        //checks if the intent has extras
        if (getIntent().hasExtra("Title") &&
            getIntent().hasExtra("Description") &&
            getIntent().hasExtra("Link") &&
            getIntent().hasExtra("pubDate") &&
            getIntent().hasExtra("Category") &&
            getIntent().hasExtra("GeoLat") &&
            getIntent().hasExtra("GeoLong")){

            Log.d(TAG, "found extra intent");

            //sets extras to the variables
            String title = getIntent().getStringExtra("Title");
            String description = getIntent().getStringExtra("Description");
            String link = getIntent().getStringExtra("Link");
            String pubDate = getIntent().getStringExtra("pubDate");
            String category = getIntent().getStringExtra("Category");
            String geoLat = getIntent().getStringExtra("GeoLat");
            String geoLong = getIntent().getStringExtra("GeoLong");


            //set the data on the textviews
            setTitle(title);
            setDescription(description);
            setLink(link);
            setPubDate(pubDate);
            setCategory(category);
            setGeoLat(geoLat);
            setGeoLong(geoLong);
        }
        else {
            Log.d(TAG, "no intent found");
        }
    }

    //sets the textview to the data received
    public void setTitle(String title){
        TextView textView = findViewById(R.id.titleText);
        textView.setText(title);
    }
    public void setDescription(String description){
        TextView textView = findViewById(R.id.descriptionText);
        textView.setText(description);
    }
    public void setPubDate(String pubDate){
        TextView textView = findViewById(R.id.pubDateHeader);
        textView.setText(pubDate);
    }
    public void setLink(String link){
        TextView textView = findViewById(R.id.linkText);
        textView.setText(link);
    }
    public void setCategory(String category){
        TextView textView = findViewById(R.id.categoryText);
        textView.setText(category);
    }
    public void setGeoLat(String geoLat){
        TextView textView = findViewById(R.id.geoLatText);
        textView.setText(geoLat);
    }
    public void setGeoLong(String geoLong){
        TextView textView = findViewById(R.id.geoLongText);
        textView.setText(geoLong);
    }



}
