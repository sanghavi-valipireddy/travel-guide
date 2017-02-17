package com.ibtspl.foodmanagement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CountDisplayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CountDisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CountDisplayFragment extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
   // private static final String POSITION = "itemNo";
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
//    private String mParam2;

    private DatabaseReference ref;
    private static final String TAG = "BreakFastCOUNT";
    TextView BFCount,LCount,DCount;
    String date1;
    private int dd, mm, yy;


    private OnFragmentInteractionListener mListener;

    public CountDisplayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment CountDisplayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CountDisplayFragment newInstance() {
        final CountDisplayFragment fragment = new CountDisplayFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
       // args.putString(POSITION, itemNo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

          //  mParam1 = getArguments().getInt(POSITION);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_count_display, container, false);
        View view = inflater.inflate(R.layout.fragment_count_display, container, false);
        ref = FirebaseDatabase.getInstance().getReference("Count");
        StringBuilder date;
        final Calendar cal = Calendar.getInstance();
        dd = cal.get(Calendar.DAY_OF_MONTH);
        mm = cal.get(Calendar.MONTH);
        yy = cal.get(Calendar.YEAR);
        date = new StringBuilder().append(dd).append(" ").append(mm + 1).append(" ").append(yy);
        date1 = date.toString();
        GetCount(date1);
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
      //  if (context instanceof OnFragmentInteractionListener) {
        //    mListener = (OnFragmentInteractionListener) context;
        //} else {
          //  throw new RuntimeException(context.toString()
            //        + " must implement OnFragmentInteractionListener");
        //}
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
                assert getActivity()!=null;
                TextView BFCount =(TextView) getActivity().findViewById(R.id.textbfitems);
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
                TextView LCount =(TextView) getActivity().findViewById(R.id.textlitems);
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
                TextView SCount =(TextView) getActivity().findViewById(R.id.textsitems);
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

