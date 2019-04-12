package com.example.earthquakedetails;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SearchList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);
    }

    //makes new intent to switch activity
    public static Intent makeIntent(Context context) {
        return new Intent(context, SearchList.class);
    }
}
