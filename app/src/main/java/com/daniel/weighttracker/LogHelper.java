package com.daniel.weighttracker;

/**
 * Created by Daniel on 5/22/2017.
 */

import android.util.Log;

public class LogHelper
{

    private static final String TAG = "WeightTracker::";

    public static void Log(String s)
    {
        Log.i(TAG, s);
    }

    public static void Log( String s, Exception e)
    {
        Log.i(TAG, s);
        e.printStackTrace();
    }


}
