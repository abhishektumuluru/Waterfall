package edu.gatech.cs2340.waterfall.controller;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import edu.gatech.cs2340.waterfall.R;
import edu.gatech.cs2340.waterfall.model.Model;
import edu.gatech.cs2340.waterfall.model.SourceReport;
import edu.gatech.cs2340.waterfall.model.User;
import edu.gatech.cs2340.waterfall.model.waterCondition;
import edu.gatech.cs2340.waterfall.model.waterType;

public class FillSourceReportActivity extends AppCompatActivity {
    private Spinner wtSpinner;
    private Spinner wcSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_source_report);


        wtSpinner = (Spinner) findViewById(R.id.wtSpinner);
        wcSpinner = (Spinner) findViewById(R.id.wcSpinner);

        wtSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, waterType.values()));
        wcSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, waterCondition.values()));


    }

    /**
     *
     * @param view the fill reprot view where asks for source report info
     * Submit the information collected and go back to the reports acticity
     *
     */
    public void submit(View view) {
        final Model model = Model.getInstance();
        waterType wt = (waterType) wtSpinner.getSelectedItem();
        waterCondition wc = (waterCondition) wcSpinner.getSelectedItem();

        User user = Model.getInstance().getCurrentUser();
        GPSTracker gps = new GPSTracker(this);
        Location userLocation = gps.getLocation();
        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();
        String locationStr = "Lats: " + latitude + " Long: " + longitude;
        Log.d("Submit", locationStr);
        SourceReport report = new SourceReport(user, userLocation, wt, wc);
        Log.d("INSTANTIATED REPORT", report.toString());
        report.writeToDatabase();
        Intent intent = new Intent(this, SourceReportsActivity.class);
        startActivity(intent);


    }


    /**
     *
     * @param v the view
     * if the user cancels adding the water report
     */
    public void cancel(View v) {
        Intent intent = new Intent(this, SourceReportsActivity.class);
        startActivity(intent);
    }
}

