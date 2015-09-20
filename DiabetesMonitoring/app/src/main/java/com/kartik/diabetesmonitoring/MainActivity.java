package com.kartik.diabetesmonitoring;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GraphView graph;
    LineGraphSeries<DataPoint> series_insulin_reading, series_sugar_reading;
    private int x = 0;
    private InsulinReadingDataSource insulinReadingDataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insulinReadingDataSource = new InsulinReadingDataSource(this);
        insulinReadingDataSource.open();
        drawGraphOnStart();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void addReading(View view) {
        x++;
        Calendar cal = Calendar.getInstance();
        int y_insulin_reading = Integer.valueOf(((TextView)findViewById(R.id.insuling_reading)).getText().toString());
        InsulinReading insulinReading = new InsulinReading("Lantus","Long","Morning",y_insulin_reading,cal.getTimeInMillis(),cal.getTimeInMillis());
        insulinReadingDataSource.addInsulinReading(insulinReading);
        Toast.makeText(getApplicationContext(),"Count = " + insulinReadingDataSource.getInsulinReadingsCount(),Toast.LENGTH_LONG).show();


    }

    @Override
    protected void onResume() {
        insulinReadingDataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        insulinReadingDataSource.close();
        super.onPause();
    }

    private void drawGraphOnStart() {
        GraphView graph = (GraphView) findViewById(R.id.graph);
        series_insulin_reading = new LineGraphSeries<>();
        series_insulin_reading.setColor(Color.BLUE);
        graph.addSeries(series_insulin_reading);
        List<InsulinReading> insulinReadings = insulinReadingDataSource.getAllInsulingReadings();
        int i = 0;
        for (InsulinReading insulinReading : insulinReadings) {
            series_insulin_reading.appendData(new DataPoint(i, insulinReading.getQuantity()), true, 20);
            i++;
        }
    }
}
