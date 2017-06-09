package com.daniel.weighttracker.DBHelpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.daniel.weighttracker.LogHelper.Log;

/**
 * Created by Daniel on 6/8/2017.
 */

public class WeightTrackerDatabase extends SQLiteOpenHelper
{
    public static final String WEIGHT_TRACKER_TABLE = "weightRecords";
    public static final String ID_COLUMN = "weight_id";
    public static final String WEIGHT_COLUMN = "weight_tx";
    public static final String FILE_COLUMN = "file_path_tx";
    public static final String DATE_COLUMN = "date_tx";

    private static final String DATABASE_NAME = "weightRecords.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_DATABASE =
            new StringBuilder().append("CREATE TABLE " )
                .append(WEIGHT_TRACKER_TABLE)
                .append( " ( " )
                .append( ID_COLUMN )
                .append( " integer primary key autoincrement, " )
                .append( WEIGHT_COLUMN )
                .append( " text not null, " )
                .append( FILE_COLUMN )
                .append( " text not null, ")
                .append( DATE_COLUMN )
                .append( " integer not null ); " ).toString();

    public WeightTrackerDatabase( Context context )
    {
        super (context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log("Creating Database: " + CREATE_DATABASE);
        db.execSQL( CREATE_DATABASE );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log("Database Upgrading from version: " + oldVersion + " To: " + newVersion);
        db.execSQL( " DROP TABLE IF EXISTS " + WEIGHT_TRACKER_TABLE );
        onCreate(db);
    }
}
