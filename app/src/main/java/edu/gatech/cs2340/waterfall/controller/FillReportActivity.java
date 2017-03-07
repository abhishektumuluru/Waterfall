package edu.gatech.cs2340.waterfall.controller;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.Console;

import edu.gatech.cs2340.waterfall.R;
import edu.gatech.cs2340.waterfall.model.Model;
import edu.gatech.cs2340.waterfall.model.Report;
import edu.gatech.cs2340.waterfall.model.SourceReport;
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

    public void submit(View view) {
        final Model model = Model.getInstance();
        overallCondition oc = (overallCondition) ocSpinner.getSelectedItem();
        waterType wt = (waterType) wtSpinner.getSelectedItem();
        waterCondition wc = (waterCondition) wcSpinner.getSelectedItem();

        int virus = Integer.parseInt(virusLevel.getText().toString());
        int containment = Integer.parseInt(containmentLevel.getText().toString());

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        //SourceReport sr = new SourceReport(model.getCurrentUser(), location, wt, wc);
        Log.d("Submit", location.toString());
    }

}
