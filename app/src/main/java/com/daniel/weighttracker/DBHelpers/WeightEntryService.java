package com.daniel.weighttracker.DBHelpers;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.daniel.weighttracker.WeightRecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

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
            context = c;
            return true;
        }

        return false;

    }

    public static Context getContext()
    {
        return context;
    }

    public static boolean saveNewFile(String filename)
    {
        File file = new File( context.getFilesDir()  , filename);

        writeFileContents(file, "Hello There World");

        return true;
    }

    //Returns the FilePath of the newly written image
    public static String writeWeightRecord(WeightRecord record)
    {

        return "";
    }

    public static String writeWeightRecord(Bitmap image)
    {

        return "";
    }

    public static WeightRecord getWeightRecord()
    {


        return new WeightRecord();

    }
    public static Bitmap getImage(String filename)
    {
        return true;
    }

    public static boolean updateFile(String filename)
    {
       return true;
    }

    public static String readFile( File file )
    {
        String contents = "";
        try ( FileInputStream inputStream = new FileInputStream( new File(file.toString()) ) )
        {
            contents = getFileContents( inputStream );
        }
        catch( IOException e )
        {}

        return contents;

    }

    private static String getFileContents(InputStream input)
    {
        StringBuilder string = new StringBuilder();

        try ( BufferedReader reader = new BufferedReader( new InputStreamReader( input )) )
        {
            String line = null;
            while ( (line = reader.readLine()) != null )
            {
                string.append( line ).append("\n");
            }
        }
        catch (IOException e)
        {

        }

        return string.toString();

    }

    private static String writeFileContents(File file, String data)
    {
        try (FileOutputStream output = context.openFileOutput(file.getName(), Context.MODE_PRIVATE))
        {
            output.write(data.getBytes());
        }
        catch(IOException e)
        {

        }

        return "";
    }



}
