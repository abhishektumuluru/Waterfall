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

        ListView reports = (ListView) findViewById(R.id.reports_listview);
        DatabaseReference ref = WelcomeActivity.getmReportDatabase();
        populateListview(ref, reports);

    }

    public void populateListview(DatabaseReference ref, final ListView reports) {
        readData(ref, new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                ArrayList<String> reportList = new ArrayList<String>();
                for (DataSnapshot report: dataSnapshot.getChildren()) {
                    String condition = report.child("condition").getValue(String.class);
                    String type = report.child("type").getValue(String.class);
                    Double lat = report.child("location").child("latitude").getValue(Double.class);
                    Double longitude = report.child("location").child("longitude").getValue(Double.class);
                    String toPrint = condition + "   " + type + "     " + longitude + "   " + lat;
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

    public void openReportFillPage(View view) {

        Intent intent = new Intent(this, FillReportActivity.class);
        startActivity(intent);
    }


}
