package com.example.android.spointer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profileScreen extends AppCompatActivity {
    FirebaseUser currentUser;
    String uid;
    TextView uidtv,branchtv,semtv;
    DatabaseReference ref;
    ImageView profilePhoto;
    private GoogleSignInAccount acct;
    final Context context = this;
    Button abs_pointer,pred_pointer;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        profilePhoto=(ImageView)findViewById(R.id.profilePicture);
        branchtv = (TextView)findViewById(R.id.branch_tv);
        semtv = (TextView)findViewById(R.id.sem_tv);
        abs_pointer = (Button)findViewById(R.id.abspointer);
        pred_pointer = (Button)findViewById(R.id.predictpointer);

        acct = GoogleSignIn.getLastSignedInAccount(profileScreen.this);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        uid = currentUser.getUid();

        final String personName = acct.getDisplayName();
        final String emailid = acct.getEmail();

        uidtv=(TextView)findViewById(R.id.uidTv);
        uidtv.setText(personName);

        try{
            Glide.with(this).load(acct.getPhotoUrl()).into(profilePhoto);
        }catch (NullPointerException e){
            Toast.makeText(getApplicationContext(),"image not found",Toast.LENGTH_LONG).show();
        }

        ref= FirebaseDatabase.getInstance().getReference("users");



        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                User loggedinuser = dataSnapshot.child(uid).getValue(User.class);

                if(loggedinuser!=null){
                    branchtv.setText(loggedinuser.branch);
                    semtv.setText(loggedinuser.sem);
                }
                else
                    {
                    writeNewUser(uid,personName,emailid);



                    LayoutInflater li = LayoutInflater.from(context);
                    View promptsView = li.inflate(R.layout.input_dailog, null);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                    // set prompts.xml to alertdialog builder
                    alertDialogBuilder.setView(promptsView);

                    final Spinner branchsp = (Spinner)promptsView.findViewById(R.id.branch_spinner);
                    final Spinner semsp = (Spinner)promptsView.findViewById(R.id.sem_spinner);


                    // set dialog message
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            // get user input and set it to result
                                            // edit text
                                            String branch = String.valueOf(branchsp.getSelectedItem());
                                            String sem = String.valueOf(semsp.getSelectedItem());
                                            ref.child(uid).child("branch").setValue(branch);
                                            ref.child(uid).child("sem").setValue(sem);
                                            branchtv.setText(branch);
                                            semtv.setText(sem);

                                        }
                                    })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            dialog.cancel();
                                        }
                                    });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("UserListActivity", "Error occured");
            }
        });

        final Intent intent = new Intent(this,subjectListScreen.class);
        intent.putExtra("branch",branchtv.getText());
        intent.putExtra("sem",semtv.getText());

        abs_pointer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(profileScreen.this,subjectListScreen.class);
                intent.putExtra("branch",branchtv.getText());
                intent.putExtra("sem",semtv.getText());
                startActivity(intent);
            }
        });


    }


    public void writeNewUser(String id,String name,String emailid)
    {
        User user = new User(name,emailid);
        ref.child(id).setValue(user);
    }
}
