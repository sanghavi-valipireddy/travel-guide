package com.ibtspl.foodmanagement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Count extends AppCompatActivity {
    private static final String TAG = "DATE";
    private static final String TAG1 = "COUNT";
    private CheckBox breakfast;
    private CheckBox lunch;
    private CheckBox supper;
    Button submit;
    String date1;
    private int dd, mm, yy;
    private DatabaseReference ref,ref1;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference("Count");



        breakfast = (CheckBox) findViewById(R.id.breakfast);
        lunch = (CheckBox) findViewById(R.id.lunch);
        supper = (CheckBox) findViewById(R.id.supper);
        submit = (Button) findViewById(R.id.submit);

//        Firebase.setAndroidContext(this);

        StringBuilder date;
        final Calendar cal = Calendar.getInstance();
        dd = cal.get(Calendar.DAY_OF_MONTH);
        mm = cal.get(Calendar.MONTH);
        yy = cal.get(Calendar.YEAR);
        date = new StringBuilder().append(dd).append(" ").append(mm + 1).append(" ").append(yy);
        date1 = date.toString();
        // Log.d(TAG,)
        if (submit != null) {
            submit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {


                            Log.d(TAG1, date1);
                            if (breakfast.isChecked()) {
                                Log.d(TAG, "1");
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                Log.d(TAG, "5");
                                userid = user.getUid();
                                ref.child(date1).child("breakfast").child(userid).setValue("1");
                            } else {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                if(user!=null)
                                userid = user.getUid();
                                ref.child(date1).child("breakfast").child(userid).setValue("0");
                            }

                            if (lunch.isChecked()) {
                                Log.d(TAG, "1");
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                Log.d(TAG, "5");
                                userid = user.getUid();
                                ref.child(date1).child("lunch").child(userid).setValue("1");

                            } else {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                userid = user.getUid();
                                ref.child(date1).child("lunch").child(userid).setValue("0");
                            }
                            if (supper.isChecked()) {
                                Log.d(TAG, "1");
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                Log.d(TAG, "5");
                                userid = user.getUid();
                                ref.child(date1).child("supper").child(userid).setValue("1");
                            } else {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                userid = user.getUid();
                                ref.child(date1).child("supper").child(userid).setValue("0");
                            }


                            OnSubmitting (view);



                }

            });


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * On selecting action bar icons
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {

            case R.id.action_help:
                // help action
                OnHelp();
                return true;
            case R.id.logout:
//                OnLogOut();
                signOut();

                // logout
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * Launching new activity`
     * */
//    private void OnLogOut() {
//        Intent i = new Intent(MenuActivity.this, LoginActivity.class);
//        startActivity(i);
//    }


    private void signOut() {
        mAuth.signOut();
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);

    }

    private void OnHelp() {
        Intent i = new Intent(this, HelpActivity.class);
        startActivity(i);
    }





    public void OnSubmitting (View view) {
        Log.d(TAG, "2");

        Intent intent = new Intent(this, ThankActivity.class);
        Log.d(TAG, "3");
//        EditText editText = (EditText) findViewById(R.id.edit_message);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        Log.d(TAG, "4");
    }


}