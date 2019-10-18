package com.example.android.spointer;

import java.util.*;


public class SemSubject {
    private int subjectId;
    private String subjectName;
    private int subjectCredits;
    public int ise;
    public int mse;
    public int ese;
    public static int sumCredits;
    public static int sumProducts;


    public SemSubject(int subjectId,String subjectName,int subjectCredits)
    {
        this.subjectCredits=subjectCredits;
        this.subjectId=subjectId;
        this.subjectName=subjectName;
    }

    public int getSubjectId()
    {
        return subjectId;
    }

    public void setSubjectId(int subjectId){this.subjectId=subjectId;}

    public int getSubjectCredits()
    {

        return subjectCredits;
    }

    public int getIse(){return ise;}

    public void setIse(int ise){this.ise=ise;}

    public int getMse(){return mse;}

    public void setMse(int mse){this.mse=ise;}

    public int getEse(){return ese;}

    public void setEse(int ese){this.ese=ise;}

    public void setSubjectCredits(int subjectCredits){this.subjectCredits=subjectCredits;}

    public String getSubjectName()
    {
        return subjectName;
    }

    public void setSubjectName(String subjectName){this.subjectName=subjectName;}



}
