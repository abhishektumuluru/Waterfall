package edu.gatech.cs2340.waterfall.controller;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import edu.gatech.cs2340.waterfall.R;
import edu.gatech.cs2340.waterfall.model.Model;

public class MainActivityUser extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d("MAINACTIVITYUSER", "Hello");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Model model = Model.getInstance();
        View nameemail = navigationView.getHeaderView(0);
        TextView nameText = (TextView) nameemail.findViewById(R.id.headerName);
        TextView emailText = (TextView) nameemail.findViewById(R.id.headerEmail);
        if (model.getInstance().getCurrentUser() != null) {
            String name = Model.getInstance().getCurrentUser().getName();
            nameText.setText(name);
            String email = Model.getInstance().getCurrentUser().getEmail();
            emailText.setText(email);
        } else {
            String name = "Log in!";
            nameText.setText(name);
            String email = "";
            emailText.setText(email);
        }
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.d("PermissionCheck", permissionCheck + "");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        }
    }

    /**
     * Close the navigation drawer when back is pressed
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Inflate the menu; this adds items to the action bar if it is present.

     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    //Navigation drawer options
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.view_water_sources) {
            Intent intent = new Intent(this, ReportsActivity.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.add_water_report) {
            Intent intent = new Intent(this, FillSourceReportActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            Intent send = new Intent();
            send.setAction(Intent.ACTION_SEND);
            send.setType("text/plain");
            send.putExtra(Intent.EXTRA_SUBJECT, "Sent via WaterFall!");
            Intent.createChooser(send,"Share with");
            startActivity(send);

        } else if (id == R.id.nav_editprof) {
            Intent intent = new Intent(this, CreateProfile.class);
            intent.putExtra("isEdit", true);
            startActivity(intent);
            //log out and return to the welcome activity when sign out is clicked
        } else if (id == R.id.nav_logout) {
            FirebaseAuth auth = WelcomeActivity.getAuth();
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {
                            // user is now signed out
                            Log.d("AUTH", "Logged out");
                            Intent intent = new Intent(MainActivityUser.this, WelcomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * when view reports is clicked we want to go to report activity
     * @param view the view that user is on
     */
    public void viewSourceReports(View view) {

        Intent intent = new Intent(this, SourceReportsActivity.class);
        startActivity(intent);
    }

    /**
     *
     * @param view map view
     */
    public void viewMap(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

}
