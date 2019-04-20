package com.emahaschool.EmsApp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class InnerSessionmanagement

{

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "EmahaschoolusercartSession";

    // User name (make variable public to access from outside)
    public static final String KEY_USERCARTID = "usercartsessionid";


    // Constructor
    public InnerSessionmanagement(Context context)
    {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    /**
     * Create login session
     * */
    public void createUsercartSession(String usercartsessionid)
    {
        // Storing name in pref
        editor.putString(KEY_USERCARTID, usercartsessionid);

        // commit changes
        editor.commit();
    }


    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserCartSessionDetails()
    {
        HashMap<String, String> user = new HashMap<String, String>();

        // user name
        user.put(KEY_USERCARTID, pref.getString(KEY_USERCARTID, null));

        // return user
        return user;
    }


    /**
     * Clear session details
     * */
    public void logoutUserCartSessionID() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent i = new Intent(_context, com.emahaschool.EmsApp.MainActivity.class);

        // Staring Login Activity
        _context.startActivity(i);
    }

}

