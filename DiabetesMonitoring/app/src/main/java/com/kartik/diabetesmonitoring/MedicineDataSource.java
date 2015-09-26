package com.kartik.diabetesmonitoring;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kartikn on 26-09-2015.
 */
public class MedicineDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {MySQLiteHelper.COLUMN_MEDICINE_MASTER_ID,
            MySQLiteHelper.COLUMN_MEDICINE_MASTER_INSULIN,
            MySQLiteHelper.COLUMN_MEDICINE_MASTER_NAME,
            MySQLiteHelper.COLUMN_MEDICINE_MASTER_PURPOSE,
            MySQLiteHelper.COLUMN_MEDICINE_MASTER_MORNING,
            MySQLiteHelper.COLUMN_MEDICINE_MASTER_NOON,
            MySQLiteHelper.COLUMN_MEDICINE_MASTER_EVENING,
            MySQLiteHelper.COLUMN_MEDICINE_MASTER_BEFORE_FOOD,
            MySQLiteHelper.COLUMN_MEDICINE_MASTER_QUANTITY_TYPE,
            MySQLiteHelper.COLUMN_MEDICINE_MASTER_QUANTITY,
    };

    public MedicineDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addMedicine(Medicine medicine) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_MEDICINE_MASTER_INSULIN, boolToInt(medicine.getIsInsulin()));
        values.put(MySQLiteHelper.COLUMN_MEDICINE_MASTER_NAME, medicine.getName());
        values.put(MySQLiteHelper.COLUMN_MEDICINE_MASTER_PURPOSE, medicine.getPurpose());
        values.put(MySQLiteHelper.COLUMN_MEDICINE_MASTER_MORNING, boolToInt(medicine.isMorning()));
        values.put(MySQLiteHelper.COLUMN_MEDICINE_MASTER_NOON, boolToInt(medicine.isNoon()));
        values.put(MySQLiteHelper.COLUMN_MEDICINE_MASTER_EVENING, boolToInt(medicine.isEvening()));
        values.put(MySQLiteHelper.COLUMN_MEDICINE_MASTER_BEFORE_FOOD, boolToInt(medicine.isBeforeFood()));
        values.put(MySQLiteHelper.COLUMN_MEDICINE_MASTER_QUANTITY_TYPE, medicine.getQuantityType());
        values.put(MySQLiteHelper.COLUMN_MEDICINE_MASTER_QUANTITY, medicine.getQuantity());
        long insertId = database.insert(MySQLiteHelper.TABLE_MEDICINE_MASTER, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_MEDICINE_MASTER,
                allColumns, MySQLiteHelper.COLUMN_MEDICINE_MASTER_ID + " = " + insertId, null,
                null, null, null);
        cursor.close();
    }

    public void deleteMedicine(Medicine medicine) {
        long id = medicine.getID();
        database.delete(MySQLiteHelper.TABLE_MEDICINE_MASTER, MySQLiteHelper.COLUMN_MEDICINE_MASTER_ID
                + " = " + id, null);
    }

    public List<Medicine> getAllMedicines() {
        List<Medicine> medicines = new ArrayList<Medicine>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_MEDICINE_MASTER,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Medicine medicine = new Medicine();
            medicine.setID(cursor.getLong(0));
            medicine.setIsInsulin(intToBoolean(cursor.getInt(1)));
            medicine.setName(cursor.getString(2));
            medicine.setPurpose(cursor.getString(3));
            medicine.setIsMorning(intToBoolean(cursor.getInt(4)));
            medicine.setIsNoon(intToBoolean(cursor.getInt(5)));
            medicine.setIsEvening(intToBoolean(cursor.getInt(6)));
            medicine.setIsBeforeFood(intToBoolean(cursor.getInt(7)));
            medicine.setQuantityType(cursor.getString(8));
            medicine.setQuantity(cursor.getDouble(9));


            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return medicines;
    }

    public int getMedicineCount() {
        String countQuery = "SELECT  * FROM " + MySQLiteHelper.TABLE_MEDICINE_MASTER;
        Cursor cursor = database.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    private int boolToInt(boolean boolvalue) {
        return (boolvalue) ? 1 : 0;
    }

    private boolean intToBoolean(int intvalue) {
        return (intvalue == 1) ? true : false;
    }

}
