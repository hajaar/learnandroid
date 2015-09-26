package com.kartik.diabetesmonitoring;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kartikn on 20-09-2015.
 */
public class InsulinReadingDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_INSULIN_READING_ID,
            MySQLiteHelper.COLUMN_INSULIN_READING_NAME,
            MySQLiteHelper.COLUMN_INSULIN_READING_TYPE,
            MySQLiteHelper.COLUMN_INSULIN_TIME_OF_DAY,
            MySQLiteHelper.COLUMN_INSULIN_READING_QUANTITY,
            MySQLiteHelper.COLUMN_INSULIN_READING_INJECTED_DATETIME,
            MySQLiteHelper.COLUMN_INSULIN_READING_CREATED_DATETIME,
    };

    public InsulinReadingDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addInsulinReading(InsulinReading insulinReading) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_INSULIN_READING_NAME,insulinReading.getName());
        values.put(MySQLiteHelper.COLUMN_INSULIN_READING_TYPE, insulinReading.getType());
        values.put(MySQLiteHelper.COLUMN_INSULIN_TIME_OF_DAY, insulinReading.getTimeOfDay());
        values.put(MySQLiteHelper.COLUMN_INSULIN_READING_QUANTITY, insulinReading.getQuantity());
        values.put(MySQLiteHelper.COLUMN_INSULIN_READING_INJECTED_DATETIME, insulinReading.getInjecteDateTime());
        values.put(MySQLiteHelper.COLUMN_INSULIN_READING_CREATED_DATETIME, insulinReading.getCreatedDateTime());
        long insertId = database.insert(MySQLiteHelper.TABLE_INSULIN_READING, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_INSULIN_READING,
                allColumns, MySQLiteHelper.COLUMN_INSULIN_READING_ID + " = " + insertId, null,
                null, null, null);
        cursor.close();
    }

    public void deleteInsulinReading(InsulinReading insulinReading) {
        long id = insulinReading.getID();
        database.delete(MySQLiteHelper.TABLE_INSULIN_READING, MySQLiteHelper.COLUMN_INSULIN_READING_ID
                + " = " + id, null);
    }

    public List<InsulinReading> getAllInsulingReadings() {
        List<InsulinReading> insulinReadings = new ArrayList<InsulinReading>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_INSULIN_READING,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            InsulinReading insulinReading = new InsulinReading();
            insulinReading.setID(cursor.getLong(0));
            insulinReading.setName(cursor.getString(1));
            insulinReading.setType(cursor.getString(2));
            insulinReading.setTimeOfDay(cursor.getString(3));
            insulinReading.setQuantity(cursor.getInt(4));
            insulinReading.setInjecteDateTime(cursor.getLong(5));
            insulinReading.setCreatedDateTime(cursor.getLong(6));
            insulinReadings.add(insulinReading);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return insulinReadings;
    }

    public int getInsulinReadingsCount() {
        String countQuery = "SELECT  * FROM " + MySQLiteHelper.TABLE_INSULIN_READING;
        Cursor cursor = database.rawQuery(countQuery, null);
        int count = cursor.getCount();
                cursor.close();

        // return count
        return count;
    }

}


