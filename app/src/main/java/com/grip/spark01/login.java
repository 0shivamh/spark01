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


        forgot.setOnClickListener((View)->{
            Intent intent=new Intent(login.this,forgot.class);
        startActivity(intent);

//            Pair[] pairs=new Pair[4];
//            pairs[0]=new Pair<View,String>(logo,"logoimg");
//            pairs[1]=new Pair<View,String>(head,"head");
//            pairs[2]=new Pair<View,String>(short_t,"t_t");
//            pairs[3]=new Pair<View,String>(email,"email_t");
//            pairs[4]=new Pair<View,String>(go,"go_t");
//
//
//
//            ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(login.this,pairs);
//
//            startActivity(intent,options.toBundle());
        });

        HandleSignUp.setOnClickListener((View)->{
            Intent intent=new Intent(login.this,signup.class);
            startActivity(intent);

//                Pair[] pairs=new Pair[2];
//
//                pairs[0]=new Pair<View,String>(logo,"logoimg");
//                pairs[1]=new Pair<View,String>(head,"head");
//                pairs[2]=new Pair<View,String>(short_t,"t_t");
//                pairs[3]=new Pair<View,String>(email,"email_t");
//                pairs[4]=new Pair<View,String>(psw,"psw_t");
//                pairs[5]=new Pair<View,String>(forgot,"forgot_t");
//                pairs[6]=new Pair<View,String>(go,"go_t");
//                pairs[7]=new Pair<View,String>(HandleSignUp,"n_t");
//
//
//
//                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(login.this,pairs);
//
//                startActivity(intent,options.toBundle());
        });

}}