package com.ibtspl.foodmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;

import android.widget.TextView;
public class DateActivity extends AppCompatActivity {
    DatePicker picker;
    Button displayDate;
    TextView textview1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);



        textview1=(TextView)findViewById(R.id.textView1);
        picker=(DatePicker)findViewById(R.id.datePicker1);
        displayDate=(Button)findViewById(R.id.date_button);

        textview1.setText(getCurrentDate());

        displayDate.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {
                textview1.setText(getCurrentDate());
                String date=textview1.getText().toString();
                Intent intent=new Intent(DateActivity.this,Ad_Menu.class);
                intent.putExtra("message",date);
                startActivity(intent);

            }

        });
    }
    public String getCurrentDate(){
        StringBuilder builder=new StringBuilder();
        builder.append(picker.getDayOfMonth()+" "+(picker.getMonth() + 1)+" "+picker.getYear());
        //builder.append((picker.getMonth() + 1)+"/"+picker.getDayOfMonth()+"/"+picker.getYear());//month is 0 based
        //builder.append(picker.getDayOfMonth()+"/");
        //builder.append(picker.getYear());
        return builder.toString();
    }


}
