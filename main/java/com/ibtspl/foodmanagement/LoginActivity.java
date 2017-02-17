package com.ibtspl.foodmanagement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//import com.google.android.gms.tasks.OnCompleteListener;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

//public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "EmailPassword";
    //  private TextView mStatusTextView;

    //    private EditText mNameField;
    private EditText mEmailField;
    private EditText mPasswordField;
    String userid;
    private DatabaseReference ref;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    // [START declare_auth_listener]
    private FirebaseAuth.AuthStateListener mAuthListener;
    // [END declare_auth_listener]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ref = FirebaseDatabase.getInstance().getReference("users");

        // Views
//        mNameField = (EditText) findViewById(R.id.edit_name);
        mEmailField = (EditText) findViewById(R.id.edit_email);
        mPasswordField = (EditText) findViewById(R.id.edit_password);

        // Buttons
        Button fab = (Button) findViewById(R.id.edit_login);
        if (fab != null)

            fab.setOnClickListener(this);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
        // [START auth_state_listener]
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // [START_EXCLUDE]
//                updateUI(user);
//                // [END_EXCLUDE]
            }
        };
        // [END auth_state_listener]

    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    // [END on_start_add_listener]

    // [START on_stop_remove_listener]
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    // [END on_stop_remove_listener]
    private void signIn(String mEmailField, String mPasswordField ) {
        if (!validateForm()) {
            return;
        }
        Log.d(TAG, "signIn:" + mEmailField);
        // [START sign_in_with_email]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            String uid = user.getUid();
        }
        mAuth.signInWithEmailAndPassword(mEmailField,mPasswordField )
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {

                            //Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                           FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            userid = user.getUid();
                            ValueEventListener adminuser = new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Log.d(TAG, "i am in");

                                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                                        Log.d(TAG, "i am in too");
                                        //ref.child(userid).child("status").setValue("online");

                                        if (child.getValue().equals(userid)) {
                                            Intent myintent = new Intent(LoginActivity.this, Admin.class);
                                            startActivity(myintent);
                                        }
                                            else
                                            {
                                                Intent myintent = new Intent(LoginActivity.this, MenuActivity.class);
                                                startActivity(myintent);
                                            }
                                        }
                                    }




                                 @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    // Getting Post failed, log a message
                                    Log.w(TAG, "loadCount:onCancelled", databaseError.toException());
                                    // ...

                                }
                            };
                            ref.addValueEventListener(adminuser);
                            DatabaseReference adminRef = ref.child(userid);
                            Query query=ref.orderByKey().equalTo(userid);
//                            if (query!=null)
//                            {
//                            Intent myintent = new Intent(LoginActivity.this, Admin.class);
//                            startActivity(myintent);
//                            }
//                            else
//                            {
//                                Intent myintent = new Intent(LoginActivity.this, MenuActivity.class);
//                                startActivity(myintent);
//                            }
                        }
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:onFailure:" + task.isSuccessful());
                            Log.w(TAG, "signInWithEmail", task.getException());

                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
//                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }
    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.edit_register:
//                createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
//                break;
            case R.id.edit_login:
                signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
                break;
//            case R.id.sign_out_button:
//                signOut();
//                break;
        }
    }
}



