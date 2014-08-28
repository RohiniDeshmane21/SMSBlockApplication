package com.example.rupali.blocksms;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by rupali on 20-08-2014.
 */
public class MySQLiteHelper extends SQLiteOpenHelper{

    public static final String TABLE_NAME = "numberList";
    public static final String COLUMN_ID =  "id";
    public static final String COLUMN_NAME = "numbers";

    private static final String DATABASE_NAME = "numberLists.db";
    private static final int DATABASE_VERSION = 1;

    //Database create sql statement
    private static final String DATABASE_CREATE = "create table "+ TABLE_NAME + "("+ COLUMN_ID + " integer primary key autoincrement, "+ COLUMN_NAME + " text not null);";

    public MySQLiteHelper(Context context)
    {
         super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
            database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
            Log.w(MySQLiteHelper.class.getName(),"Upgrading database from version" + oldversion +" to " + newversion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);

            onCreate(db);
    }

}
