package com.daniel.weighttracker;

/**
 * Created by Daniel on 6/7/2017.
 */

public class Comment
{
    private long id;
    private String comment;

    public long getId(){ return id;}
    public void setId(long v ){ id = v; }

    public String getComment(){ return comment; }
    public void setComment(String text){ comment = text; }

    @Override
    public String toString()
    {
        return comment;
    }


}
