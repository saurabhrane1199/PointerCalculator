package com.example.android.spointer;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String name;
    public String emailid;
    public String branch;
    public String sem;
    //private String[] subject = new String[8];

    public User()
    {

    }

    public User(String name,String emailid)
    {
        this.name = name;
        this.emailid = emailid;
    }
}
