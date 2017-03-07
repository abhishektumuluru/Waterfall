package edu.gatech.cs2340.waterfall.controller;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
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

    public void submit(View view) {
        final Model model = Model.getInstance();
        overallCondition oc = (overallCondition) ocSpinner.getSelectedItem();
        waterType wt = (waterType) wtSpinner.getSelectedItem();
        waterCondition wc = (waterCondition) wcSpinner.getSelectedItem();

        int virus = Integer.parseInt(virusLevel.getText().toString());
        int containment = Integer.parseInt(containmentLevel.getText().toString());
        User user = Model.getInstance().getCurrentUser();


        LocationManager locationManager = null;
        Location location = getLastKnownLocation(locationManager);
//        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        LocationListener locationListener = new CurrentLocationListener();
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
//        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        //SourceReport sr = new SourceReport(model.getCurrentUser(), location, wt, wc);
        String locationStr = "Lats: " + location.getLatitude() + " Long: " + location.getLongitude();
        Log.d("Submit", locationStr);
        SourceReport report = new SourceReport(user, location, wt, wc);
        Log.d("INSTANTIATED REPORT", report.toString());
        report.writeToDatabase();


    }
    private Location getLastKnownLocation(LocationManager locationManager) {
        locationManager = (LocationManager)getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
        }
        Location loc = null;
        for (String provider : providers) {
            Location l = locationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (loc == null || l.getAccuracy() < loc.getAccuracy()) {
                // Found best last known location: %s", l);
                loc = l;
            }
        }
        return loc;
    }

}
