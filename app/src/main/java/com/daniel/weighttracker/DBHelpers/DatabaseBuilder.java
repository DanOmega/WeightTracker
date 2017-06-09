package com.daniel.weighttracker.DBHelpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.daniel.weighttracker.LogHelper.Log;

/**
 * Created by Daniel on 6/7/2017.
 */

public class DatabaseBuilder extends SQLiteOpenHelper
{

    public static final String COMMENTS_TABLE = "comments";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_COMMENT = "comment";

    private static final String DATABASE_NAME = "test.db";
    private static final int DATABASE_VERSION = 1;

    //CREATE TABLE comments ( _id integer primary key autoincrement, comment text not null );
    private static final String CREATE_DATABASE =
            new StringBuilder().append("CREATE TABLE ")
                .append( COMMENTS_TABLE )
                .append( " ( ")
                .append( COLUMN_ID )
                .append( " integer primary key autoincrement, " )
                .append( COLUMN_COMMENT )
                .append( " text not null); " ).toString();

    public DatabaseBuilder(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        Log(CREATE_DATABASE);
        database.execSQL( CREATE_DATABASE );
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
    {
        Log("Database Upgrade");

        database.execSQL( " DROP TABLE IF EXISTS " + COMMENTS_TABLE);
        onCreate(database);

    }


}
