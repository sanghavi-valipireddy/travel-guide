package com.ibtspl.foodmanagement;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DateFragment.OnDateSelected} interface
 * to handle interaction events.
 * Use the {@link DateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DateFragment extends Fragment {
    DatePicker picker;
    Button displayDate;
    TextView textview1;
    private static final String DATE = "dates";
    private static final String TAG="date4";
    String date;


    private OnDateSelected mListener1;

    public DateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment DateFragment.
     */
    // TODO: Rename and change types and number of parameters


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d(TAG,"date8");

        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG,"date9");

        //  setContentView(R.layout.activity_date);
            View view =inflater.inflate(R.layout.fragment_date, container, false);


            textview1=(TextView)view.findViewById(R.id.textView1);
            picker=(DatePicker)view.findViewById(R.id.datePicker1);
            displayDate=(Button)view.findViewById(R.id.date_button);
            if(textview1!=null)
            textview1.setText(getCurrentDate());
        Log.d(TAG,"date10");

        if(displayDate!=null)
            displayDate.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Log.d(TAG,"date19");

                    textview1.setText(getCurrentDate());
                  //  String date=textview1.getText().toString();
//                    Intent intent=new Intent(DateActivity.this,Ad_Menu.class);
//                    intent.putExtra("message",date);
//                    startActivity(intent);
                    Log.d(TAG,"date11");

                    if(mListener1!=null)
                     mListener1.onDateSelected(getCurrentDate());
                    Log.d(TAG,"date12");
                    if(date!=null)
                    onButtonPressed(date);

                    Toast.makeText(getActivity(), "Item: " + "date", Toast.LENGTH_SHORT).show();
                   // void onButtonPressed();

                }

            });



        // Inflate the layout for this fragment
       return view;
    }
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//       // ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.options, android.R.layout.simple_list_item_1);
//        //setListAdapter(adapter);
//        displayDate.onDateSelected(this);
//    }

     // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String date) {
        Log.d(TAG,"date13");

        if (mListener1 != null) {
            Log.d(TAG,"date14");
            if(mListener1!=null)
            mListener1.onDateSelected(date);
            Log.d(TAG,"date15");

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDateSelected) {
           // mListener1= (OnDateSelected) getActivity();
            mListener1 = (Admin)getActivity();

            //mListener1 = (OnDateSelected) context;
       } else {
           throw new RuntimeException(context.toString()
                    + " must implement OnDateSelected");
       }
    }
    public static DateFragment newInstance(String date) {
        Log.d(TAG,"date7");
        DateFragment fragment = new DateFragment();
        Bundle args = new Bundle();
        args.putString(DATE, date);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public void setmListener1(OnDateSelected listener){
        this.mListener1 =listener;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener1 = null;
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

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, String date, long id) {
//        if(mListener1!=null)
//            mListener1.onDateSelected(date);
//        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
//    }


    public interface OnDateSelected {
        // TODO: Update argument type and name
        void onDateSelected(String date);
    }
    public String getCurrentDate(){
        Log.d(TAG,"date16");


        StringBuilder builder=new StringBuilder();
            if(picker!=null)
            builder.append(picker.getDayOfMonth() + " " + (picker.getMonth() + 1) + " " + picker.getYear());
            //builder.append((picker.getMonth() + 1)+"/"+picker.getDayOfMonth()+"/"+picker.getYear());//month is 0 based
            //builder.append(picker.getDayOfMonth()+"/");
            //builder.append(picker.getYear());


        Log.d(TAG,"date17");


        return builder.toString();
    }


//    displayDate.setOnClickListener(new View.OnClickListener() {
//        public void onClick(View v) {
//
//            Fragment frag = new YourFragment(Data yourdata);
//
//
//            FragmentTransaction ft = getFragmentManager().beginTransaction();
//            ft.replace(R.id.fragment_container, frag);
//            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//            ft.addToBackStack(null);
//            ft.commit();
//        }
//    });
}
