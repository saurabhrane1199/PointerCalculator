package com.example.android.spointer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultScreen extends AppCompatActivity {
    private int pointer;
    TextView pv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

        pv=(TextView)findViewById(R.id.pointerview);
        Intent pointerintent= getIntent();
        Bundle b = pointerintent.getExtras();

        if(b!=null)
        {
            String j =(String) b.get("yourPointer");
            System.out.println(j);
            pv.setText(j);
        }
    }
}
