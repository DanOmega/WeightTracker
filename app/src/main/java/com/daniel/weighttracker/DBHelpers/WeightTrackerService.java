package com.daniel.weighttracker.DBHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.daniel.weighttracker.WeightRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.daniel.weighttracker.DBHelpers.WeightEntryService.writeWeightRecord;
import static com.daniel.weighttracker.LogHelper.Log;

/**
 * Created by Daniel on 6/8/2017.
 */

public class WeightTrackerService
{

    private SQLiteDatabase db;
    private WeightTrackerDatabase dbBuilder;
    private String[] allColumns =
            {
                    dbBuilder.ID_COLUMN,
                    dbBuilder.WEIGHT_COLUMN,
                    dbBuilder.FILE_COLUMN,
                    dbBuilder.DATE_COLUMN
            };

    public WeightTrackerService ( Context context )
    {
        dbBuilder = new WeightTrackerDatabase(context);
    }

    public void open()
    {
        try {
            db = dbBuilder.getWritableDatabase();
        }
        catch ( SQLException e )
        {
            Log("Error Opening Database: ", e);
            if( db != null )
                db.close();

            db = null;
        }
    }

    public void close()
    {
        try
        {
            dbBuilder.close();
        }
        catch( Exception e )
        {
            Log("Exception Occurred closing Database: ", e);
        }
    }

    public void saveNewWeightRecord( WeightRecord w )
    {
        if( w == null )
        {
            Toast.makeText(null, "Can't save due to null WeightRecord", Toast.LENGTH_SHORT ).show();
            return;
        }
       if( w.getImage() == null )
       {
           Toast.makeText(null, "Missing Image", Toast.LENGTH_SHORT).show();
           return;
       }
       if( w.getWeight() == null )
       {
           Toast.makeText(null, "Missing Weight Value", Toast.LENGTH_SHORT).show();
           return;
       }
       if( w.getDate() == 0 )
       {
           w.setDate(new Date());
       }

        ContentValues values = new ContentValues();
        values.put( dbBuilder.WEIGHT_COLUMN, w.getWeight() );
        String fileName = writeWeightRecord(w);
        if( fileName == null )
        {
            return;
        }
        values.put( dbBuilder.FILE_COLUMN, fileName);
        values.put( dbBuilder.DATE_COLUMN, w.getDate() );

        try
        {
            long insertId = db.insert( dbBuilder.WEIGHT_TRACKER_TABLE, null, values);

            if( insertId > 0 )
            {
                Toast.makeText(null, "Record Saved", Toast.LENGTH_SHORT ).show();
            }
            else
            {
                Toast.makeText(null, "Record was not saved", Toast.LENGTH_SHORT ).show();
            }
        }
        catch ( Exception e )
        {
            Log("Error inserting Weight Record: ", e);
        }
    }

    public void deleteWeightRecord( WeightRecord w)
    {
        long id = w.getId();

        if( id == 0 )
        {
            Toast.makeText(null, "Can't delete this Weight Record", Toast.LENGTH_SHORT).show();
            return;
        }

        Log("Deleting Weight Record with ID: " + id);
        try
        {
             int result =
                     db.delete(dbBuilder.WEIGHT_TRACKER_TABLE, dbBuilder.ID_COLUMN + " = " + id, null );

            if( result == 1 )
            {
                Toast.makeText(null, "Record Deleted", Toast.LENGTH_SHORT ).show();
            }
        }
        catch(Exception e )
        {
            Log( "Error Deleting: ", e );
        }

    }

    public WeightRecord getWeightRecord( long id )
    {
        WeightRecord w = null;
        if( id <= 0 )
        {
            Toast.makeText(null, "Can't fetch Record", Toast.LENGTH_SHORT ).show();
        }

        try(
            Cursor cursor =
                db.query(dbBuilder.WEIGHT_TRACKER_TABLE, allColumns, dbBuilder.ID_COLUMN + " = " + id, null, null, null, null);
        )
        {
            if( cursor.moveToFirst() )
            {
                w = cursorToWeightRecord(cursor);
            }
            else {
                Toast.makeText(null, "Couldn't find Record", Toast.LENGTH_SHORT).show();
            }

        }
        catch (SQLException e)
        {
            Log("Database Error: ", e);
            return null;
        }
        return w;
    }

    //FIXME: Should only get the weight value and the date probably to expensive to get the entire weight object
    public List<WeightRecord> getAllRecords()
    {
        List<WeightRecord> records = new ArrayList<WeightRecord>();

        try(  Cursor cursor = db.query(dbBuilder.WEIGHT_TRACKER_TABLE, allColumns, null, null, null, null, null) )
        {
            cursor.moveToFirst();

            while( ! cursor.isAfterLast() )
            {
                WeightRecord w = cursorToWeightRecord(cursor);
                records.add(w);
                cursor.moveToNext();
            }
        }
        catch (SQLException e )
        {
            Log("Database Error:" );
            Log( e.toString() );
        }

        return records;
    }

    private WeightRecord cursorToWeightRecord( Cursor cursor)
    {
        long id = cursor.getLong(0);
        String weightValue = cursor.getString(1);
        String fileName = cursor.getString(2);
        Date date = new Date();
        date.setTime(cursor.getLong(3));

        return new WeightRecord(id, WeightEntryService.getImage(fileName),weightValue, fileName, date);
    }






}
