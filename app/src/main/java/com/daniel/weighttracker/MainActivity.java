package com.daniel.weighttracker;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.daniel.weighttracker.DBHelpers.WeightTrackerDatabase;
import com.daniel.weighttracker.DBHelpers.WeightTrackerService;

import java.lang.reflect.Array;
import java.util.List;

import static com.daniel.weighttracker.DBHelpers.WeightEntryService.setContext;
import static com.daniel.weighttracker.LogHelper.Log;

public class MainActivity extends ListActivity
{
    private WeightTrackerService db;
    public static WeightRecord weights;
    public static WeightRecord currentRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContext(this);
        db = new WeightTrackerService( this );
        db.open();

        List<WeightRecord> records = db.getAllRecords();
        ArrayAdapter<WeightRecord> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, records);
        setListAdapter(adapter);


    }


    public void addNewWeightRecord(View view)
    {
        currentRecord = null;
        Intent intent = new Intent(this, WeightActivity.class);
        startActivity(intent);

    }
    @Override
    protected void onResume()
    {
        db.open();
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        db.close();
        super.onPause();
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        currentRecord = (WeightRecord)getListAdapter().getItem(position);
        Intent intent = new Intent(this, WeightActivity.class);
        startActivity(intent);
        super.onListItemClick(l, v, position, id);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
