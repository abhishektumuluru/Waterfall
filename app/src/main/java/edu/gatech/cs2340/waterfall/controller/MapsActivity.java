package edu.gatech.cs2340.waterfall.controller;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import edu.gatech.cs2340.waterfall.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DatabaseReference ref;
    private LatLng loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        ref = WelcomeActivity.getmSourceReportDatabase();
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        readData(ref, new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot report: dataSnapshot.getChildren()) {
                    loc = new LatLng(report.child("location").child("latitude").getValue(Double.class), report.child("location").child("longitude").getValue(Double.class));
                    int date = report.child("dateAndTime").child("date").getValue(int.class);
                    int month = report.child("dateAndTime").child("month").getValue(int.class);
                    String condition = report.child("condition").getValue(String.class);
                    String type = report.child("type").getValue(String.class);
                    int hour = report.child("dateAndTime").child("hours").getValue(int.class);
                    int minutes = report.child("dateAndTime").child("minutes").getValue(int.class);
                    mMap.addMarker(new MarkerOptions().position(loc).title(hour + ":" + minutes + "   " + date + "/" + month + "   " + condition + "   " + type));
                }
                mMap.setMyLocationEnabled(true);
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFailure() {

            }
        });
        //mMap.moveCamera(CameraUpdateFactory.newLatLng());
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
}
