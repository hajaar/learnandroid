package com.kartik.diabetesmonitoring;

/**
 * Created by kartikn on 20-09-2015.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "diabetesMonitoring.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_INSULIN_READING = "insulin_reading";
    public static final String COLUMN_INSULIN_READING_ID = "id";
    public final static String COLUMN_INSULIN_READING_NAME = "name";
    public static final String COLUMN_INSULIN_READING_TYPE = "type";
    public static final String COLUMN_INSULIN_TIME_OF_DAY = "time_of_day";
    public static final String COLUMN_INSULIN_READING_QUANTITY = "quantity";
    public static final String COLUMN_INSULIN_READING_INJECTED_DATETIME = "injected_datetime";
    public static final String COLUMN_INSULIN_READING_CREATED_DATETIME = "created_datetime";

    // Database creation sql statement
    private static final String CREATE_TABLE_INSULIN_READINGS = "create table " + TABLE_INSULIN_READING + "(" +
            COLUMN_INSULIN_READING_ID + " integer primary key autoincrement, " +
            COLUMN_INSULIN_READING_NAME + " text not null, " +
            COLUMN_INSULIN_READING_TYPE + " text not null, " +
            COLUMN_INSULIN_READING_QUANTITY + " integer not null, " +
            COLUMN_INSULIN_TIME_OF_DAY + " text not null, " +
            COLUMN_INSULIN_READING_INJECTED_DATETIME + " integer not null, " +
            COLUMN_INSULIN_READING_CREATED_DATETIME + " integer not null " +
            ")";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_INSULIN_READINGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSULIN_READING);
        onCreate(db);
    }

}