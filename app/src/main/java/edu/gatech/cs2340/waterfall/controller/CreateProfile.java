package edu.gatech.cs2340.waterfall.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import edu.gatech.cs2340.waterfall.model.*;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

import edu.gatech.cs2340.waterfall.R;
//Create profile activr
public class CreateProfile extends AppCompatActivity {
    //instance variables
    private List<String> modes;
    private Spinner modeSelection;
    private EditText nameText;
    private String email;
    private String uid;
    private EditText phone;
    private EditText zip;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        auth = WelcomeActivity.getAuth();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        //add a spinner for selecting the type of user
        modes = Arrays.asList("User", "Worker", "Manager", "Administrator");
        modeSelection = (Spinner) findViewById(R.id.modeSelection);

        email = auth.getCurrentUser().getEmail();
        uid = auth.getCurrentUser().getUid();


        nameText = (EditText) findViewById(R.id.name_text);
        zip = (EditText) findViewById(R.id.zipcode_text);
        phone = (EditText) findViewById(R.id.phone_text);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, modes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modeSelection.setAdapter(adapter);

    }

    /**
     * @param view the current view
     * Adds the user to the database with all relevant details.
     * Sets the current user to the one just added.
     */
    public void onSubmit(View view) {
        int index = modes.indexOf((String) modeSelection.getSelectedItem());
        int zipcode = Integer.parseInt(zip.getText().toString());
        String name = nameText.getText().toString();
        String phoneNumber = phone.getText().toString();
        if (getIntent().hasExtra("isEdit")) {
            User oldUser = Model.getInstance().getCurrentUser();
            oldUser.deleteFromDatabase();
        }
        if (index == 0) {
            User newUser = new User(uid, name, email, zipcode, phoneNumber);
            newUser.writeToDatabase("user");
            Model.getInstance().setCurrentUser(newUser);
        } else if (index == 1) {
            Worker newUser = new Worker(uid, name, email, zipcode, phoneNumber);
            newUser.writeToDatabase("worker");
            Model.getInstance().setCurrentUser(newUser);
        } else if (index == 2) {
            Manager newUser = new Manager(uid, name, email, zipcode, phoneNumber);
            newUser.writeToDatabase("manager");
            Model.getInstance().setCurrentUser(newUser);
        } else if (index == 3) {
            Administrator newUser = new Administrator(uid, name, email, zipcode, phoneNumber);
            newUser.writeToDatabase("admin");
            Model.getInstance().setCurrentUser(newUser);
        }
        Intent intent = new Intent(CreateProfile.this, MainActivity.class);
        startActivity(intent);

    }
}
