//package com.ibtspl.foodmanagement;
//
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//
//import static com.ibtspl.foodmanagement.R.id.options_fragment;
//
//public class Admin extends AppCompatActivity implements CountdisplayActivity.OpitonsFragment.OnOptionSelectedListener {
//    private static final String TAG = "sss";
////    private Button count;
////    private Button menu;
////    private Button give_count;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_optionsfragment);
//        setContentView(R.layout.fragment_countdisplayfragment);
//        // Check that the activity is using the layout version with
//        // the fragment_container FrameLayout
//        Log.d(TAG,"ss1");
//                if (findViewById(options_fragment) != null) {
//                    Log.d(TAG,"ss2");
//                    // However, if we're being restored from a previous state,
//                    // then we don't need to do anything and should return or else
//                    // we co
//                    // uld end up with overlapping fragments.
//                    if (savedInstanceState != null) {
//                        Log.d(TAG,"ss3");
//                        return;
//                    }
//
//                    // Create a new Fragment to be placed in th5 activity layout
//                    CountdisplayActivity.OpitonsFragment firstFragment = new CountdisplayActivity.OpitonsFragment();
//                    Log.d(TAG,"ss4");
//                    // In case this activity was started with special instructions from an
//                    // Intent, pass the Intent's extras to the fragment as arguments
//                    firstFragment.setArguments(getIntent().getExtras());
//
//                    // Add the fragment to the 'fragment_container' FrameLayout
//                    FragmentManager fragmentManager = getSupportFragmentManager();
//                    fragmentManager.beginTransaction().add(R.id.options_fragment,firstFragment,"").commit();
//                }
//        Log.d(TAG,"ss5");
//
//
//
//
//    // Create fragment and give it an argument specifying the article it should show
//    Countdisplayfragment newFragment = new Countdisplayfragment();
//    Bundle args = new Bundle();
//    args.putString(Countdisplayfragment.ARG_POSITION, "");
//    newFragment.setArguments(args);
//        Log.d(TAG,"ss6");
//
//    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//
//// Replace whatever is in the fragment_container view with this fragment,
//// and add the transaction to the back stack so the user can navigate back
//    transaction.replace(options_fragment, newFragment);
//    transaction.addToBackStack(null);
//        Log.d(TAG,"ss7");
//// Commit the transaction
//    transaction.commit();
//        Log.d(TAG,"ss8");
//        }
////        count = (Button) findViewById(R.id.count);
////        menu = (Button) findViewById(R.id.menu1);
////        give_count = (Button) findViewById(R.id.give_count);
////        count.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Intent intent = new Intent(Admin.this, CountdisplayActivity.class);
////                //intent.putExtra("message",date);
////                startActivity(intent);
////            }
////        });
////        menu.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Intent intent = new Intent(Admin.this, DateActivity.class);
////                //intent.putExtra("message",date);
////                startActivity(intent);
////            }
////        });
////        give_count.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Intent intent = new Intent(Admin.this, Count.class);
////                //intent.putExtra("message",date);
////                startActivity(intent);
////            }
////        });
//    public void onCountdisplaySelected(int position) {
//        // The user selected the headline of an article from the HeadlinesFragment
//        // Do something here to display that article
//        Log.d(TAG,"ss9");
//    }
//
//}
//

package com.ibtspl.foodmanagement;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

public class Admin extends AppCompatActivity implements OptionsFragment.OnOptionSelected,DateFragment.OnDateSelected {
    private static final String TAG="here";
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Log.d(TAG,"here5");
        if(savedInstanceState==null)
        {
            Log.d(TAG,"here4");

            OptionsFragment fragment = OptionsFragment.newInstance("");
            fragment.setmListener(this);

            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container,fragment,"optionsfragment")
                    .commit();

        }


    }

    @Override
    public void onOptionSelected(int position)

    {
        Log.d(TAG,"here3");
        if(position==0)
        {
            Log.d(TAG,"here1");
            final CountDisplayFragment countdisplayfragment = CountDisplayFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,countdisplayfragment.newInstance(),"countdisplayfragment")
                    .addToBackStack(null)
                    .commit();
//            FragmentManager fm = getFragmentManager(); // or 'getSupportFragmentManager();'
//            int count = fm.getBackStackEntryCount();
//            for(int i = 0; i < count; ++i) {
//                fm.popBackStack();
//            }


            Log.d(TAG,"here2");
        }


        if(position==2)
        {
            Log.d(TAG,"here10");
            Intent intent = new Intent(this, MenuActivity.class);
//        EditText editText = (EditText) findViewById(R.id.edit_message);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);

//            final MenuDisplayAndCountFragment menudisplayandcountfragment = MenuDisplayAndCountFragment.newInstance();
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fragment_container, menudisplayandcountfragment.newInstance(),"givecountfragment")
//                    .addToBackStack(null)
//                    .commit();
////            FragmentManager fm = getFragmentManager(); // or 'getSupportFragmentManager();'
////            int count = fm.getBackStackEntryCount();
////            for(int i = 0; i < count; ++i) {
////                fm.popBackStack();
////            }


            Log.d(TAG,"here9");
        }
        if(position==1)
        {
            Log.d(TAG,"here1");
           final DateFragment dateFragment = DateFragment.newInstance("");
           dateFragment.setmListener1(this);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,dateFragment.newInstance(""),"countdisplayfragment")
                    .addToBackStack(null)
                    .commit();

//            FragmentManager fm = getFragmentManager(); // or 'getSupportFragmentManager();'
//            int count = fm.getBackStackEntryCount();
//            for(int i = 0; i < count; ++i) {
//                fm.popBackStack();
//            }


            Log.d(TAG,"here2");

        }
        Toast.makeText(this,"hey you selected"+position+"@",Toast.LENGTH_SHORT).show();

        //onDateSelected(date);

    }

    public void onDateSelected(String date1)

    {
        Log.d(TAG,"working1");
        if(date1!=null)
        Log.d(TAG,date1);
        Intent intent1=new Intent(this,Ad_Menu.class);
        intent1.putExtra("message",date1);

        startActivity(intent1);
//
//        final UpdateMenuFragment updatemenufragment = UpdateMenuFragment.newInstance(date1);
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragment_container,updatemenufragment.newInstance(date1),"updatemenufragment")
//                .addToBackStack(null)
//                .commit();
        Log.d(TAG,"working2");


    }
}

