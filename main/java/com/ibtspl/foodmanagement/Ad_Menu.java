package com.ibtspl.foodmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ViewFlipper;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class Ad_Menu extends AppCompatActivity {
    private EditText b1;
    private EditText b2,l1,l2,l3,l4,l5,s1,s2,s3,s4;
    private Button submit,submit1,submit2;
    private Firebase ref;
    private DatabaseReference ref1,ref2,ref3;
    String date;
    private ViewFlipper viewFlipper;
    private float lastX;
    private static final String TAG="here";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            Log.d(TAG,"eeeeeeeee");
               date=bundle.getString("message");}
        Log.d(TAG,date);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad__menu);
        Firebase.setAndroidContext(this);
        viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
        ref = new Firebase("https://food-6ec31.firebaseio.com");
        ref1 = FirebaseDatabase.getInstance().getReference("Breakfast");
        ref2 = FirebaseDatabase.getInstance().getReference("Lunch");
        ref3 = FirebaseDatabase.getInstance().getReference("Supper");

        b1 = (EditText) findViewById(R.id.b1);
        b2 = (EditText) findViewById(R.id.b2);
        l1= (EditText) findViewById(R.id.l1);
        l2= (EditText) findViewById(R.id.l2);
        l3= (EditText) findViewById(R.id.l3);
        l4= (EditText) findViewById(R.id.l4);
        l5= (EditText) findViewById(R.id.l5);
        s1= (EditText) findViewById(R.id.s1);
        s2= (EditText) findViewById(R.id.s2);
        s3= (EditText) findViewById(R.id.s3);
        s4=(EditText) findViewById(R.id.s4);
        submit = (Button) findViewById(R.id.submit);
        submit1 = (Button) findViewById(R.id.submit1);
        submit2 = (Button) findViewById(R.id.submit2);

        if(date!=null) {
            getBreakfastItem(date.toString());
            getLunchItem(date.toString());
            getSupperItem(date.toString());

        }



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String B1 = b1.getText().toString();
                String B2 = b2.getText().toString();

                Break bf=new Break(B1, B2);
                if(date!=null)
                ref.child("Breakfast").child(date).setValue(bf);
//
//                Intent intent = new Intent(Ad_Menu.this, Ad_Menu2.class);
//                intent.putExtra("message",date);
//                startActivity(intent);
            }
        });
        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String L1 = l1.getText().toString();
                String L2 = l2.getText().toString();
                String L3 = l3.getText().toString();
                String L4 = l4.getText().toString();
                String L5 = l5.getText().toString();
                Lunch l=new Lunch(L1,L2,L3,L4,L5);
                if(date!=null)
                ref.child("Lunch").child(date).setValue(l);
//                Intent intent = new Intent(Ad_Menu2.this, Ad_menu3.class);
//                intent.putExtra("message",date);
//                startActivity(intent);
            }
        });
        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String S1 = s1.getText().toString();
                String S2 = s2.getText().toString();
                String S3 = s3.getText().toString();
                String S4 = s4.getText().toString();
                Supper s=new Supper(S1,S2,S3,S4);
                if(date!=null)
                ref.child("Supper").child(date).setValue(s);
                Intent intent = new Intent(Ad_Menu.this, Admin.class);
                startActivity(intent);

            }
        });

    }

    private void getBreakfastItem(String date) {
        Query query = ref1.orderByKey().equalTo(date);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Break breakfast = dataSnapshot.getValue(Break.class);
                b1.setText(breakfast.getItem1());
                b2.setText(breakfast.getItem2());

            }

            //
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                final String bre[] = new String[2];
                Break breakfast = dataSnapshot.getValue(Break.class);
                b1.setText(breakfast.getItem1());
                b2.setText(breakfast.getItem2());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }
        });
    }


    private void getLunchItem(String date) {
        Query query = ref2.orderByKey().equalTo(date);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Lunch lunch = dataSnapshot.getValue(Lunch.class);
                l1.setText(lunch.getItem1());
                l2.setText(lunch.getItem2());
                l3.setText(lunch.getItem3());
                l4.setText(lunch.getItem4());
                l5.setText(lunch.getItem5());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                Lunch lunch = dataSnapshot.getValue(Lunch.class);
                l1.setText(lunch.getItem1());
                l2.setText(lunch.getItem2());
                l3.setText(lunch.getItem3());
                l4.setText(lunch.getItem4());
                l5.setText(lunch.getItem5());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }
        });
    }

    private void getSupperItem(String date) {
        Query query = ref3.orderByKey().equalTo(date);
//
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Supper supper = dataSnapshot.getValue(Supper.class);
                s1.setText(supper.getItem1());
                s2.setText(supper.getItem2());
                s3.setText(supper.getItem3());
                s4.setText(supper.getItem4());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Supper supper = dataSnapshot.getValue(Supper.class);
                s1.setText(supper.getItem1());
                s2.setText(supper.getItem2());
                s3.setText(supper.getItem3());
                s4.setText(supper.getItem4());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }
        });

    }












    // Method to handle touch event like left to right swap and right to left swap
    public boolean onTouchEvent(MotionEvent touchevent)
    {
        switch (touchevent.getAction())
        {
            // when user first touches the screen to swap
            case MotionEvent.ACTION_DOWN:
            {
                lastX = touchevent.getX();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                float currentX = touchevent.getX();

                // if left to right swipe on screen
                if (lastX < currentX)
                {

                    // If no more View/Child to flip
                    if (viewFlipper.getDisplayedChild() == 0)
                        break;

                    // set the required Animation type to ViewFlipper
                    // The Next screen will come in form Left and current Screen will go OUT from Right
                    viewFlipper.setInAnimation(this, R.anim.in_from_left);
                    viewFlipper.setOutAnimation(this, R.anim.out_to_right);
                    // Show the next Screen
                    viewFlipper.showNext();
                }

                // if right to left swipe on screen
                if (lastX > currentX)
                {
                    if (viewFlipper.getDisplayedChild() == 1)
                        break;
                    // set the required Animation type to ViewFlipper
                    // The Next screen will come in form Right and current Screen will go OUT from Left
                    viewFlipper.setInAnimation(this, R.anim.in_from_right);
                    viewFlipper.setOutAnimation(this, R.anim.out_to_left);
                    // Show The Previous Screen
                    viewFlipper.showPrevious();
                }
                break;
            }
        }
        return false;
    }
}
