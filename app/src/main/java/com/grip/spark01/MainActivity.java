package com.grip.spark01;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int s_screen=3000;

    //vars
    Animation top_anim,bottom_anim;

    ImageView logo;
    TextView name,heading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        //animations
        top_anim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom_anim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        logo=findViewById(R.id.logo);
        name=findViewById(R.id.textView);
        heading=findViewById(R.id.headline);

        logo.setAnimation(top_anim);
        name.setAnimation(bottom_anim);
        heading.setAnimation(bottom_anim);

        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,login.class);

                Pair[] pairs=new Pair[2];

                pairs[0]=new Pair<View,String>(logo,"logoimg");
                pairs[1]=new Pair<View,String>(name,"head");

                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);

                startActivity(intent,options.toBundle());
//                finish();

            }
        },s_screen);

    }
}