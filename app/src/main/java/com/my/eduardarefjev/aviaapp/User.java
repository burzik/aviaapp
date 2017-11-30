package com.my.eduardarefjev.aviaapp;

/**
 * Created by EduardArefjev on 03/11/2017.
 */

public class User {
    String firstName;
    String lastName;
    String email;
    String privileges;
    String uid;

    public User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
}
