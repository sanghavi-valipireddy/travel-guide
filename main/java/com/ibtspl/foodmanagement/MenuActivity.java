package com.ibtspl.foodmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class MenuActivity extends AppCompatActivity {
    private static final String TAG = "DATE";

    private static final String TAG1 = "BREAKFAST_ITEMS";
    private static final String TAG2 = "LUNCH_ITEMS";
    private static final String TAG3 = "SUPPER_ITEMS";
    private DatabaseReference mDatabase;
    private DatabaseReference ref1, ref2, ref3, ref4;
    private int dd, mm, yy;
    String b1, b2, l1, l2, l3, l4, l5, s1, s2, s3, s4, bf, l, s;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    Button submit;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    String userid;
    private CheckBox breakfast;
    private CheckBox lunch;
    private CheckBox supper;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        Firebase.setAndroidContext(this);
        ref1 = FirebaseDatabase.getInstance().getReference("Count");
        ref4 = FirebaseDatabase.getInstance().getReference("Breakfast");
        ref3 = FirebaseDatabase.getInstance().getReference("Lunch");
        ref2 = FirebaseDatabase.getInstance().getReference("Supper");
        TextView SItem = (TextView) findViewById(R.id.textsitems);
        final Calendar cal = Calendar.getInstance();
        dd = cal.get(Calendar.DAY_OF_MONTH);
        mm = cal.get(Calendar.MONTH);
        yy = cal.get(Calendar.YEAR);
        StringBuilder date1 = new StringBuilder().append(dd).append(" ").append(mm + 1).append(" ").append(yy);
        date=date1.toString();
        getBreakfastItem(date);
        getLunchItem(date);
        getSupperItem(date);
        setContentView(R.layout.activity_menu);
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        listDataHeader.add("BREAKFAST");
        listDataHeader.add("LUNCH");
        listDataHeader.add("SUPPER");
        Log.d(TAG1, date.toString());
        Log.d(TAG2, "three");
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);
        submit = (Button) findViewById(R.id.go);
        breakfast = (CheckBox) findViewById(R.id.breakfast);
        lunch = (CheckBox) findViewById(R.id.lunch);
        supper = (CheckBox) findViewById(R.id.supper);
//        if (submit != null) {
//            Log.d(TAG2, "hi this is deepika");

//            submit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(MenuActivity.this, Count.class);
//                    startActivity(intent);
//
//                }
//            });
        if (submit != null) {
            submit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Log.d(TAG1, date);
                    if (breakfast!=null && breakfast.isChecked()) {
                        Log.d(TAG, "1");
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        Log.d(TAG, "5");
                        userid = user.getUid();
                        ref1.child(date).child("breakfast").child(userid).setValue("1");
                    } else {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        userid = user.getUid();
                        ref1.child(date).child("breakfast").child(userid).setValue("0");
                    }

                    if (breakfast!=null && lunch.isChecked() ) {
                        Log.d(TAG, "1");
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        Log.d(TAG, "5");
                        userid = user.getUid();
                        ref1.child(date).child("lunch").child(userid).setValue("1");

                    } else {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        userid = user.getUid();
                        ref1.child(date).child("lunch").child(userid).setValue("0");
                    }
                    if (breakfast!=null && supper.isChecked()) {
                        Log.d(TAG, "1");
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        Log.d(TAG, "5");
                        userid = user.getUid();
                        ref1.child(date).child("supper").child(userid).setValue("1");
                    } else {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        userid = user.getUid();
                        ref1.child(date).child("supper").child(userid).setValue("0");
                    }


                    OnSubmitting(view);


                }

            });


        }
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

//
    private void signOut() {
        mAuth.signOut();
        Intent i = new Intent(MenuActivity.this, LoginActivity.class);
        startActivity(i);

    }

    private void OnHelp() {
        Intent i = new Intent(MenuActivity.this, HelpActivity.class);
        startActivity(i);
    }


    // Header, Child data
    private void getBreakfastItem(String date) {
        Query query = ref4.orderByKey().equalTo(date);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Break breakfast = dataSnapshot.getValue(Break.class);
                b1 = breakfast.getItem1();
                b2 = breakfast.getItem2();
                List<String> breakf = new ArrayList<String>();
                breakf.add(b1);
                breakf.add(b2);
                listDataChild.put(listDataHeader.get(0), breakf);
////                StringBuilder bfitems =(new StringBuilder().append("item1").append(" ").append("item2"));
//                TextView BfItem =(TextView) findViewById(R.id.textbfitems);
//                assert BfItem != null;
//                StringBuilder breakitems =new StringBuilder().append(item1).append(" ").append(item2);
//                Log.d(TAG1,breakitems.toString());
//                BfItem.setText(breakitems.toString());
//
//               public Item1.getValue();
            }

            //
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                final String bre[] = new String[2];
                Break breakfast = dataSnapshot.getValue(Break.class);
                b1 = breakfast.getItem1();
                b2 = breakfast.getItem2();
                breakfast.setItem1(b1);
                breakfast.setItem1(b2);
                List<String> breakf = new ArrayList<String>();
                breakf.add(b1);
                breakf.add(b2);
                listDataChild.put(listDataHeader.get(0), breakf);

//                TextView BfItem =(TextView) findViewById(R.id.textbfitems);
//                assert BfItem != null;
//                StringBuilder breakitems =new StringBuilder().append(item1).append(" ").append(item2);
//                Log.d(TAG1,breakitems.toString());
//                BfItem.setText(breakitems.toString());
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
        Query query = ref3.orderByKey().equalTo(date);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Lunch lunch = dataSnapshot.getValue(Lunch.class);
                l1 = lunch.getItem1();
                l2 = lunch.getItem2();
                l3 = lunch.getItem3();
                l4 = lunch.getItem4();
                l5 = lunch.getItem5();
                List<String> lun = new ArrayList<String>();
                lun.add(l1);
                lun.add(l2);
                lun.add(l3);
                lun.add(l4);
                lun.add(l5);
                listDataChild.put(listDataHeader.get(1), lun);
                Log.d(TAG2, lunch.getItem1());
                Log.d(TAG2, lunch.getItem2());
                Log.d(TAG2, lunch.getItem3());
                Log.d(TAG2, lunch.getItem4());
                Log.d(TAG2, lunch.getItem5());
//                TextView LItem =(TextView) findViewById(R.id.textlitems);
//                assert LItem != null;
//                StringBuilder lunchitems = new StringBuilder().append(item1).append(" ").append(item2).append(" ").append(item3).append(" ").append(item4).append(" ").append(item5);
//                Log.d(TAG2,lunchitems.toString());
//                LItem.setText(lunchitems.toString());
//

//               public Item1.getValue();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                Lunch lunch = dataSnapshot.getValue(Lunch.class);
                l1 = lunch.getItem1();
                l2 = lunch.getItem2();
                l3 = lunch.getItem3();
                l4 = lunch.getItem4();
                l5 = lunch.getItem5();
                List<String> lun = new ArrayList<String>();
                lun.add(l1);
                lun.add(l2);
                lun.add(l3);
                lun.add(l4);
                lun.add(l5);
                listDataChild.put(listDataHeader.get(1), lun);
                Log.d(TAG2, "hello");
                Log.d(TAG2, lunch.getItem1());
                Log.d(TAG2, lunch.getItem2());
                Log.d(TAG2, lunch.getItem3());
                Log.d(TAG2, lunch.getItem4());
                Log.d(TAG2, lunch.getItem5());
//                TextView LItem =(TextView) findViewById(R.id.textlitems);
//                assert LItem != null;
//                StringBuilder lunchitems = new StringBuilder().append(item1).append(" ").append(item2).append(" ").append(item3).append(" ").append(item4).append(" ").append(item5);
//                Log.d(TAG2,lunchitems.toString());
//                LItem.setText(lunchitems.toString());
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
        Query query = ref2.orderByKey().equalTo(date);
//        query.child("item1");
//        ChildEventListener childEventListener = new ChildEventListener()
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Supper supper = dataSnapshot.getValue(Supper.class);
                s1 = supper.getItem1();
                s2 = supper.getItem2();
                s3 = supper.getItem3();
                s4 = supper.getItem4();
                List<String> supp = new ArrayList<String>();
                supp.add(s1);
                supp.add(s2);
                supp.add(s3);
                supp.add(s4);
                listDataChild.put(listDataHeader.get(2), supp);
                Log.d(TAG3, "hello");
                Log.d(TAG3, supper.getItem1());
                Log.d(TAG3, supper.getItem2());
                Log.d(TAG3, supper.getItem3());
                Log.d(TAG3, supper.getItem4());
//                TextView SItem =(TextView) findViewById(R.id.textsitems);
//                assert SItem != null;
//                StringBuilder supperitems = new StringBuilder().append(item1).append(" ").append(item2).append(" ").append(item3).append(" ").append(item4);
//                Log.d(TAG3,supperitems.toString());
//                SItem.setText(supperitems.toString());

//               public Item1.getValue();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Supper supper = dataSnapshot.getValue(Supper.class);
//                 String item1 = breakfast.getItem1();
//                 String item2 = breakfast.getItem2();
                s1 = supper.getItem1();
                s2 = supper.getItem2();
                s3 = supper.getItem3();
                s4 = supper.getItem4();
                List<String> supp = new ArrayList<String>();
                supp.add(s1);
                supp.add(s2);
                supp.add(s3);
                supp.add(s4);
                listDataChild.put(listDataHeader.get(2), supp);
                Log.d(TAG3, "hello");
                Log.d(TAG3, supper.getItem1());
                Log.d(TAG3, supper.getItem2());
                Log.d(TAG3, supper.getItem3());
                Log.d(TAG3, supper.getItem4());
//                TextView SItem =(TextView) findViewById(R.id.textsitems);
//                assert SItem != null;
//                StringBuilder supperitems = new StringBuilder().append(item1).append(" ").append(item2).append(" ").append(item3).append(" ").append(item4);
//                Log.d(TAG3,supperitems.toString());
//                SItem.setText(supperitems.toString());

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
        //return s1;
    }
}

//    private void prepareListData(String b1,String b2,String l1,String l2,String l3,String l4,String l5,String s1,String s2,String s3,String s4) {
//        String sa="hi";
//        listDataHeader = new ArrayList<String>();
//        listDataChild = new HashMap<String, List<String>>();
//        Log.d(TAG3, "hi this is testing");
////        Log.d(TAG3, b1);
//        // Adding child data
//        listDataHeader.add("BREAKFAST");
//        listDataHeader.add("LUNCH");
//        listDataHeader.add("SUPPER");
//
//        // Adding child data
//        List<String> breakfast = new ArrayList<String>();
//        breakfast.add(sa);
//        breakfast.add(b1);
//
//        List<String> lunch = new ArrayList<String>();
//        lunch.add(l1);
//        lunch.add(l2);
//        lunch.add(l3);
//        lunch.add(l4);
//        lunch.add(l5);
//
//        List<String> supper = new ArrayList<String>();
//        supper.add(s1);
//        supper.add(s2);
//        supper.add(s3);
//        supper.add(s4);
//
//        listDataChild.put(listDataHeader.get(0), breakfast); // Header, Child data
//        listDataChild.put(listDataHeader.get(1), lunch);
//        listDataChild.put(listDataHeader.get(2), supper);
//    }
////    public boolean appbarclick(View view) {
////        //        @Override
////        public boolean onOptionsItemSelected (MenuItem item){
////            switch (item.getItemId()) {
////                case R.id.action_settings:
////                    // User chose the "Settings" item, show the app settings UI...
////                    return true;
////
////                case R.id.action_favorite:
////                    // User chose the "Favorite" action, mark the current item
////                    // as a favorite...
////                    return true;
////
////                default:
////                    // If we got here, the user's action was not recognized.
////                    // Invoke the superclass to handle it.
////                    return super.onOptionsItemSelected(item);
////
////            }j
////        }
////    }
//
//    /** Called when the user clicks the Give your Count button button */
////    public void GivingCount (View view) {
////        Intent intent = new Intent(this, Count.class);
//////        EditText editText = (EditText) findViewById(R.id.edit_message);
//////        String message = editText.getText().toString();
//////        intent.putExtra(EXTRA_MESSAGE, message);
////        startActivity(intent);
//
//
////}
//}
//
//
