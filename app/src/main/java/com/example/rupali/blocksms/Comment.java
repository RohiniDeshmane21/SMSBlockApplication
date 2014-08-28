package com.example.rupali.blocksms;

/**
 * Created by rupali on 20-08-2014.
 */
public class Comment {

    private long id;
    private String comment;
    public boolean isSelected = false;

    public long getId()
    {
        return  id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String  getComment()
    {
        return  comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    //will be used by array adapter in the listview
 public String toString()
    {
        return  comment;
    }


}
