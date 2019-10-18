package com.example.android.spointer;

import android.icu.text.StringSearch;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class subject
{
    String name;
    String credits;
    String subjcode;
    static int totalCredits;
    static int totalCreditsScored;

    public subject(){

    }

    public subject(String name,String credits,String subjcode)
    {
        this.credits = credits;
        this.name = name;
        this.subjcode = subjcode;
    }

}