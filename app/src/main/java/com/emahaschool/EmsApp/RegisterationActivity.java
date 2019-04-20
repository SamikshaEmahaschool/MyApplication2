package com.emahaschool.EmsApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class RegisterationActivity extends AppCompatActivity {

    AppCompatEditText fname,lname,uname,password,phone,email,address;
    Button spn_phone_type;
    String txtfname,txtlname,txtuname,txtpassword,txtphone,txtemail,txtcustregistrationid,txtaddress;
    EmsSqlserverDBHelper sqlserverDBHelper;


    Random rndnumber = new Random();
    public int rndnumber1 = rndnumber.nextInt(1000);

    Boolean registertionstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        sqlserverDBHelper = new EmsSqlserverDBHelper();

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        uname = findViewById(R.id.uname);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        initToolbar();

    }

    private void initToolbar()
    {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_done) {

            registeruser();
            Toast.makeText(getApplicationContext(), "Registration Succesfull", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void registeruser(){

        txtfname =fname.getText().toString();
        txtlname = lname.getText().toString();
        txtuname = uname.getText().toString();
        txtpassword = password.getText().toString();
        txtemail = email.getText().toString();
        txtphone = phone.getText().toString();
        txtaddress = address.getText().toString();
        txtcustregistrationid = "EMS" + rndnumber1;
        registertionstatus = sqlserverDBHelper.insertintoregistertion(txtfname,txtlname,txtuname,txtpassword,txtemail,txtphone,txtaddress,txtcustregistrationid,"Pending");


        if(registertionstatus)
        {
            Toast.makeText(getApplicationContext(), "Registration Succesfull", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
        }
        else {
            Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
