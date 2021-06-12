package com.grip.spark01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.textfield.TextInputLayout;

public class signup extends AppCompatActivity {

    TextInputLayout name,email,psw;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_signup);


        name=findViewById(R.id.name1);
        email=findViewById((R.id.email1));
        psw=findViewById((R.id.psw1));


    }
    private Boolean validateName(){
            String n_val=name.getEditText().getText().toString();
            if(n_val.isEmpty()){
                psw.setError("Field cannot be empty");
                return false;
            }
            else{
                psw.setError(null);
                return true;
            }
        }

    private Boolean validateEmail(){
            String e_val=email.getEditText().getText().toString();
            String pattern="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
            if(e_val.isEmpty()){
                email.setError("Field cannot be empty");
                return false;
            }
            else if(e_val.matches(pattern)){
                email.setError("Invalid email address");
                return false;
            }
            else{
                email.setError(null);
                return true;
            }
        }

    private Boolean ValidatePSW(){
        String p_val=email.getEditText().getText().toString();
        String psw_val="^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        if(p_val.isEmpty()){
            email.setError("Field cannot be empty");
            return false;
        }
        else if(p_val.matches(psw_val)){
            email.setError("Invalid email address");
            return false;
        }
        else{
            email.setError(null);
            return true;
        }
    }

    public void HandleSignUp(View view){

        if(!validateName() || !validateEmail() || ValidatePSW()){
            return;
        }

        String user_name=name.getEditText().getText().toString();
        String user_email=email.getEditText().getText().toString();
        String user_psw=psw.getEditText().getText().toString();

//        UserHelperClass helperClass=new UserHelperClass(user_name,user_email,user_psw);
//        refer


    }

}