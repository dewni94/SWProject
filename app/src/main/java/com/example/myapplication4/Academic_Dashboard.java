package com.example.myapplication4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Academic_Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aca_nav_activity);

        NavigationView navigationView=findViewById(R.id.nav_view4);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_home){
                    Toast.makeText(Academic_Dashboard.this,"Home",Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId()==R.id.nav_appinfo){
                    Intent i;
                    i=new Intent(getApplicationContext(),AppInfo.class);
                    startActivity(i);
                    Toast.makeText(Academic_Dashboard.this,"App Info",Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId()==R.id.nav_logout){
                    AlertDialog.Builder message = new AlertDialog.Builder(Academic_Dashboard.this);
                    message.setTitle("Logout");
                    message.setMessage("Are you sure you want to logout ?");
                    message.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            FirebaseAuth.getInstance().signOut();
                            Intent j;
                            j=new Intent(getApplicationContext(),MainDashboard.class);
                            startActivity(j);
                            Toast.makeText(Academic_Dashboard.this,"Logged Out!",Toast.LENGTH_SHORT).show();
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
                DrawerLayout drawerLayout=findViewById(R.id.drawer_layout2);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
}