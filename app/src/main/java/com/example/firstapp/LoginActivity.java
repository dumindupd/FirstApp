package com.example.firstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email,password;
    private Button login;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtEmail = email.getText().toString();
                String txtPass = password.getText().toString();

                if (txtEmail.isEmpty() || txtPass.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter both username and password...", Toast.LENGTH_SHORT).show();
                }
                else{
                    validateUser(txtEmail,txtPass);
                }
            }
        });
    }

    private void validateUser(String e, String p) {
        mAuth.signInWithEmailAndPassword(e,p).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(LoginActivity.this, "Valid User...", Toast.LENGTH_SHORT).show();
                Intent int_home = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(int_home);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, "Invalid username or password, try again...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            Intent int_home = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(int_home);
            finish();
        }
    }
}