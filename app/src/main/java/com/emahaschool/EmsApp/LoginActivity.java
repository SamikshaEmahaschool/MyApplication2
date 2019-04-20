package com.emahaschool.EmsApp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView newreg;
    SessionManagement session;
    EmsSqlserverDBHelper sqldbhelper;

    EditText user, input_password;
    Button btnsub;
    String txtpassword, forgotpassword, txtinput_username, txtaddress, txtemail, txtstatus, txtcontact, txtcustomerid;
    Boolean loginstatus;


    AlertDialogManager alert = new AlertDialogManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        newreg = findViewById(R.id.newreg);
        newreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterationActivity.class);
                startActivity(i);
            }
        });

        session = new SessionManagement(this);
        sqldbhelper = new EmsSqlserverDBHelper();


        user = findViewById(R.id.user);
        input_password = findViewById(R.id.input_password);

        btnsub = findViewById(R.id.btnsub);
        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTaskRunner(getApplicationContext()).execute();
            }
        });

    }

    public class AsyncTaskRunner extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog;
        private Context context;

        public AsyncTaskRunner(Context context) {
            this.context = context;
        }


        @Override

        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(LoginActivity.this, "Login", "wait for a few seconds");
        }

        @Override
        protected String doInBackground(String... strings) {
            verifyUser();
            return null;
        }

        //@Override
        // protected String doInBackground (String, String){
        // verifyUser();
        //  return null;

        @Override
        protected void onPostExecute(String result) {
            if (loginstatus) {
                txtinput_username = sqldbhelper.getUserName();
                txtaddress = sqldbhelper.getFulladdress();
                txtemail = sqldbhelper.getEmail();
                txtcontact = sqldbhelper.getContactno();
                txtcustomerid = sqldbhelper.getCustomerid();
                session.createLoginSession(txtinput_username, txtaddress, txtemail, txtcontact, txtcustomerid);

                progressDialog.dismiss();

                if (loginstatus) {
                    txtinput_username = sqldbhelper.getUserName();
                    txtaddress = sqldbhelper.getFulladdress();
                    txtemail = sqldbhelper.getEmail();
                    txtcontact = sqldbhelper.getContactno();
                    txtcustomerid = sqldbhelper.getCustomerid();

                    Toast.makeText(getApplicationContext(), "Login Succesfull", Toast.LENGTH_SHORT).show();
                    session.createLoginSession(txtinput_username, txtemail, txtcustomerid);
                    Intent i = new Intent(getApplicationContext(), com.emahaschool.EmsApp.MainActivity.class);
                    startActivity(i);
                } else {
                    // username / password doesn't match
                    alert.showAlertDialog(LoginActivity.this, "Login Failed", "Username/Password is incorrect", false);
                    progressDialog.dismiss();
                }
            }

        }

        public void verifyUser() {
            txtinput_username = user.getText().toString().trim();
            txtpassword = input_password.getText().toString().trim();
            txtstatus = "approve";
            loginstatus = (Boolean) sqldbhelper.checkUser(txtstatus, txtinput_username, txtpassword);
        }
    }

}
