package com.ibtspl.foodmanagement;

import android.content.Context;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ViewFlipper;

import com.firebase.client.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UpdateMenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UpdateMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateMenuFragment extends Fragment implements GestureDetector.OnGestureListener
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
    private EditText b1;
    private EditText b2,l1,l2,l3,l4,l5,s1,s2,s3,s4;
    private Button submit,submit1,submit2;
    //private DatabaseReference ref;
    private DatabaseReference ref1,ref2,ref3;
    private  String date;
    private ViewFlipper viewFlipper;
    private float lastX;
    private GestureDetector detecture;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private DatabaseReference ref;

    public UpdateMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateMenuFragment newInstance(String date) {
        UpdateMenuFragment fragment = new UpdateMenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, date);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.fragment_update_menu);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_update_menu, container, false);

        view.setOnTouchListener(new View.OnTouchListener() {

//
            public boolean onTouch(View view, MotionEvent event) {
                if(event!=null)
               detecture.onTouchEvent(event);
//
//                if(event.getAction() == MotionEvent.ACTION_MOVE){
//                    //do something
//                }
                return true;
           }
        });

        Bundle bundle=getActivity().getIntent().getExtras();
        if(date!=null)
        date=bundle.getString("message");
        super.onCreate(savedInstanceState);
      //  View view =inflater.inflate(R.layout.fragment_update_menu, container, false);

        //Firebase.setAndroidContext(this);
        viewFlipper = (ViewFlipper) getActivity().findViewById(R.id.view_flipper);
      //  ref = new Firebase("https://food-6ec31.firebaseio.com");


        ref1 = FirebaseDatabase.getInstance().getReference("Breakfast");
        ref2 = FirebaseDatabase.getInstance().getReference("Lunch");
        ref3 = FirebaseDatabase.getInstance().getReference("Supper");

        b1 = (EditText)view.findViewById(R.id.b1);
        b2 = (EditText) getActivity().findViewById(R.id.b2);
        l1= (EditText) getActivity().findViewById(R.id.l1);
        l2= (EditText)getActivity(). findViewById(R.id.l2);
        l3= (EditText) getActivity().findViewById(R.id.l3);
        l4= (EditText) getActivity().findViewById(R.id.l4);
        s1= (EditText)getActivity().findViewById(R.id.s1);
        s2= (EditText) getActivity().findViewById(R.id.s2);
        s3= (EditText) getActivity().findViewById(R.id.s3);
        s4=(EditText) getActivity().findViewById(R.id.s4);
        submit = (Button) getActivity().findViewById(R.id.submit);
        submit1 = (Button) getActivity().findViewById(R.id.submit1);
        submit2 = (Button) getActivity().findViewById(R.id.submit2);

        if(date!=null) {
            getBreakfastItem(date.toString());
            getLunchItem(date.toString());
            getSupperItem(date.toString());
        }


       // onTouchEvent();


        if(submit!=null)
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String B1 = b1.getText().toString();
                String B2 = b2.getText().toString();

                Break bf=new Break(B1, B2);
                ref.child("Breakfast").child(date).setValue(bf);
//
//                Intent intent = new Intent(Ad_Menu.this, Ad_Menu2.class);
//                intent.putExtra("message",date);
//                startActivity(intent);
            }
        });
        if(submit1!=null)
        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String L1 = l1.getText().toString();
                String L2 = l2.getText().toString();
                String L3 = l3.getText().toString();
                String L4 = l4.getText().toString();
                String L5 = l5.getText().toString();
                Lunch l=new Lunch(L1,L2,L3,L4,L5);
                ref.child("Lunch").child(date).setValue(l);
//                Intent intent = new Intent(Ad_Menu2.this, Ad_menu3.class);
//                intent.putExtra("message",date);
//                startActivity(intent);
            }
        });
        if(submit2!=null)
        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String S1 = s1.getText().toString();
                String S2 = s2.getText().toString();
                String S3 = s3.getText().toString();
                String S4 = s4.getText().toString();
                Supper s=new Supper(S1,S2,S3,S4);
                ref.child("Supper").child(date).setValue(s);
//                Intent intent = new Intent(Uthis, Admin.class);
//                startActivity(intent);

            }
        });



        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
                    if(viewFlipper!=null){
                    // If no more View/Child to flip
                    if (viewFlipper.getDisplayedChild() == 0)
                        break;

                    // set the required Animation type to ViewFlipper
                    // The Next screen will come in form Left and current Screen will go OUT from Right
                    viewFlipper.setInAnimation(getActivity(), R.anim.in_from_left);
                    viewFlipper.setOutAnimation(getActivity(), R.anim.out_to_right);
                    // Show the next Screen
                    viewFlipper.showNext();}
                }

                // if right to left swipe on screen
                if (lastX > currentX)
                {
                    if(viewFlipper!=null){
                    if (viewFlipper.getDisplayedChild() == 1)
                        break;
                    // set the required Animation type to ViewFlipper
                    // The Next screen will come in form Right and current Screen will go OUT from Left
                    if(viewFlipper!=null)
                    {viewFlipper.setInAnimation(getActivity(), R.anim.in_from_right);
                    viewFlipper.setOutAnimation(getActivity(), R.anim.out_to_left);
                    // Show The Previous Screen
                    viewFlipper.showPrevious();}}
                }
                break;
            }
        }
        return false;
    }

}

