package com.daniel.weighttracker;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static com.daniel.weighttracker.LogHelper.Log;

public class MainActivity extends AppCompatActivity
{



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if( WeightEntryService.getContext() == null)
        {
           WeightEntryService.setContext( getApplicationContext() );
        }

        WeightEntryService.saveNewFile( "Test");

        Log("Hello");

        File file = getApplicationContext().getFileStreamPath("Test");

        String test = WeightEntryService.readFile( getApplicationContext().getFileStreamPath("Test") );

        Log(file.toString());
        Log(test);
    }
}
