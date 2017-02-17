package com.ibtspl.foodmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class Ad_Menu2 extends AppCompatActivity {
    private EditText l1;
    private EditText l2;
    private EditText l3;
    private EditText l4;
    private EditText l5;
    private Button submit;
    private Firebase ref;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad__menu2);
        Firebase.setAndroidContext(this);
        Bundle bundle=getIntent().getExtras();
        date=bundle.getString("message");
        ref = new Firebase("https://food-6ec31.firebaseio.com");
        l1 = (EditText) findViewById(R.id.f1);
        l2 = (EditText) findViewById(R.id.f2);
        l3 = (EditText) findViewById(R.id.f3);
        l4 = (EditText) findViewById(R.id.f4);
        l5 = (EditText) findViewById(R.id.f5);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String L1 = l1.getText().toString();
                String L2 = l2.getText().toString();
                String L3 = l3.getText().toString();
                String L4 = l4.getText().toString();
                String L5 = l5.getText().toString();
                Lunch l=new Lunch(L1,L2,L3,L4,L5);
                ref.child("Lunch").child(date).setValue(l);
                Intent intent = new Intent(Ad_Menu2.this, Ad_menu3.class);
                intent.putExtra("message",date);
                startActivity(intent);
            }
        });
    }
}
