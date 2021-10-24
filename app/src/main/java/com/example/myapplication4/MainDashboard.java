package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainDashboard extends AppCompatActivity implements View.OnClickListener {

    public CardView admincard,studentcard,academiccard,nonacademiccard,chatforumcard,appinfocard;
    private FirebaseAuth firebaseAuth;


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

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        Intent i;

        if (currentUser == null) {

            switch (view.getId()) {
                case R.id.admin:
                    i = new Intent(this, AdminLogin.class);
                    startActivity(i);
                    break;
                case R.id.student:
                    i = new Intent(this, StudentLogin.class);
                    startActivity(i);
                    break;
                case R.id.academic:
                    i = new Intent(this, AcademicLogin.class);
                    startActivity(i);
                    break;
                case R.id.nonacademic:
                    i = new Intent(this, NonAcademicLogin.class);
                    startActivity(i);
                    break;
                case R.id.appinfo:
                    i = new Intent(this, AppInfo.class);
                    startActivity(i);
                    break;
                case R.id.chatforum:
                    i = new Intent(this, ChatForum.class);
                    startActivity(i);
                    break;
            }
        }
        else{
            switch (view.getId()) {
                case R.id.admin:
                    i = new Intent(this, AdminDashboard.class);
                    startActivity(i);
                    break;
                case R.id.student:
                    i = new Intent(this, StudentDashboard.class);
                    startActivity(i);
                    break;
                case R.id.academic:
                    i = new Intent(this, Academic_Dashboard.class);
                    startActivity(i);
                    break;
                case R.id.nonacademic:
                    i = new Intent(this, NonAcademicDashboard.class);
                    startActivity(i);
                    break;
                case R.id.appinfo:
                    i = new Intent(this, AppInfo.class);
                    startActivity(i);
                    break;
                case R.id.chatforum:
                    i = new Intent(this, ChatForum.class);
                    startActivity(i);
                    break;
            }


        }


    }
}