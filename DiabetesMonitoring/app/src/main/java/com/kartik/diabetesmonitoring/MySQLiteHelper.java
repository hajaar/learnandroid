package com.kartik.diabetesmonitoring;

/**
 * Created by kartikn on 20-09-2015.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_INSULIN_READING = "insulin_reading";
    public static final String COLUMN_INSULIN_READING_ID = "id";
    public final static String COLUMN_INSULIN_READING_NAME = "name";
    public static final String COLUMN_INSULIN_READING_TYPE = "type";
    public static final String COLUMN_INSULIN_TIME_OF_DAY = "time_of_day";
    public static final String COLUMN_INSULIN_READING_QUANTITY = "quantity";
    public static final String COLUMN_INSULIN_READING_INJECTED_DATETIME = "injected_datetime";
    public static final String COLUMN_INSULIN_READING_CREATED_DATETIME = "created_datetime";
    public static final String TABLE_MEDICINE_MASTER = "insulin_master";
    public static final String COLUMN_MEDICINE_MASTER_ID = "id";
    public final static String COLUMN_MEDICINE_MASTER_INSULIN = "is_insulin"; //INSULIN OR TABLET
    public final static String COLUMN_MEDICINE_MASTER_NAME = "name"; //LANTUS or TRAJENTA
    public static final String COLUMN_MEDICINE_MASTER_PURPOSE = "purpose"; //LONG or SHORT or DIABETES or CHOLESTEROL
    public static final String COLUMN_MEDICINE_MASTER_MORNING = "is_morning"; //Y
    public static final String COLUMN_MEDICINE_MASTER_NOON = "is_noon"; //N
    public static final String COLUMN_MEDICINE_MASTER_EVENING = "is_evening"; //Y
    public static final String COLUMN_MEDICINE_MASTER_BEFORE_FOOD = "is_before_food"; //Y
    public static final String COLUMN_MEDICINE_MASTER_QUANTITY_TYPE = "quantity_type";//FIXED or VARIABLE
    public static final String COLUMN_MEDICINE_MASTER_QUANTITY = "quantity";//FIXED INSULIN or TABLETS COULD BE 0.5 or 1. VARIABLE INSULIN WILL BE NULL
    private static final String DATABASE_NAME = "diabetesMonitoring.db";
    private static final int DATABASE_VERSION = 1;
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

    private static final String CREATE_TABLE_MEDICINE_MASTER = "create table " + TABLE_MEDICINE_MASTER + "(" +
            COLUMN_MEDICINE_MASTER_ID + " integer primary key autoincrement, " +
            COLUMN_MEDICINE_MASTER_INSULIN + " text not null, " +
            COLUMN_MEDICINE_MASTER_NAME + " text not null, " +
            COLUMN_MEDICINE_MASTER_PURPOSE + " text , " +
            COLUMN_MEDICINE_MASTER_MORNING + " integer not null, " +
            COLUMN_MEDICINE_MASTER_NOON + " integer not null, " +
            COLUMN_MEDICINE_MASTER_EVENING + " integer not null, " +
            COLUMN_MEDICINE_MASTER_BEFORE_FOOD + " integer not null, " +
            COLUMN_MEDICINE_MASTER_QUANTITY_TYPE + " integer not null, " +
            COLUMN_MEDICINE_MASTER_QUANTITY + " numeric not null " +
            ")";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_INSULIN_READINGS);
        database.execSQL(CREATE_TABLE_MEDICINE_MASTER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSULIN_READING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICINE_MASTER);
        onCreate(db);
    }

}