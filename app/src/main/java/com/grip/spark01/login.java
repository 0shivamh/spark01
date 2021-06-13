package com.grip.spark01;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class login extends AppCompatActivity {
    Button HandleSignUp,forgot,go,g_btn,fb_btn;
    ImageView logo;
    TextView head;
    TextInputLayout email,psw;
    TextView short_t;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        HandleSignUp=findViewById(R.id.newuser);
        logo=findViewById(R.id.logo);
        head=findViewById(R.id.textView);
        email=findViewById(R.id.email);
        psw=findViewById(R.id.psw);
        forgot=findViewById(R.id.forgot);
        go=findViewById(R.id.go);
        short_t=findViewById(R.id.stitle);
        g_btn=findViewById(R.id.google_btn);
        fb_btn=findViewById(R.id.fb_btn);




        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        g_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        fb_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




        //call activity

        HandleSignUp.setOnClickListener((View)->{
            Intent intent=new Intent(login.this,signup.class);
            startActivity(intent);
        });

        forgot.setOnClickListener((View)->{
            Intent intent=new Intent(login.this,forgot.class);
            startActivity(intent);
        });


}}