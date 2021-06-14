package com.grip.spark01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;

import static com.squareup.picasso.Picasso.*;

public class profile extends AppCompatActivity {
    private FirebaseAuth auth;
    Button logout;
    TextView hi,name,email;
    ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        auth= FirebaseAuth.getInstance();

        logout=findViewById(R.id.logout);
        hi=findViewById(R.id.hi);
        name=findViewById(R.id.name_d);
        email=findViewById(R.id.email_d);
        pic=findViewById(R.id.profile);

       String email_id= auth.getCurrentUser().getEmail();

       email.setText("Email Address:"+email_id);
       int index = email_id.indexOf('@');
       String username = email_id.substring(0, index);
       String namehi=auth.getCurrentUser().getDisplayName();

       name.setText("Name: "+namehi);
       hi.setText("Hi "+namehi);

      String pic_url=auth.getCurrentUser().getPhotoUrl().toString();
        Picasso.get().load(pic_url).into(pic);;


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Toast.makeText(getApplicationContext(), "Log out succesfully", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(profile.this,login.class));
                finish();
            }
        });


    }


}