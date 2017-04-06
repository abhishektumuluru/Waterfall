package edu.gatech.cs2340.waterfall.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import edu.gatech.cs2340.waterfall.R;

public class ReportsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        ListView reports = (ListView) findViewById(R.id.reports_listView);
        DatabaseReference ref = WelcomeActivity.getSourceReportDatabase();
        populateListView(ref, reports);

    }

    /**
     * populate the list view in the reports activity
     * @param ref the reference to the database
     * @param reports the reports
     */
    public void populateListView(DatabaseReference ref, final ListView reports) {
        readData(ref, new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                ArrayList<String> reportList = new ArrayList<String>();
                reportList.add("Condition  Type  Long  Lat  Date Time");
                for (DataSnapshot report: dataSnapshot.getChildren()) {
                    String condition = report.child("condition").getValue(String.class);
                    String type = report.child("type").getValue(String.class);
                    int date = report.child("dateAndTime").child("date").getValue(int.class);
                    int month = report.child("dateAndTime").child("month").getValue(int.class);
                    int hours = report.child("dateAndTime").child("hours").getValue(int.class);
                    int minutes = report.child("dateAndTime").child("minutes").getValue(int.class);
                    Double lat = report.child("location").child("latitude").getValue(Double.class);
                    lat = (Math.round(lat * 100.0)) / 100.0;
                    Double longitude = report.child("location").child("longitude").getValue(Double.class);
                    longitude = (Math.round(longitude * 100.0)) / 100.0;
                    String toPrint = condition + " " + type + "  " + longitude + " " + lat + "  " + month + "/" + date + "  " + hours + ":" + minutes;
                    reportList.add(toPrint);
                }
                ArrayAdapter<String> reportsAdapter = new ArrayAdapter<String>(ReportsActivity.this, android.R.layout.simple_list_item_1,reportList);
                reports.setAdapter(reportsAdapter);
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFailure() {

            }
        });
    }

    /**
     * read the data from the database
     * @param ref to the data base
     * @param listener the data listener
     */
    public void readData(DatabaseReference ref, final OnGetDataListener listener) {
        listener.onStart();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listener.onSuccess(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onFailure();
            }
        });

    }

    /**
     * if the user wants to submit the report, go to the fill reports activity
     * @param view of the user
     */
    public void openReportFillPage(View view) {

        Intent intent = new Intent(this, FillSourceReportActivity.class);
        startActivity(intent);
    }


}
