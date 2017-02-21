package edu.gatech.cs2340.waterfall.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

import edu.gatech.cs2340.waterfall.R;

public class CreateProfile extends AppCompatActivity {
    FirebaseAuth auth = WelcomeActivity.getAuth();

    private List<String> modes = Arrays.asList("User", "Worker", "Manager", "Administrator");
    private Spinner modeSelection = (Spinner) findViewById(R.id.mode_selection);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        EditText nameText = (EditText) findViewById(R.id.name_text);
        nameText.setText(auth.getCurrentUser().getDisplayName());

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, modes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modeSelection.setAdapter(adapter);

    }

    public void onSubmit(View view) {
        //TODO: Functionality for add/update
        int index = modes.indexOf((String) modeSelection.getSelectedItem());
        if (index == 0) {

        } else if (index == 1) {

        } else if (index == 2) {

        } else if (index == 3) {

        }

    }
}
