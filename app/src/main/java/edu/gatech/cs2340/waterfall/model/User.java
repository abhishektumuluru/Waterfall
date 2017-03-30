package edu.gatech.cs2340.waterfall.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.v4.view.ViewCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.gatech.cs2340.waterfall.controller.WelcomeActivity;


public class User implements Parcelable {
    //Getters and setters for user class

    /**
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name to change
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return the zipcode
     */
    public int getZipcode() {
        return zipcode;
    }

    /**
     *
     * @param zipcode to change
     */
    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    /**
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @param phoneNumber to change
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    //instance variables
    private String name;
    private String uid;
    private String email;
    //private String type = "user";
    private int zipcode;
    private String phoneNumber;
    //Constructor
    public User(String uid, String name, String email, int zipcode, String phoneNumber) {
        this.name = name;
        this.uid = uid;
        this.email = email;
        this.zipcode = zipcode;
        this.phoneNumber = phoneNumber;
    }
    //Parcelable constructor
    public User(Parcel parcel) {
        this.name = parcel.readString();
        this.uid = parcel.readString();
        this.email = parcel.readString();
        this.zipcode = parcel.readInt();
        this.phoneNumber = parcel.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flag) {
        parcel.writeString(this.name);
        parcel.writeString(this.uid);
        parcel.writeString(this.email);
        parcel.writeInt(this.zipcode);
        parcel.writeString(this.phoneNumber);
    }
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    /**
     * @param type The type of the user
     * Write the user's details to the database
     */
    public void writeToDatabase(String type) {
        DatabaseReference mUserDatabase = WelcomeActivity.getmUserDatabase();
        mUserDatabase.child(uid).child("name").setValue(name);
        mUserDatabase.child(uid).child("email").setValue(email);
        mUserDatabase.child(uid).child("zipcode").setValue(zipcode);
        mUserDatabase.child(uid).child("phone number").setValue(phoneNumber);
        mUserDatabase.child(uid).child("type").setValue(type);

    }

    /**
     * Delete the user from the database
     */
    public void deleteFromDatabase() {
        DatabaseReference mUserDatabase = WelcomeActivity.getmUserDatabase();
        mUserDatabase.child(uid).removeValue();
    }

    /**
     * Allow the user to submit a water report
     */
//     public void submitReport() {
//         //TODO
//     }

    /**
     * View water sources in your area
     */
//     public void viewSources() {
//         //TODO
//     }
}
