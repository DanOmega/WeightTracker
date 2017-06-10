package com.daniel.weighttracker;

import android.graphics.Bitmap;

import java.util.Date;


/**
 * Created by Daniel on 5/27/2017.
 */

public class WeightRecord  {

    private long id;
    private Bitmap image;
    private String weight;
    private String fileName;
    private Date date;

    public WeightRecord()
    {
        if ( date == null )
            date = new Date();
    }

    public WeightRecord(long id, Bitmap image, String weight, String fileName, Date date )
    {
        this.id = id;
        this.weight = weight;
        this.image = image;
        this.fileName = fileName;
        this.date = date;
    }

    public long getId(){ return id; }
    public void setId(long id){this.id = id; }

    public Bitmap getImage() { return image; }
    public void setImage(Bitmap image) { this.image = image; }

    public String getWeight(){  return weight; }
    public void setWeight(String weight ){ this.weight = weight; }

    public String getFileName(){ return fileName; }
    public void setFileName(String fileName){this.fileName = fileName; }

    public long getDate(){ return date.getTime(); }
    public void setDate(Date date ){ this.date = date;}

    public String getDateAsString(){ return date.toString(); }

    @Override
    public String toString()
    {
        String date = getDateAsString();
        date = date.substring(0, date.lastIndexOf(":"));
        return getWeight() + " "  + date;
    }









}
