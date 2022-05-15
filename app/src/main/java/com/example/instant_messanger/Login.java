package com.example.instant_messanger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private String emailValue;
    private String passwordValue;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView signUp = findViewById(R.id.have_not_account);
        EditText email = findViewById(R.id.login_email);
        EditText password = findViewById(R.id.login_passsword);
        MaterialButton btnLogin = findViewById(R.id.btnlogin);
        mAuth=FirebaseAuth.getInstance();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailValue = email.getText().toString();
                passwordValue = password.getText().toString();

                if (emailValue.isEmpty() && passwordValue.isEmpty()) {
                    Toast.makeText(Login.this, "Field should not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    progressBar=findViewById(R.id.loginProgressBar);
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(emailValue,passwordValue).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            }
                            email.getText().clear();
                            password.getText().clear();
                            progressBar.setVisibility(View.GONE);
                            Intent intent=new Intent(Login.this,chat.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}