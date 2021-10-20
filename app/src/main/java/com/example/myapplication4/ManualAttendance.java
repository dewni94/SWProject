package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import java.util.Calendar;

public class ManualAttendance extends AppCompatActivity implements View.OnClickListener{

    private ImageView backimg,datepickimg,timepickimg;
    private EditText dateshow,timeshow;
    int tHour,tMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_attendance);


        backimg=(ImageView) findViewById(R.id.imageView38);
        backimg.setOnClickListener(this);

        dateshow=findViewById(R.id.editTextTextPersonName20);
        datepickimg=findViewById(R.id.imageView53);

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