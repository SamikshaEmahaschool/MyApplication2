package com.emahaschool.EmsApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class NewRatingBar extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_rating_bar);

        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), com.emahaschool.EmsApp.MainActivity.class);
                startActivity(i);

                Toast.makeText(getApplicationContext(), "Thank you for Rating Us", Toast.LENGTH_SHORT).show();

            }
        });

    }

    void press(View v)
    {
        RatingBar rt = (RatingBar) findViewById(R.id.ratingBar);
    }
}
