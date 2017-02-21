package edu.gatech.cs2340.waterfall.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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

public class WelcomeActivity extends AppCompatActivity {

    private static FirebaseAuth auth;
    private static final int RC_SIGN_IN = 0;
    private static DatabaseReference mUserDatabase;
    private static DatabaseReference mReportDatabase;

    /**
     *
     * @return Firebase Auth
     */
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
    }

    public void openLoginScreen(View view) {
        auth = FirebaseAuth.getInstance();
        mUserDatabase = FirebaseDatabase.getInstance().getReference("users");
        mReportDatabase = FirebaseDatabase.getInstance().getReference("Reports");
        if (auth.getCurrentUser() != null) {
            Log.d("LOGGED", auth.getCurrentUser().getEmail());
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            startActivityForResult(AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setProviders(Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                    new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                                    new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build()))
                            .build(),
                    RC_SIGN_IN);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == ResultCodes.OK) {
                final String uniqueId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                final String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                final String displayName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
                DatabaseReference UserRef = mUserDatabase.child(uniqueId);
                UserRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Object userData = dataSnapshot.getValue();
                        Intent intent;
                        if (userData == null) {
                            //mUserDatabase.child(uniqueId).child("email").setValue(email);
                            //mUserDatabase.child(uniqueId).child("name").setValue(displayName);
                            intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        } else {
                            //mUserDatabase.setValue(uniqueId);
                            //mUserDatabase.child("email").setValue(email);
                            intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        }
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Log.d("AUTH", auth.getCurrentUser().getEmail());

                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
