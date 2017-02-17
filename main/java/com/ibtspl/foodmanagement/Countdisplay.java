package com.ibtspl.foodmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Countdisplay extends AppCompatActivity {
    private DatabaseReference ref;
    private static final String TAG = "BreakFastCOUNT";
    TextView BFCount,LCount,DCount;
    String date1;
    private int dd, mm, yy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdisplay);
        ref = FirebaseDatabase.getInstance().getReference("Count");
        StringBuilder date;
        final Calendar cal = Calendar.getInstance();
        dd = cal.get(Calendar.DAY_OF_MONTH);
        mm = cal.get(Calendar.MONTH);
        yy = cal.get(Calendar.YEAR);
        date = new StringBuilder().append(dd).append(" ").append(mm + 1).append(" ").append(yy);
        date1 = date.toString();
        GetCount(date1);
    }
    public void GetCount(String date1) {

        ValueEventListener countchange1 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG,"i am in");
                int bfcnt=0;
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.d(TAG,"i am in too");

                    if (child.getValue().equals("1")) {
                        Log.d(TAG,"i am in to this also");
                        bfcnt=bfcnt +1;
                    }
                }
                String num1 = Integer.toString(bfcnt);
                Log.d(TAG,num1);
                TextView BFCount =(TextView) findViewById(R.id.textbfitems);
                assert BFCount != null;
                BFCount.setText(num1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadCount:onCancelled", databaseError.toException());
                // ...

            }
        };
        ref.child(date1).child("breakfast").addValueEventListener(countchange1);

        ValueEventListener countchange2 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG,"i am in");
                int lcnt=0;
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.d(TAG,"i am in too");

                    if (child.getValue().equals("1")) {
                        Log.d(TAG,"i am in to this also");
                        lcnt=lcnt +1;
                    }
                }
                String num2 = Integer.toString(lcnt);
                Log.d(TAG,num2);
                TextView LCount =(TextView) findViewById(R.id.textlitems);
                assert LCount != null;
                LCount.setText(num2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadCount:onCancelled", databaseError.toException());
                // ...

            }
        };
        ref.child(date1).child("lunch").addValueEventListener(countchange2);

        ValueEventListener countchange3 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG,"i am in");
                int scnt=0;
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.d(TAG,"i am in too");

                    if (child.getValue().equals("1")) {
                        Log.d(TAG,"i am in to this also");
                        scnt=scnt +1;
                    }
                }
                String num3 = Integer.toString(scnt);
                Log.d(TAG,num3);
                TextView SCount =(TextView) findViewById(R.id.textsitems);
                assert SCount != null;
                SCount.setText(num3);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadCount:onCancelled", databaseError.toException());
                // ...

            }
        };
        ref.child(date1).child("supper").addValueEventListener(countchange3);

    }



}

