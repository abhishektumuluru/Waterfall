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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

import edu.gatech.cs2340.waterfall.R;
import edu.gatech.cs2340.waterfall.model.Administrator;
import edu.gatech.cs2340.waterfall.model.Manager;
import edu.gatech.cs2340.waterfall.model.Model;
import edu.gatech.cs2340.waterfall.model.User;
import edu.gatech.cs2340.waterfall.model.Worker;
import retrofit2.http.HEAD;

import static android.R.attr.data;
import static android.R.attr.dateTextAppearance;
import static android.R.attr.type;

public class WelcomeActivity extends AppCompatActivity {

    private static FirebaseAuth auth;
    private static final int RC_SIGN_IN = 0;
    private static DatabaseReference mUserDatabase;
    private static DatabaseReference mReportDatabase;

    private static String type;
    private static String name;
    private static String phoneNumber;
    private static int zip;
    private static String uniqueId;
    private static String email;


    public static FirebaseAuth getAuth() {
        return auth;
    }

    public static DatabaseReference getmUserDatabase() {
        return mUserDatabase;
    }

    public static DatabaseReference getmReportDatabase() {
        return mReportDatabase;
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
     *  Otherwise, open the firebase login
     */
    public void openLoginScreen(View view) {
        auth = FirebaseAuth.getInstance();
        mUserDatabase = FirebaseDatabase.getInstance().getReference("users");
        mReportDatabase = FirebaseDatabase.getInstance().getReference("Reports");
        if (auth.getCurrentUser() != null) {
            Log.d("LOGGED", auth.getCurrentUser().getEmail());
            //finish();
            String uid = auth.getCurrentUser().getUid();
            DatabaseReference ref = mUserDatabase.child(uid);
            setUser(ref);
//            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
//            startActivity(intent);
        } else {
            //open firebase login if current user is null i.e. not signed in
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
     * Retrieve the user details from firebase and set the local current user in the model
     */
//    public static void setUser() {
//        Log.d("LOGGED", "SET USER CALLED");
//        String uniqueId = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
//        Log.d("EMAIL FROM FIREBASE", email);
//        String name = mUserDatabase.child(uniqueId).child("name").orderByValue().toString();
//        int zip = mUserDatabase.child(uniqueId).child("zipcode").orderByValue().hashCode();
//        String phone = mUserDatabase.child(uniqueId).child("phone number").orderByValue().toString();
//
//        DatabaseReference typ = mUserDatabase.child(uniqueId).child("type");
//        typ.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.d("LOGGING", "Entered");
//                type = dataSnapshot.getValue().toString();
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError e) {
//                Log.d("Error", e.getMessage());
//            }
//        });
//        //make a user locally in the model
//        if (type.equals("user")) {
//            Model.getInstance().setCurrentUser(new User(uniqueId, email, name, zip, phone));
//        } else if (type.equals("worker")) {
//            Model.getInstance().setCurrentUser(new Worker(uniqueId, email, name, zip, phone));
//        } else if (type.equals("manager")) {
//            Model.getInstance().setCurrentUser(new Manager(uniqueId, email, name, zip, phone));
//        } else if (type.equals("admin")) {
//            Model.getInstance().setCurrentUser(new Administrator(uniqueId, email, name, zip, phone));
//        }
//    }

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

    public void setUser(DatabaseReference ref) {
        readData(ref, new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                uniqueId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                type = dataSnapshot.child("type").getValue(String.class);
                name = dataSnapshot.child("name").getValue(String.class);
                zip = dataSnapshot.child("zipcode").getValue(int.class);
                phoneNumber = dataSnapshot.child("phone number").getValue(String.class);
                Log.d("ONDATACHANGE", type + " " + name);
                if (type.equals("user")) {
                    Model.getInstance().setCurrentUser(new User(uniqueId, name, email, zip, phoneNumber));
                } else if (type.equals("worker")) {
                    Model.getInstance().setCurrentUser(new Worker(uniqueId, name, email, zip, phoneNumber));
                } else if (type.equals("manager")) {
                    Model.getInstance().setCurrentUser(new Manager(uniqueId, name, email, zip, phoneNumber));
                } else if (type.equals("admin")) {
                    Model.getInstance().setCurrentUser(new Administrator(uniqueId, name, email, zip, phoneNumber));
                }
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onStart() {
                Log.d("ONSTART", "Started");
            }

            @Override
            public void onFailure() {

            }
        });
    }
//    public static void setUser() {
//        Log.d("LOGGED", "SET USER CALLED");
//        final String uniqueId = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        final String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
//        Log.d("EMAIL FROM FIREBASE", email);
//        DatabaseReference ref = mUserDatabase.child(uniqueId);
//        //make a user locally in the model
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
////                type = dataSnapshot.child("type").getValue(String.class);
////                name = dataSnapshot.child("name").getValue(String.class);
////                zip = dataSnapshot.child("zipcode").getValue(int.class);
////                phoneNumber = dataSnapshot.child("phone number").getValue(String.class);
////                Log.d("ONDATACHANGE", type + " " + name);
////                if (type.equals("user")) {
////                    Model.getInstance().setCurrentUser(new User(uniqueId, email, name, zip, phoneNumber));
////                } else if (type.equals("worker")) {
////                    Model.getInstance().setCurrentUser(new Worker(uniqueId, email, name, zip, phoneNumber));
////                } else if (type.equals("manager")) {
////                    Model.getInstance().setCurrentUser(new Manager(uniqueId, email, name, zip, phoneNumber));
////                } else if (type.equals("admin")) {
////                    Model.getInstance().setCurrentUser(new Administrator(uniqueId, email, name, zip, phoneNumber));
////                }
////                Log.d("ONDATACHANGE", "Excuted");
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
////        if (type.equals("user")) {
////            Model.getInstance().setCurrentUser(new User(uniqueId, email, name, zip, phone));
////        } else if (type.equals("worker")) {
////            Model.getInstance().setCurrentUser(new Worker(uniqueId, email, name, zip, phone));
////        } else if (type.equals("manager")) {
////            Model.getInstance().setCurrentUser(new Manager(uniqueId, email, name, zip, phone));
////        } else if (type.equals("admin")) {
////            Model.getInstance().setCurrentUser(new Administrator(uniqueId, email, name, zip, phone));
////        }
//    }

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
                //final String displayName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
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
                        } else {
                            //mUserDatabase.setValue(uniqueId);
                            //mUserDatabase.child("email").setValue(email);
                            setUser(UserRef);
                            //intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        }
                        //startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Log.d("AUTH", auth.getCurrentUser().getEmail());

                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                //finish();
            }
        }
    }
}
