package com.example.myapplication4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class ForgotPassword extends AppCompatActivity {

    private EditText emailEditText;
    private Button resetPasswordButton;
    private ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailEditText=(EditText) findViewById(R.id.editTextTextPersonName34);
        resetPasswordButton=(Button) findViewById(R.id.button9);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        auth=FirebaseAuth.getInstance();

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }
    private void resetPassword(){
        String email=emailEditText.getText().toString().trim();
        if(email.isEmpty()){
            emailEditText.setError("Email is required!");
            emailEditText.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this, "Check your email to reset your password!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(ForgotPassword.this, "Something went wrong!Try again!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}