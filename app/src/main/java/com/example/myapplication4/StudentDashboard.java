package com.example.myapplication4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StudentDashboard extends AppCompatActivity implements View.OnClickListener {

    public CardView giveattendancecard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_main);

        giveattendancecard=(CardView) findViewById(R.id.giveatt);

        giveattendancecard.setOnClickListener(this);



        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_home){
                    Toast.makeText(StudentDashboard.this,"Home",Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId()==R.id.nav_appinfo){
                    Intent i;
                    i=new Intent(getApplicationContext(),AppInfo.class);
                    startActivity(i);
                    Toast.makeText(StudentDashboard.this,"App Info",Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId()==R.id.nav_logout){
                    AlertDialog.Builder message = new AlertDialog.Builder(StudentDashboard.this);
                    message.setTitle("Logout");
                    message.setMessage("Are you sure you want to logout ?");
                    message.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            FirebaseAuth.getInstance().signOut();
                            Intent j;
                            j=new Intent(getApplicationContext(),MainDashboard.class);
                            startActivity(j);
                            Toast.makeText(StudentDashboard.this,"Logged Out!",Toast.LENGTH_SHORT).show();
                        }
                    });
                    message.setNegativeButton(" NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    message.show();
                }
                DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.giveatt:
                i=new Intent(this,GiveAttendance.class);
                startActivity(i);
                break;
        }
    }

}