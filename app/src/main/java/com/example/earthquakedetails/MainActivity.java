package com.example.earthquakedetails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView aTextViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //identifing the buttons
        Button listButton = findViewById(R.id.listButton);
        Button searchButton = findViewById(R.id.searchButton);
        Button exitButton = findViewById(R.id.exitButton);

        //when list button is clicked
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //launch view list activity
                Intent intent = ViewList.makeIntent(MainActivity.this);
                startActivity(intent);
            }
        });

        //when search button is clicked
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //launch search list activity
                Intent intent = SearchList.makeIntent(MainActivity.this);
                startActivity(intent);
            }
        });

        //when exit button is clicked
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //closes app
                MainActivity.this.finish();
            }
        });
    }
}


