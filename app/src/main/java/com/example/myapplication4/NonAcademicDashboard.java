package com.example.myapplication4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class NonAcademicDashboard extends AppCompatActivity implements View.OnClickListener {

    public CardView markleavecard,takeattendancecard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nonaca_nav_activity);

        markleavecard=(CardView) findViewById(R.id.markleave);
        takeattendancecard=(CardView) findViewById(R.id.takeatt);

        markleavecard.setOnClickListener(this);
        takeattendancecard.setOnClickListener(this);

        NavigationView navigationView=findViewById(R.id.nav_view3);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_home){
                    Toast.makeText(NonAcademicDashboard.this,"Home",Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId()==R.id.nav_appinfo){
                    Intent i;
                    i=new Intent(getApplicationContext(),AppInfo.class);
                    startActivity(i);
                    Toast.makeText(NonAcademicDashboard.this,"App Info",Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId()==R.id.nav_logout){
                    AlertDialog.Builder message = new AlertDialog.Builder(NonAcademicDashboard.this);
                    message.setTitle("Logout");
                    message.setMessage("Are you sure you want to logout ?");
                    message.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            FirebaseAuth.getInstance().signOut();
                            Intent j;
                            j=new Intent(getApplicationContext(),MainDashboard.class);
                            startActivity(j);
                            Toast.makeText(NonAcademicDashboard.this,"Logged Out!",Toast.LENGTH_SHORT).show();
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
                DrawerLayout drawerLayout=findViewById(R.id.drawer_layout4);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.markleave:
                i=new Intent(this,MarkLeave.class);
                startActivity(i);
                break;
            case R.id.takeatt:
                AlertDialog.Builder message = new AlertDialog.Builder(NonAcademicDashboard.this);
                message.setTitle("Camera Permission");
                message.setIcon(R.drawable.camera);
                message.setMessage("Allow NEXTSTEPSTUDENT to start camera ?");
                message.setPositiveButton("ALLOW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseAuth.getInstance().signOut();
                        Intent j;
                        j=new Intent(getApplicationContext(),TakeAttendance.class);
                        startActivity(j);
                    }
                });
                message.setNegativeButton(" DENY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                message.show();
                break;
        }
    }

}