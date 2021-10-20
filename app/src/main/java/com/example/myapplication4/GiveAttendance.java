package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class GiveAttendance extends AppCompatActivity implements View.OnClickListener{

    private ImageView backimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_attendance);

        backimg=(ImageView) findViewById(R.id.imageView);
        backimg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
        case R.id.imageView:
        startActivity(new Intent(this,StudentDashboard.class));
        break;
        }
    }
}