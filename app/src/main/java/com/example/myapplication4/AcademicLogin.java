package com.example.myapplication4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AcademicLogin extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail,editTextPassword;
    private Button signIn;
    private TextView forgotPassword;
    private ImageView backimg;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_login);

        signIn = (Button) findViewById(R.id.button4);
        signIn.setOnClickListener(this);

        editTextEmail=(EditText) findViewById(R.id.editTextTextPersonName3);
        editTextPassword=(EditText) findViewById(R.id.editTextTextPassword3);


        backimg=(ImageView) findViewById(R.id.imageView5);
        backimg.setOnClickListener(this);


        mAuth=FirebaseAuth.getInstance();

        forgotPassword=(TextView) findViewById(R.id.textView3);
        forgotPassword.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button4:
                userLogin();
                break;
            case R.id.textView3:
                startActivity(new Intent(this,ForgotPassword2.class));
                break;
            case R.id.imageView5:
                startActivity(new Intent(this,MainDashboard.class));
                break;
        }
    }

    private void userLogin() {
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length()<6){
            editTextPassword.setError("Min password length is 6 characters!");
            editTextPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(AcademicLogin.this, Academic_Dashboard.class));
                }
                else{
                    Toast.makeText(AcademicLogin.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();


                }
            }
        });

    }
}