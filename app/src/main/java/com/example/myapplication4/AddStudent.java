package com.example.myapplication4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AddStudent extends AppCompatActivity {

    private EditText studentEmail , studentPass;
    private Button Update;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        auth = FirebaseAuth.getInstance();

        studentEmail = findViewById(R.id.editTextTextPersonName23);
        studentPass = findViewById(R.id.editTextTextPassword5);
        Update = findViewById(R.id.button6);

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Password = studentPass.getText().toString();
                String Email = studentEmail.getText().toString();

                if (!Email.isEmpty() && !Password.isEmpty()){

                    auth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(AddStudent.this, "Student added successfully" , Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            else{
                                Toast.makeText(AddStudent.this, task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(AddStudent.this, "Enter email & password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}