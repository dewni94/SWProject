package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainDashboard extends AppCompatActivity implements View.OnClickListener {

    public CardView admincard,studentcard,academiccard,nonacademiccard,chatforumcard,appinfocard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        admincard=(CardView) findViewById(R.id.admin);
        studentcard=(CardView) findViewById(R.id.student);
        academiccard=(CardView) findViewById(R.id.academic);
        nonacademiccard=(CardView) findViewById(R.id.nonacademic);
        chatforumcard=(CardView) findViewById(R.id.chatforum);
        appinfocard=(CardView) findViewById(R.id.appinfo);

        admincard.setOnClickListener(this);
        studentcard.setOnClickListener(this);
        academiccard.setOnClickListener(this);
        nonacademiccard.setOnClickListener(this);
        chatforumcard.setOnClickListener(this);
        appinfocard.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.admin:
                i=new Intent(this,AdminLogin.class);
                startActivity(i);
                break;
            case R.id.student:
                i=new Intent(this,StudentLogin.class);
                startActivity(i);
                break;
            case R.id.academic:
                i=new Intent(this, AcademicLogin.class);
                startActivity(i);
                break;
            case R.id.nonacademic:
                i=new Intent(this, NonAcademicLogin.class);
                startActivity(i);
                break;
            case R.id.appinfo:
                i=new Intent(this, AppInfo.class);
                startActivity(i);
                break;
            case R.id.chatforum:
                i=new Intent(this, ChatForrum.class);
                startActivity(i);
                break;
         }


    }
}