package com.example.myapplication4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ManualAttendance extends AppCompatActivity implements View.OnClickListener{

    ImageView backimg,datepickimg,timepickimg;
    EditText dateshow,timeshow,name,indexNo;
    int tHour,tMinute;
    Button button;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_attendance);


        backimg=(ImageView) findViewById(R.id.imageView38);
        backimg.setOnClickListener(this);

        dateshow=findViewById(R.id.editTextTextPersonName20);
        datepickimg=findViewById(R.id.imageView53);
        name=findViewById(R.id.editTextTextPersonName17);
        indexNo=findViewById(R.id.editTextTextPersonName18);
        button=findViewById(R.id.button3);
        button.setOnClickListener(this);
        db=FirebaseFirestore.getInstance();

        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

        datepickimg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        ManualAttendance.this, new DatePickerDialog.OnDateSetListener() {
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
        timepickimg=findViewById(R.id.imageView58);
        timeshow=findViewById(R.id.editTextTextPersonName19);
        timepickimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(
                        ManualAttendance.this, new TimePickerDialog.OnTimeSetListener() {
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
        db = FirebaseFirestore.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name=name.getText().toString();
                String IndexNo=indexNo.getText().toString();
                String Date=dateshow.getText().toString();
                String Time=timeshow.getText().toString();
                Map<String,Object> attendance=new HashMap<>();
                attendance.put("Name",Name);
                attendance.put("Index_No",IndexNo);
                attendance.put("Date",Date);
                attendance.put("Time",Time);
                db.collection("Attendance Details").add(attendance).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(ManualAttendance.this, "Details updated successfully!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ManualAttendance.this, "Details updating unsuccessfull!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageView38:
                startActivity(new Intent(this,TakeAttendance.class));
                break;
        }
    }
}