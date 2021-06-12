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
    Button HandleSignUp,forgot,go;
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





//        private Boolean validateName(){
//            String val=psw.getEditText().getText().toString();
//            String pattern="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$"
//            if(val.isEmpty()){
//                email.setError("Field cannot be empty");
//                return false;
//            }
//            else if(val.matches(pattern)){
//                email.setError("Invalid email address");
//                return false;
//            }
//            else{
//                email.setError(null);
//                return true;
//            }
//        }
//
//        private Boolean validatPSW(){
//            String val=psw.getEditText().getText().toString();
//            if(val.isEmpty()){
//                psw.setError("Field cannot be empty");
//                return false;
//            }
//            else{
//                psw.setError(null);
//                return true;
//            }
//        }


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