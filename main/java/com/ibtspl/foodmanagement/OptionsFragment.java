//package com.ibtspl.foodmanagement;
//
//import android.content.Context;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//
///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link OptionsFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link OptionsFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class OptionsFragment extends Fragment {
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    private OnFragmentInteractionListener mListener;
//
//    public OptionsFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment OptionsFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static OptionsFragment newInstance(String param1, String param2) {
//        OptionsFragment fragment = new OptionsFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_options, container, false);
//    }
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
//}



package com.ibtspl.foodmanagement;

import android.annotation.SuppressLint;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.support.v4.app.Fragment;
//import android.app.Activity;



//public class MyListFragment extends ListFragment implements OnItemClickListener {
public class OptionsFragment extends ListFragment implements OnItemClickListener {
    private static final String TAG = "oops";
    private static final String POSITION1 = "itemNo";
   private OnOptionSelected mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG,"hey1");
        if (context instanceof OnOptionSelected) {
            mListener = (Admin)getActivity();
            Log.d(TAG,"hey");
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnOptionSelected");
        }
    }
    public static OptionsFragment newInstance(String itemNo) {
        final OptionsFragment fragment1 = new OptionsFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
        args.putString(POSITION1, itemNo);
        fragment1.setArguments(args);
        return fragment1;
    }
    public void setmListener(OnOptionSelected listener){
        this.mListener =listener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.list_fragment, container, false);
        View view = inflater.inflate(R.layout.fragment_options, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.options, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(mListener!=null)
        mListener.onOptionSelected(position);
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }
    public interface OnOptionSelected{
        void onOptionSelected(int ItemNo);

    }}
//    @Override
//    public void onDestroyView(){}
//    ViewGroup mContainer =(ViewGroup)getActivity().findViewById(R.id.fragment_container);
//    mContainer.removeAllViews();
//super.onDestroyView();}