package com.ibtspl.foodmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class ThankActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
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





    public void GivingCount (View view) {
        Intent intent = new Intent(this, Count.class);
//        EditText editText = (EditText) findViewById(R.id.edit_message);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);


    }
}
