package com.example.myapplication4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.Result;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TakeAttendance extends AppCompatActivity implements View.OnClickListener {

    CodeScanner codeScanner;
    CodeScannerView scanView;
    Button button;
    int tHour,tMinute;
    EditText dateshow,timeshow;
    TextView manualInput;
    ImageView backimg,datepickimg,timepickimg;
    DialogInterface.OnClickListener setListener;
    FirebaseFirestore db;
    String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);
        button=(Button) findViewById(R.id.button5);
        button.setOnClickListener(this);
        backimg=(ImageView) findViewById(R.id.imageView46);
        backimg.setOnClickListener(this);
        dateshow=findViewById(R.id.datepicker);
        datepickimg=findViewById(R.id.imageView50);
        manualInput=findViewById(R.id.textView17);
        manualInput.setOnClickListener(this);
        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

        datepickimg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        TakeAttendance.this, new DatePickerDialog.OnDateSetListener() {
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
        timepickimg=findViewById(R.id.imageView73);
        timeshow=findViewById(R.id.editTextTextPersonName38);
        timepickimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(
                        TakeAttendance.this, new TimePickerDialog.OnTimeSetListener() {
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

        scanView=findViewById(R.id.scanner_view);

        codeScanner=new CodeScanner(this,scanView);
        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        res=result.getText();
                    }
                });
            }
        });
        db = FirebaseFirestore.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Date=dateshow.getText().toString();
                String Time=timeshow.getText().toString();
                String Result=res.toString();
                Map<String,Object> attendance=new HashMap<>();;
                attendance.put("Date",Date);
                attendance.put("Time",Time);
                attendance.put("Name & Index_No",Result);
                db.collection("Attendance Details").add(attendance).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(TakeAttendance.this, "Details updated successfully!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(TakeAttendance.this, "Details updating unsuccessfull!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        codeScanner.startPreview();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textView17:
                startActivity(new Intent(this,ManualAttendance.class));
                break;
            case R.id.imageView46:
                startActivity(new Intent(this,NonAcademicDashboard.class));
                break;
        }

    }
}