package com.grip.spark01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    TextInputLayout name,email,psw;
    Button SignUp,google_signup,fb_signup;
    boolean isAllFieldsChecked = false;


    FirebaseDatabase RootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_signup);


        name=findViewById(R.id.name1);
        email=findViewById((R.id.email1));
        psw=findViewById((R.id.psw1));
        SignUp=findViewById((R.id.signup));
        google_signup=findViewById((R.id.google_btn));
        fb_signup=findViewById((R.id.fb_btn));



        SignUp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                RootNode= FirebaseDatabase.getInstance();
                reference= RootNode.getReference("Users");

                String u_name=name.getEditText().getText().toString().trim();
                String u_email=email.getEditText().getText().toString().trim();
                String u_psw=psw.getEditText().getText().toString().trim();


                if(u_name.isEmpty()){
                        name.setError("This feild is required");
                }
                else if(u_email.isEmpty()){
                    email.setError("This feild is required");
                }
                else if(u_psw.isEmpty()){
                    psw.setError("This feild is required");

                }
                else{
                    name.setError(null);
                    email.setError(null);
                    psw.setError(null);


                    int index = u_email.indexOf('@');
                    String n_email = u_email.substring(0, index);


                    UserHelperClass helperClass = new UserHelperClass(u_name, u_email, u_psw);
                    reference.child(n_email).setValue(helperClass);

                    Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();

                    name.getEditText().setText("");
                    email.getEditText().setText("");
                    psw.getEditText().setText("");
                }


            }
        });


        //Google

        google_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String u_name=name.getEditText().getText().toString().trim();
                String u_email=email.getEditText().getText().toString().trim();
                String u_psw=psw.getEditText().getText().toString().trim();

            }
        });

        //FB

        fb_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String u_name=name.getEditText().getText().toString().trim();
                String u_email=email.getEditText().getText().toString().trim();
                String u_psw=psw.getEditText().getText().toString().trim();

            }
        });

    }




}
