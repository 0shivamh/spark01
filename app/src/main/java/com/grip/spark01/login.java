package com.grip.spark01;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.OAuthProvider;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class login extends AppCompatActivity {
    TwitterLoginButton loginButton;
    Button HandleSignUp, forgot, go, g_btn, fb_btn;
    ImageView logo;
    TextView head;
    TextInputLayout email, psw;
    TextView short_t;

    private FirebaseAuth auth;
    private GoogleSignInClient mGoogleSignInClient;
    private final static  int RC_SIGN_IN=123;


    OAuthProvider.Builder provider = OAuthProvider.newBuilder("twitter.com");




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Twitter.initialize(this);
        setContentView(R.layout.activity_login);



        HandleSignUp = findViewById(R.id.newuser);
        logo = findViewById(R.id.logo);
        head = findViewById(R.id.textView);
        email = findViewById(R.id.email);
        psw = findViewById(R.id.psw);
        forgot = findViewById(R.id.forgot);
        go = findViewById(R.id.go);
        short_t = findViewById(R.id.stitle);
        g_btn = findViewById(R.id.google_btn);
        fb_btn = findViewById(R.id.fb_btn);

        auth= FirebaseAuth.getInstance();

        createRequest();




        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String u_email=email.getEditText().getText().toString().trim();
                String u_psw=psw.getEditText().getText().toString().trim();

                if(u_email.isEmpty()){
                    email.setError("This feild is required");
                }
                else if(u_psw.isEmpty()){
                    psw.setError("This feild is required");

                }
                else{

                    email.setError(null);
                    psw.setError(null);

                    auth.signInWithEmailAndPassword(u_email,u_psw)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText(getApplicationContext(), "Logged in successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(login.this,profile.class));
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Login failed! Check entered details", Toast.LENGTH_SHORT).show();

                                    email.getEditText().setText("");
                                    psw.getEditText().setText("");
                                }
                            });


                }

            }
        });


        g_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();

            }
        });

        fb_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth
                        .startActivityForSignInWithProvider(/* activity= */ login.this, provider.build())
                        .addOnSuccessListener(
                                new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(getApplicationContext(), "Logged in successfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(login.this,profile.class));
                                        finish();
                                    }
                                })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Handle failure.
                                        Toast.makeText(getApplicationContext(), "Login failed!", Toast.LENGTH_SHORT).show();

                                    }
                                });
            }
        });


        //call activity

        HandleSignUp.setOnClickListener((View) -> {
            Intent intent = new Intent(login.this, signup.class);
            startActivity(intent);
        });

        forgot.setOnClickListener((View) -> {
            Intent intent = new Intent(login.this, forgot.class);
            startActivity(intent);
        });


    }


    private Boolean ValidateEmail() {
        String email_val = email.getEditText().toString();
        if (email_val.isEmpty()) {
            email.setError("Invalid");
            return false;
        } else {
            email.setError(null);
            return true;

        }

    }
    private Boolean ValidatePSW() {
        String psw_val = psw.getEditText().toString();
        if (psw_val.isEmpty()) {
            psw.setError("Invalid");
            return false;
        } else {
            psw.setError(null);
            return true;

        }

    }

    private  void createRequest(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        loginButton.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
//                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Login failed! Check entered details", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    private static final String TAG = "null";

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = auth.getCurrentUser();
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
//                            updateUI(null);
                        }
                    }
                });
    }



    //facebook OAuth






}