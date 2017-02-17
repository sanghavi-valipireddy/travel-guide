package com.ibtspl.foodmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        DatabaseReference myRef = database.getReference("Test");
//        myRef.setValue("Hello, World!");
        databaseReference.child("Test").child("Test2").setValue("Value3");
        databaseReference.child("Test").child("Test3").setValue("Value3");
//        myRef.child("Test3").setValue("Sample set value");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null)
        {
            Intent intent = new Intent(this,MenuActivity.class);
            startActivity(intent);
        }
    }
    /** Called when the user clicks the Login button */
    public void LoggingIn(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
//        EditText editText = (EditText) findViewById(R.id.edit_message);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
//

    }
    /** Called when the user clicks the Register button */
    public void Registration(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
//        EditText editText = (EditText) findViewById(R.id.edit_message);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);


    }
}