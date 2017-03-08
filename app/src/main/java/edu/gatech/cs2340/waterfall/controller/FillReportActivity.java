package edu.gatech.cs2340.waterfall.controller;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.Console;
import java.util.List;


import edu.gatech.cs2340.waterfall.R;
import edu.gatech.cs2340.waterfall.model.Model;
import edu.gatech.cs2340.waterfall.model.Report;
import edu.gatech.cs2340.waterfall.model.SourceReport;
import edu.gatech.cs2340.waterfall.model.User;
import edu.gatech.cs2340.waterfall.model.overallCondition;
import edu.gatech.cs2340.waterfall.model.waterCondition;
import edu.gatech.cs2340.waterfall.model.waterType;

public class FillReportActivity extends AppCompatActivity {
    private Spinner ocSpinner;
    private Spinner wtSpinner;
    private Spinner wcSpinner;
    private EditText virusLevel;
    private EditText containmentLevel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_report);


        ocSpinner = (Spinner) findViewById(R.id.OCspinner);
        wtSpinner = (Spinner) findViewById(R.id.wtSpinner);
        wcSpinner = (Spinner) findViewById(R.id.wcSpinner);
        virusLevel = (EditText) findViewById(R.id.virusLevel);
        containmentLevel = (EditText) findViewById(R.id.containmentLevel);

        ocSpinner.setAdapter(new ArrayAdapter<overallCondition>(this, android.R.layout.simple_spinner_item, overallCondition.values()));
        wtSpinner.setAdapter(new ArrayAdapter<waterType>(this, android.R.layout.simple_spinner_item, waterType.values()));
        wcSpinner.setAdapter(new ArrayAdapter<waterCondition>(this, android.R.layout.simple_spinner_item, waterCondition.values()));


    }

    /**
     *
     * @param view the fill reprot view where asks for source report info
     * Submit the information collected and go back to the reports acticity
     *
     */
    public void submit(View view) {
        final Model model = Model.getInstance();
        overallCondition oc = (overallCondition) ocSpinner.getSelectedItem();
        waterType wt = (waterType) wtSpinner.getSelectedItem();
        waterCondition wc = (waterCondition) wcSpinner.getSelectedItem();

        int virus = Integer.parseInt(virusLevel.getText().toString());
        int containment = Integer.parseInt(containmentLevel.getText().toString());
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
        Intent intent = new Intent(this, ReportsActivity.class);
        startActivity(intent);


    }


    /**
     *
     * @param v the view
     * if the user cancels adding the water report
     */
    public void cancel(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
