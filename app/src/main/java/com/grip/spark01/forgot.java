package com.grip.spark01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class forgot extends AppCompatActivity {

    Button forgot_btn;
    TextInputLayout email;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_forgot);

        forgot_btn=findViewById(R.id.forgot_btn);
        email=findViewById(R.id.email_id);
        auth= FirebaseAuth.getInstance();


        forgot_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String u_email=email.getEditText().getText().toString().trim();

                auth.sendPasswordResetEmail(u_email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), "We have sent you email, plase check mailbox", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(forgot.this,login.class));
                        finish();
                    }
                });


            }
        });

    }
}