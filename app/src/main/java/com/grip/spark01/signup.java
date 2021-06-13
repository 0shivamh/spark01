package com.grip.spark01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class signup extends AppCompatActivity {
    TwitterLoginButton loginButton;

    TextInputLayout name,email,psw;
    Button SignUp,google_signup,fb_signup;
    OAuthProvider.Builder provider = OAuthProvider.newBuilder("twitter.com");


    FirebaseDatabase RootNode;
    DatabaseReference reference;
    private FirebaseAuth auth;
    private GoogleSignInClient mGoogleSignInClient;
    private final static  int RC_SIGN_IN=123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Twitter.initialize(this);

        setContentView(R.layout.activity_signup);


        name=findViewById(R.id.name1);
        email=findViewById((R.id.email1));
        psw=findViewById((R.id.psw1));
        SignUp=findViewById((R.id.signup));
        google_signup=findViewById((R.id.google_btn));
        fb_signup=findViewById((R.id.fb_btn));


        auth= FirebaseAuth.getInstance();

        createRequest();


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

                    auth.createUserWithEmailAndPassword(u_email,u_psw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(signup.this,profile.class));
                        finish();
                            UserHelperClass helperClass = new UserHelperClass(u_name, u_email, u_psw);
                            reference.child(n_email).setValue(helperClass);
                            name.getEditText().setText("");
                            email.getEditText().setText("");
                            psw.getEditText().setText("");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Something is wrong, try again", Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }

        });


        //Google

        google_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        //FB

        fb_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth
                        .startActivityForSignInWithProvider(/* activity= */ signup.this, provider.build())
                        .addOnSuccessListener(
                                new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(getApplicationContext(), "Logged in successfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(signup.this,profile.class));
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

}
