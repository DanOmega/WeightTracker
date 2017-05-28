package com.daniel.weighttracker;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import static com.daniel.weighttracker.LogHelper.Log;

public class MainActivity extends AppCompatActivity
{

    public static WeightRecord weights;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log("Record new weight");

    }

    public void recordNewWeight(View view)
    {

        Log("Record new weight");
        Intent intent = new Intent( this, WeightActivity.class);

        startActivity(intent);

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
