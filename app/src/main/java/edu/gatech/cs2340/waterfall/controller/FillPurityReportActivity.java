package edu.gatech.cs2340.waterfall.controller;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


import edu.gatech.cs2340.waterfall.R;
import edu.gatech.cs2340.waterfall.model.Model;
import edu.gatech.cs2340.waterfall.model.PurityReport;
import edu.gatech.cs2340.waterfall.model.User;
import edu.gatech.cs2340.waterfall.model.overallCondition;

public class FillPurityReportActivity extends AppCompatActivity {
    private Spinner ocSpinner;
    private EditText virusLevel;
    private EditText containmentLevel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_purity_report);

        ocSpinner = (Spinner) findViewById(R.id.overallConditionSpinner);

        virusLevel = (EditText) findViewById(R.id.virusLevel);
        containmentLevel = (EditText) findViewById(R.id.containmentLevel);

        ocSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, overallCondition.values()));



    }

    /**
     *
     * @param view the fill report view where asks for source report info
     * Submit the information collected and go back to the reports activity
     *
     */
    public void submit(View view) {
        overallCondition oc = (overallCondition) ocSpinner.getSelectedItem();


        int virus = Integer.parseInt(virusLevel.getText().toString());
        int containment = Integer.parseInt(containmentLevel.getText().toString());
        User user = Model.getInstance().getCurrentUser();
        GPSTracker gps = new GPSTracker(this);
        Location userLocation = gps.getLocation();
        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();
        String locationStr = "Lats: " + latitude + " Long: " + longitude;
        Log.d("Submit", locationStr);
        PurityReport report = new PurityReport(user, userLocation, oc, virus, containment);
        Log.d("INSTANTIATED REPORT", report.toString());
        report.writeToDatabase();
        cancel(view);


    }


    /**
     *
     * @param v the view
     * if the user cancels adding the water report
     */
    public void cancel(View v) {
        Intent i = getIntent();
        Intent intent;
        if (i.hasExtra("Worker")) {
            intent = new Intent(this, MainActivityWorker.class);
        } else {
            intent = new Intent(this, MainActivityManager.class);
        }
        startActivity(intent);
    }
}
