package com.daniel.weighttracker.DBHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;

import com.daniel.weighttracker.Comment;

import java.util.ArrayList;
import java.util.List;

import static com.daniel.weighttracker.LogHelper.Log;

/**
 * Created by Daniel on 6/7/2017.
 */

public class DatabaseService
{
    private SQLiteDatabase database;
    private DatabaseBuilder dbHelper;
    private String[] columns =
            {
                    DatabaseBuilder.COLUMN_ID,
                    DatabaseBuilder.COLUMN_COMMENT
            };

    public DatabaseService ( Context context )
    {
        dbHelper = new DatabaseBuilder(context);
    }

    public void open() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        dbHelper.close();
    }

    public Comment createComment(String comment )
    {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLUMN_COMMENT, comment);

        long insertId = database.insert(dbHelper.COMMENTS_TABLE, null, values);

        Cursor cursor = database.query(dbHelper.COMMENTS_TABLE,
                columns, dbHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();

        Comment newComment = cursorToComment(cursor);

        cursor.close();

        return newComment;
    }

    public void deleteComment ( Comment comment )
    {
        long id = comment.getId();

        Log("Deleting comment ID: " + id );
        int result = database.delete(dbHelper.COMMENTS_TABLE, dbHelper.COLUMN_ID + " = " + id, null );
        Log(result + " rows deleted");
    }

    public List<Comment> getAllComments()
    {
        List<Comment> comments = new ArrayList<Comment>();

        try(  Cursor cursor = database.query(dbHelper.COMMENTS_TABLE, columns, null, null, null, null, null) )
        {
            cursor.moveToFirst();

            while( ! cursor.isAfterLast() )
            {
                Comment comment = cursorToComment(cursor);
                comments.add(comment);
                cursor.moveToNext();
            }
        }
        catch (SQLException e )
        {
            Log("Database Error:" );
            Log( e.toString() );
        }

        return comments;

    }

    private Comment cursorToComment(Cursor cursor) {
        Comment comment = new Comment();
        comment.setId(cursor.getLong(0));
        comment.setComment(cursor.getString(1));
        return comment;
    }

}
