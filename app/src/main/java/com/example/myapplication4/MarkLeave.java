package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MarkLeave extends AppCompatActivity implements View.OnClickListener{

    private ImageView backimg,datepickimg,timepickimg;
    private EditText dateshow,timeshow,name,indexNo,reason;
    int tHour,tMinute;
    private Button submit;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_leave);

        backimg=(ImageView) findViewById(R.id.imageView11);
        backimg.setOnClickListener(this);

        dateshow=findViewById(R.id.editTextTextPersonName11);
        datepickimg=findViewById(R.id.imageView26);
        name=findViewById(R.id.editTextTextPersonName9);
        indexNo=findViewById(R.id.editTextTextPersonName10);
        reason=findViewById(R.id.editTextTextPersonName13);
        submit=findViewById(R.id.button2);
        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

        datepickimg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        MarkLeave.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month=month+1;
                        String date=day+"/"+month+"/"+year;
                        dateshow.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        timepickimg=findViewById(R.id.imageView27);
        timeshow=findViewById(R.id.editTextTextPersonName12);
        timepickimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(
                        MarkLeave.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        tHour=hour;
                        tMinute=min;
                        Calendar calendarT=Calendar.getInstance();
                        calendarT.set(0,0,0,tHour,tMinute);
                        android.text.format.DateFormat df = new android.text.format.DateFormat();
                        timeshow.setText(df.format("hh:mm:aa",calendarT).toString());
                    }
                },12,0,false
                );
                timePickerDialog.updateTime(tHour,tMinute);
                timePickerDialog.show();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            String Name=name.getText().toString();
            String IndexNo=indexNo.getText().toString();
            String Date=dateshow.getText().toString();
            String Time=timeshow.getText().toString();
            String Reason=reason.toString();
            @Override
            public void onClick(View view) {
                DocumentReference documentReference=fStore.collection("Leave").document(Date).collection(IndexNo).document(Name);
                Map<String,Object> leave=new HashMap<>();
                leave.put("Name",Name);
                leave.put("Index_No",IndexNo);
                leave.put("Date",Date);
                leave.put("Time",Time);
                leave.put("Reason",Reason);
                documentReference.set(leave).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("TAG", "onSuccess: Details added to the database");
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageView11:
                startActivity(new Intent(this,NonAcademicDashboard.class));
                break;
        }

    }
}