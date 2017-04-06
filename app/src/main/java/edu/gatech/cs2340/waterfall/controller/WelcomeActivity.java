package edu.gatech.cs2340.waterfall.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

import edu.gatech.cs2340.waterfall.R;
import edu.gatech.cs2340.waterfall.model.Administrator;
import edu.gatech.cs2340.waterfall.model.Manager;
import edu.gatech.cs2340.waterfall.model.Model;
import edu.gatech.cs2340.waterfall.model.User;
import edu.gatech.cs2340.waterfall.model.Worker;


@SuppressWarnings("ALL")
public class WelcomeActivity extends AppCompatActivity {

    private static FirebaseAuth auth;
    private static final int RC_SIGN_IN = 0;
    private static DatabaseReference mUserDatabase;
    private static DatabaseReference mSourceReportDatabase;
    private static DatabaseReference mPurityReportDatabase;

    private static String type;
    private static String name;
    private static String phoneNumber;
    private static int zip;
    private static String uniqueId;
    private static String email;

    /**
     * getter method for auth
     */
    public static FirebaseAuth getAuth() {
        return auth;
    }

    /**
     * getter method for user database
     */
    public static DatabaseReference getUserDatabase() {
        return mUserDatabase;
    }

    /**
     * getter method for source report database
     */
    public static DatabaseReference getSourceReportDatabase() {
        return mSourceReportDatabase;
    }

    /**
     * getter method for purity report database
     */
    public static DatabaseReference getPurityReportDatabase() {
        return mPurityReportDatabase;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ImageView myImageView = (ImageView)findViewById(R.id.logo);
        Animation fade = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        myImageView.startAnimation(fade);
    }

    /**
     * @param view the current view
     * Open a login screen for the user
     *  If the user is already signed, then open the main activity
     *  Otherwise, open the fire base login
     */
    public void openLoginScreen(View view) {
        auth = FirebaseAuth.getInstance();
        mUserDatabase = FirebaseDatabase.getInstance().getReference("users");
        mSourceReportDatabase = FirebaseDatabase.getInstance().getReference("Source Reports");
        mPurityReportDatabase = FirebaseDatabase.getInstance().getReference("Purity Reports");
        if (auth.getCurrentUser() != null) {
            Log.d("LOGGED", auth.getCurrentUser().getEmail());
            //finish();
            String uid = auth.getCurrentUser().getUid();
            DatabaseReference ref = mUserDatabase.child(uid);
            setUser(ref);
        } else {
            //open fire base login if current user is null i.e. not signed in
            startActivityForResult(AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setProviders(Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                    new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                                    new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build()))
                            .build(),
                    RC_SIGN_IN);
        }
    }


    /**
     *
     * @param ref reference to the database
     * @param listener an instance of the get data listener
     * check if we can read data
     */
    private void readData(DatabaseReference ref, final OnGetDataListener listener) {
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
     * set the current user in the model after retrieving from fire base
     * @param ref reference to the database
     */
    private void setUser(DatabaseReference ref) {
        readData(ref, new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                uniqueId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                type = dataSnapshot.child("type").getValue(String.class);
                name = dataSnapshot.child("name").getValue(String.class);
                zip = dataSnapshot.child("zipcode").getValue(int.class);
                phoneNumber = dataSnapshot.child("phone number").getValue(String.class);
                Intent intent = null;
                if (type.equals("user")) {
                    Model.getInstance().setCurrentUser(new User(uniqueId, name, email, zip, phoneNumber));
                    intent = new Intent(WelcomeActivity.this, MainActivityUser.class);
                } else if (type.equals("worker")) {
                    Model.getInstance().setCurrentUser(new Worker(uniqueId, name, email, zip, phoneNumber));
                    intent = new Intent(WelcomeActivity.this, MainActivityWorker.class);
                } else if (type.equals("manager")) {
                    Model.getInstance().setCurrentUser(new Manager(uniqueId, name, email, zip, phoneNumber));
                    intent = new Intent(WelcomeActivity.this, MainActivityManager.class);
                } else if (type.equals("admin")) {
                    Model.getInstance().setCurrentUser(new Administrator(uniqueId, name, email, zip, phoneNumber));
                    intent = new Intent(WelcomeActivity.this, MainActivityAdmin.class);
                }
                startActivity(intent);
            }

            @Override
            public void onStart() {
                Log.d("On Start", "Started");
            }

            @Override
            public void onFailure() {

            }
        });
    }

    /**
     * @param requestCode The request code
     * @param resultCode The result code
     * @param data The intent which determines the response
     *
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == ResultCodes.OK) {
                String uniqueId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                final DatabaseReference UserRef = mUserDatabase.child(uniqueId);
                UserRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Object userData = dataSnapshot.getValue();
                        Intent intent;
                        if (userData == null) {
                            //mUserDatabase.child(uniqueId).child("email").setValue(email);
                            //mUserDatabase.child(uniqueId).child("name").setValue(displayName);
                            intent = new Intent(WelcomeActivity.this, CreateProfile.class);
                            startActivity(intent);
                        } else {
                            //mUserDatabase.setValue(uniqueId);
                            //mUserDatabase.child("email").setValue(email);
                            setUser(UserRef);
                        }
                        //startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Log.d("AUTH", auth.getCurrentUser().getEmail());


                //finish();
            }
        }
    }
}
