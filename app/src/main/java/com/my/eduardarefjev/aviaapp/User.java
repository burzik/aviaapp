package com.my.eduardarefjev.aviaapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by EduardArefjev on 03/11/2017.
 */

public class User implements Parcelable {
    String email;
    String firstName;
    String lastName;
    String privileges;
    String uid;

    public User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String email, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    //EA Parcel Data
    private User(Parcel in) {
        email = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        privileges = in.readString();
        uid = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(email);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(privileges);
        dest.writeString(uid);
    }
}
