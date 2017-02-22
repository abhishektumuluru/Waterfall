package edu.gatech.cs2340.waterfall.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import edu.gatech.cs2340.waterfall.model.*;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

import edu.gatech.cs2340.waterfall.R;

public class CreateProfile extends AppCompatActivity {
    FirebaseAuth auth = WelcomeActivity.getAuth();

    private List<String> modes;
    private Spinner modeSelection;
    private String name;
    private String email;
    private String uid;
    private EditText phone;
    private EditText zip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        modes = Arrays.asList("User", "Worker", "Manager", "Administrator");
        modeSelection = (Spinner) findViewById(R.id.modeSelection);
        name = auth.getCurrentUser().getDisplayName();
        email = auth.getCurrentUser().getEmail();
        uid = auth.getCurrentUser().getUid();

        EditText nameText = (EditText) findViewById(R.id.name_text);
        zip = (EditText) findViewById(R.id.zipcode_text);
        phone = (EditText) findViewById(R.id.phone_text);

        nameText.setText(name);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, modes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modeSelection.setAdapter(adapter);

    }

    public void onSubmit(View view) {
        //TODO: Functionality for add/update
        int index = modes.indexOf((String) modeSelection.getSelectedItem());
        int zipcode = Integer.parseInt(zip.getText().toString());
        String phoneNumber = phone.getText().toString();
        if (getIntent().hasExtra("isEdit")) {
            User oldUser = Model.getInstance().getCurrentUser();
            oldUser.deleteFromDatabase();
        }
        if (index == 0) {
            User newUser = new User(uid, email, name, zipcode, phoneNumber);
            newUser.writeToDatabase();
            Model.getInstance().setCurrentUser(newUser);
        } else if (index == 1) {
            Worker newUser = new Worker(uid, email, name, zipcode, phoneNumber);
            newUser.writeToDatabase();
            Model.getInstance().setCurrentUser(newUser);
        } else if (index == 2) {
            Manager newUser = new Manager(uid, email, name, zipcode, phoneNumber);
            newUser.writeToDatabase();
            Model.getInstance().setCurrentUser(newUser);
        } else if (index == 3) {
            Administrator newUser = new Administrator(uid, email, name, zipcode, phoneNumber);
            newUser.writeToDatabase();
            Model.getInstance().setCurrentUser(newUser);
        }

    }
}
