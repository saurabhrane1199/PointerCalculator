package com.example.android.spointer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

public class subjectListScreen extends AppCompatActivity {

    private RecyclerView recyclerView;
    public subjectAdapter adapter;
    private ArrayList<subject> subjectList;
    public Button resultButton;

    private String branch,sem,dburl;
    private  DatabaseReference ref;

    static int sumOfPointers;
    static int sumOfCredits;
    static float pointer;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list_screen);

        branch = getIntent().getStringExtra("branch");
        sem = getIntent().getStringExtra("sem");

        branch = branch.toLowerCase();
        sem = "sem"+sem;
        dburl = branch + "/" + sem;


        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        subjectList = new ArrayList<>();
        adapter = new subjectAdapter(subjectListScreen.this,subjectList);

        setSubjectList(dburl);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        resultButton=(Button)findViewById(R.id.calcButton);
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float pointer = subject.totalCreditsScored/subject.totalCredits;
                final Intent intent = new Intent(subjectListScreen.this,ResultScreen.class);
                intent.putExtra("yourPointer",pointer);
                startActivity(intent);
            }
        });











    }

    public void setSubjectList(String dburl){

        ref= FirebaseDatabase.getInstance().getReference(dburl);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot temp: dataSnapshot.getChildren()) {

                    subjectList.add(new subject(
                            temp.child("name").getValue().toString(),
                            temp.child("credits").getValue().toString(),
                            temp.child("subjcode").getValue().toString()
                    ));
                }
                adapter.notifyDataSetChanged();

                //semS.setText(Integer.toString(subjectList.size()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });

    }






}
