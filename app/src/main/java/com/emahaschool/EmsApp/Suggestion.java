package com.emahaschool.EmsApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Suggestion extends AppCompatActivity {

    android.support.v7.widget.AppCompatButton sub;

    EditText name,email,description;

    EmsSqlserverDBHelper sqlserverDBHelper;

    String txtsname,txtemail,des;

    Boolean suggestionstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);

        sqlserverDBHelper = new EmsSqlserverDBHelper();

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        description = findViewById(R.id.description);

        sub = findViewById(R.id.sub);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setSuggestion();

                Toast.makeText(getApplicationContext(), "Your Suggestions Are Submitted", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), com.emahaschool.EmsApp.MainActivity.class);
                startActivity(i);
            }
        });

        initToolbar();

    }

    public void initToolbar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.sub) {

            setSuggestion();
            Toast.makeText(getApplicationContext(), "Your Suggestion are Submitted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    public void setSuggestion()
    {
        txtsname =name.getText().toString();
        txtemail = email.getText().toString();
        des = description.getText().toString();
        suggestionstatus = sqlserverDBHelper.insertintosuggestion(txtsname,txtemail,des);

        if (suggestionstatus)
        {
            Toast.makeText(getApplicationContext(), "Your Suggestion are Submitted", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), com.emahaschool.EmsApp.MainActivity.class);
            startActivity(i);
        }
        else {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }
}
