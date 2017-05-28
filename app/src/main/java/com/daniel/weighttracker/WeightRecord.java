package com.daniel.weighttracker;

import android.graphics.Bitmap;


/**
 * Created by Daniel on 5/27/2017.
 */

public class WeightRecord  {

    private Bitmap image;
    private String weight;
    private String fileName;

    public WeightRecord(){}

    public WeightRecord(Bitmap image, String weight)
    {
        this.image = image;
        this.weight = weight;
    }

    public Bitmap getImage() { return image; }

    public void setImage(Bitmap image) { this.image = image; }

    public String getWeight(){  return weight; }

    public void setWeight(String weight ){ this.weight = weight; }

    public void setFileName()
    {

    }









}
