package com.example.instant_messanger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private String emailValue;
    private String passwordValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialization of objects
        TextView navigate_to_login = findViewById(R.id.already_account);
        MaterialButton btn_Sign_Up = findViewById(R.id.btnSignUp);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.passsword);
        mAuth=FirebaseAuth.getInstance();
        navigate_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(MainActivity.this, Login.class);
                startActivity(login);
            }
        });

        btn_Sign_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().isEmpty() && password.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fields should not empty", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    try {
                        //showing progress bar to screen
                        progressBar = findViewById(R.id.progressBar);
                        progressBar.setVisibility(View.VISIBLE);

                        emailValue = email.getText().toString();
                        passwordValue = password.getText().toString();

                        mAuth.createUserWithEmailAndPassword(emailValue, passwordValue).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                     //removing progress bar
                                    email.getText().clear();
                                    password.getText().clear();
                                    progressBar.setVisibility(View.GONE);
                                    //Moving to next screen
                                    Intent chat = new Intent(MainActivity.this, com.example.instant_messanger.chat.class);
                                    startActivity(chat);
                                }
                            }
                        });

                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }

                }
            }
        });
    }

}