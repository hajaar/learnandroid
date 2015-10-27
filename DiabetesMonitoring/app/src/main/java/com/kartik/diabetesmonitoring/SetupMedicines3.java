package com.kartik.diabetesmonitoring;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.List;

public class SetupMedicines extends AppCompatActivity implements SetupMedicinesAdapter.ClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private MedicineDataSource medicineDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_medicines);

        medicineDataSource = new MedicineDataSource(this);
        medicineDataSource.open();
        List<Medicine> medicines = medicineDataSource.getAllMedicines();
        Log.d("medicines", "size " + medicines.size());
        mRecyclerView = (RecyclerView) findViewById(R.id.medicine_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new SetupMedicinesAdapter(medicines, this);
        Log.d("madapter", "size " + mAdapter.getItemCount());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setup_medicines, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        medicineDataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        medicineDataSource.close();
        super.onPause();
    }

    @Override
    public void itemClicked(View view, int position) {
        Toast.makeText(getApplicationContext(), "id " + position, Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), SettingsDashboardActivity.class));
    }

    public void addMedicine(View view) {
        String name, purpose;
        boolean isInsulin, isMorning, isNoon, isEvening, isBeforeFood, quantitytype;
        double quantity;
        name = ((EditText) findViewById(R.id.add_medicine_name)).getText().toString();
        purpose = ((EditText) findViewById(R.id.add_medicine_purpose)).getText().toString();
        isInsulin = ((ToggleButton) findViewById(R.id.add_medicine_is_insulin)).isChecked();
        isMorning = ((CheckBox) findViewById(R.id.add_medicine_is_morning)).isChecked();
        isNoon = ((CheckBox) findViewById(R.id.add_medicine_is_noon)).isChecked();
        isEvening = ((CheckBox) findViewById(R.id.add_medicine_is_evening)).isChecked();
        isBeforeFood = ((CheckBox) findViewById(R.id.add_medicine_is_before_food)).isChecked();
        quantitytype = ((ToggleButton) findViewById(R.id.add_medicine_quantity_type)).isChecked();
        if (quantitytype == true) {
            quantity = Double.valueOf(((EditText) findViewById(R.id.add_medicine_quantity)).getText().toString());
        } else {
            quantity = 0.0;
        }
        Medicine medicine = new Medicine();
        medicine.setName(name);
        medicine.setPurpose(purpose);
        medicine.setIsInsulin(isInsulin);
        medicine.setIsMorning(isMorning);
        medicine.setIsEvening(isEvening);
        medicine.setIsNoon(isNoon);
        medicine.setIsBeforeFood(isBeforeFood);
        medicine.setQuantityType(quantitytype);
        medicine.setQuantity(quantity);
        medicineDataSource.addMedicine(medicine);

    }

}
