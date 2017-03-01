package edu.gatech.cs2340.waterfall.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.gatech.cs2340.waterfall.controller.WelcomeActivity;

/**
 * Created by vivekraja07 on 2/21/17.
 */
public class User implements Parcelable {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    //instance variables
    private String name;
    private String uid;
    private String email;
    private final String type = "user";
    private int zipcode;
    private String phoneNumber;

    public User(String name, String uid, String email, int zipcode, String phoneNumber) {
        this.name = name;
        this.uid = uid;
        this.email = email;
        this.zipcode = zipcode;
        this.phoneNumber = phoneNumber;
    }
    //constructor
    public User(Parcel parcel) {
        this.name = parcel.readString();
        this.uid = parcel.readString();
        this.email = parcel.readString();
        this.zipcode = parcel.readInt();
        this.phoneNumber = parcel.readString();

    }

    public int describeContents() {
        return 0;
    }

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
    //write to database
    public void writeToDatabase() {
        DatabaseReference mUserDatabase = WelcomeActivity.getmUserDatabase();
        //mUserDatabase.child(uid).child("email").setValue(email);
        mUserDatabase.child(uid).child("name").setValue(name);
        mUserDatabase.child(uid).child("zipcode").setValue(zipcode);
        mUserDatabase.child(uid).child("phone number").setValue(phoneNumber);
        mUserDatabase.child(uid).child("type").setValue(type);

    }

    //delete from database
    public void deleteFromDatabase() {
        DatabaseReference mUserDatabase = WelcomeActivity.getmUserDatabase();
        mUserDatabase.child(uid).removeValue();
    }

    //action methods
    public void submitReport() {
        //TODO
    }

    public void viewSources() {
        //TODO
    }

    //getters & setters

}
