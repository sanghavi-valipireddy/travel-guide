package com.ibtspl.foodmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class Ad_menu3 extends AppCompatActivity {
    private EditText s1;
    private EditText s2;
    private EditText s3;
    private EditText s4;
    private Button submit;
    private Firebase ref;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_menu3);
        Firebase.setAndroidContext(this);
        Bundle bundle=getIntent().getExtras();
        date=bundle.getString("message");
        ref = new Firebase("https://food-6ec31.firebaseio.com");
        s1 = (EditText) findViewById(R.id.s1);
        s2 = (EditText) findViewById(R.id.s2);
        s3 = (EditText) findViewById(R.id.s3);
        s4 = (EditText) findViewById(R.id.s4);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String S1 = s1.getText().toString();
                String S2 = s2.getText().toString();
                String S3 = s3.getText().toString();
                String S4 = s4.getText().toString();
                Supper s=new Supper(S1,S2,S3,S4);
                ref.child("Supper").child(date).setValue(s);
                Intent intent = new Intent(Ad_menu3.this, Admin.class);
                startActivity(intent);

            }
        });

    }
}
