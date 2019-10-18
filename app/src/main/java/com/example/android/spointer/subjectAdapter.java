package com.example.android.spointer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class subjectAdapter extends RecyclerView.Adapter<subjectAdapter.MyViewHolder> {
    public ArrayList<subject> subjectList;
    public Context mContext;
    static int temp;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView subjectCode,subjectCredits;
        public EditText ise,mse,ese;
        public Button subButton;

        public MyViewHolder(View view){
            super(view);
            subjectCode = (TextView)view.findViewById(R.id.subjectCode);
            subjectCredits = (TextView)view.findViewById(R.id.subjectCredits);
            ise = (EditText)view.findViewById(R.id.ise);
            mse = (EditText)view.findViewById(R.id.mse);
            ese = (EditText)view.findViewById(R.id.ese);
        }

    }

    public subjectAdapter(Context mContext,ArrayList<subject> subjectList){
        this.mContext = mContext;
        this.subjectList = subjectList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_card,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {


        temp = 0;
        final subject semsubject = subjectList.get(position);

        holder.subjectCode.setText(semsubject.subjcode);
        holder.subjectCredits.setText(semsubject.credits);

        holder.ise.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!TextUtils.isEmpty(holder.ise.getText().toString())){
                        temp += Integer.parseInt(holder.ise.getText().toString());
                        holder.ise.setEnabled(false);
                    }
                }

            }
        });

        holder.mse.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!TextUtils.isEmpty(holder.mse.getText().toString())){
                        temp += Integer.parseInt(holder.mse.getText().toString());
                        holder.mse.setEnabled(false);
                    }
                }

            }
        });

        holder.ese.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!TextUtils.isEmpty(holder.ese.getText().toString())){
                        int pointer;
                        temp += Integer.parseInt(holder.ese.getText().toString());
                        holder.ese.setEnabled(false);
                        pointer = getPointer(temp);
                        subject.totalCreditsScored += subject.totalCreditsScored + pointer*Integer.parseInt(semsubject.credits);
                        subject.totalCredits += subject.totalCredits + Integer.parseInt(semsubject.credits);
                    }
                }

            }
        });





    }


    public int getPointer(int totalMarks){
        int pointer;
        if(totalMarks >= 80)
            return 10;
        else if(totalMarks >= 75)
            return  9;
        else if(totalMarks >= 70)
            return 8;
        else if(totalMarks >=60)
            return 7;
        else
            return 6;
    }








    @Override
    public int getItemCount() {
        return subjectList.size();
    }


}
