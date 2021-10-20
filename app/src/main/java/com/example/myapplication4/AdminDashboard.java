package com.example.myapplication4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.Navigation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class AdminDashboard extends AppCompatActivity implements View.OnClickListener {

    public CardView updateinfocard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_nav_activity);

        updateinfocard=(CardView) findViewById(R.id.updateinfo);

        updateinfocard.setOnClickListener(this);

        NavigationView navigationView=findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_home){
                    Toast.makeText(AdminDashboard.this,"Home",Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId()==R.id.nav_appinfo){
                    Intent i;
                    i=new Intent(getApplicationContext(),AppInfo.class);
                    startActivity(i);
                    Toast.makeText(AdminDashboard.this,"App Info",Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId()==R.id.nav_logout){
                    AlertDialog.Builder message = new AlertDialog.Builder(AdminDashboard.this);
                    message.setTitle("Logout");
                    message.setMessage("Are you sure you want to logout ?");
                    message.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            FirebaseAuth.getInstance().signOut();
                            Intent j;
                            j=new Intent(getApplicationContext(),MainDashboard.class);
                            startActivity(j);
                            Toast.makeText(AdminDashboard.this,"Logged Out!",Toast.LENGTH_SHORT).show();
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
                DrawerLayout drawerLayout=findViewById(R.id.drawer_layout3);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }


    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.updateinfo:
                i=new Intent(this,UpdateInfo.class);
                startActivity(i);
                break;
        }
    }

}