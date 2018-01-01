package com.my.eduardarefjev.aviaapp.UserInformation;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * HISTORY
 * 	Date			Author				Comments
 * 	03.11.2017		Eduard Arefjev 		Created "User" class, which store main information about users
 * 	28.12.2017      Eduard Arefjev      Some minor refactoring
 * 	01.01.2017      Eduard Arefjev      Fixed email error
 */

public class User implements Parcelable {
    private String email;
    private String firstName;
    private String lastName;
    private String privileges;
    private String uid;

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

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    public String getPrivileges() {
        return privileges;
    }

    void setPrivileges(String privileges) {
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
