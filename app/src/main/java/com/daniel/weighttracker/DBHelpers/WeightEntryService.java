package com.daniel.weighttracker.DBHelpers;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Parcelable;
import android.widget.Toast;

import com.daniel.weighttracker.WeightRecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Date;

import static com.daniel.weighttracker.LogHelper.Log;

/**
 * Created by Daniel on 5/22/2017.
 */

public class WeightEntryService  {

    private WeightEntryService() {}

    private static Context context;

    public static boolean setContext(Context c)
    {
        if( context == null )
        {
            if( c != null)
            {
                context = c;
                return true;
            }

        }

        return false;

    }

    public static Context getContext()
    {
        return context;
    }

    public static String saveImage(WeightRecord w)
    {

        String fileName = null;

        if( w != null && w.getImage() != null && w.getDateAsString() != null )
        {
            fileName = saveImage(w.getImage(), w.getDateAsString());
            w.setFileName(fileName);
        }


        return fileName;
    }

    private static String saveImage(Bitmap image, String fileName )
    {
        fileName += ".png";
        try( FileOutputStream fos = context.openFileOutput( fileName, Context.MODE_PRIVATE) )
        {
            if( image.compress(Bitmap.CompressFormat.PNG, 100, fos) );
            {
                return fileName;
            }
        }
        catch(FileNotFoundException e )
        {
            Log("FileNotFoundException saving Image: ", e);
        }
        catch(IOException e )
        {
            Log("IO Exception occurred saving Image: ", e);
        }



        return null;
    }

    public static Bitmap getImage(String imageName)
    {
        Bitmap image = null;

        try
        {
            File filepath = context.getFileStreamPath( imageName );
            FileInputStream fis = new FileInputStream( filepath );

            image = BitmapFactory.decodeStream(fis);
        }
        catch(FileNotFoundException e )
        {
            Log("FileNotFoundException getting Image: ", e);
        }
        catch(IOException e )
        {
            Log("IO Exception occurred getting Image: ", e);
        }

        return image;
    }

    public static void deleteImage(WeightRecord w)
    {
        delete(w.getFileName());
    }

    private static void delete(String doomedFile)
    {
        File file = context.getFileStreamPath( doomedFile );

        if(file.exists() )
        {
            file.delete();
            Toast.makeText(context, "Record Deleted", Toast.LENGTH_SHORT).show();
        }
    }






}
