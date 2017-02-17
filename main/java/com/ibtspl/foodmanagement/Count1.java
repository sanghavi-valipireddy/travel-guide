package com.ibtspl.foodmanagement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Calendar;

public class Count1 extends AppCompatActivity {
    private static final String TAG = "DATE";
    private static final String TAG1 = "COUNT";
    private CheckBox breakfast;
    private CheckBox lunch;
    private CheckBox supper;
    Button submit;
    String date1;
    private int dd, mm, yy;
    private DatabaseReference ref;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count1);
        ref = FirebaseDatabase.getInstance().getReference("Count");

        Firebase.setAndroidContext(this);

        StringBuilder date;
        final Calendar cal = Calendar.getInstance();
        dd = cal.get(Calendar.DAY_OF_MONTH);
        mm = cal.get(Calendar.MONTH);
        yy = cal.get(Calendar.YEAR);
        date = new StringBuilder().append(dd).append(" ").append(mm + 1).append(" ").append(yy);
        date1 = date.toString();

        if (submit != null) {
            submit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Log.d(TAG1, date1);
                    if (breakfast.isChecked()) {
                        mAuthListener = new FirebaseAuth.AuthStateListener() {
                            @Override
                            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                if (user != null) {
                                    // User is signed in
                                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                                    String userid= user.getUid();
                                    ref.child(date1).child("breakfast").child(userid);
                                } else {
                                    // User is signed out
                                    Log.d(TAG, "onAuthStateChanged:signed_out");
                                }

                            }
                        };

                    }

                    if (lunch.isChecked()) {
                        mAuthListener = new FirebaseAuth.AuthStateListener() {
                            @Override
                            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                if (user != null) {
                                    // User is signed in

                                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                                String userid = user.getUid();
                                    ref.child(date1).child("lunch").child(userid).setValue(true);
                                } else {
                                    // User is signed out
                                    Log.d(TAG, "onAuthStateChanged:signed_out");
                                }

                            }
                        };

                    }
                    if (supper.isChecked()) {
                        mAuthListener = new FirebaseAuth.AuthStateListener() {
                            @Override
                            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                if (user != null) {
                                    // User is signed in
                                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                                    String userid= user.getUid();
                                    ref.child(date1).child("supper").child(userid).setValue(true);
                                } else {
                                    // User is signed out
                                    Log.d(TAG, "onAuthStateChanged:signed_out");
                                }

                            }
                        };

                    }


                }

            });
            Intent intent=new Intent(Count1.this ,ThankActivity.class);
            startActivity(intent);
        }
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        mAuth.addAuthStateListener(mAuthListener);
//
//    }


}