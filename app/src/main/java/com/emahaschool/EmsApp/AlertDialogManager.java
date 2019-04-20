package com.emahaschool.EmsApp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertDialogManager
{

    public void showAlertDialog(Context context, String title, String message, Boolean status)
    {
        AlertDialog alertdialog = new AlertDialog.Builder(context).create();

        alertdialog.setTitle(title);
        alertdialog.setMessage(message);


        if(status != null)
        {
            // Setting alert dialog icon
            alertdialog.setIcon((status) ? R.drawable.success : R.drawable.error);
        }


        // Setting OK Button
        alertdialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });


        // Showing Alert Message
        alertdialog.show();
    }

}
