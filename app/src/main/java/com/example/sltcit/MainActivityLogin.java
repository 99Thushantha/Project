package com.example.sltcit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivityLogin extends AppCompatActivity {

    private EditText txtEmail,txtPw;
    private Button btnLogin,btnRegister, btnFogotPw, btnGoogleSign;

    ProgressBar progressBarLogin;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        Toast.makeText(MainActivityLogin.this,"login screen",Toast.LENGTH_LONG);
        OpenRegForm();
        Login();
        FogotPw();



    }

    private void OpenRegForm()
    {
        btnRegister=findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityNewReg = new Intent(getApplicationContext(), NewRegister.class);
                startActivity(activityNewReg);

            }
        });
    }
    private void FogotPw()
    {
        btnFogotPw=findViewById(R.id.btnFogotPw);

        btnFogotPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(getApplicationContext(), FogotPassword.class);
                startActivity(activity);

            }
        });
    }
    private void Login()
    {
        try {
            btnLogin=findViewById(R.id.btnSendLink);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean mailValid=false;
                    txtEmail=findViewById(R.id.txtEmail);
                    txtPw=findViewById(R.id.txtPw);
                    progressBarLogin=findViewById(R.id.progressBarLogin);
                    fAuth=FirebaseAuth.getInstance();

                    String email=txtEmail.getText().toString().trim();
                    String pw=txtPw.getText().toString().trim();

                    if(TextUtils.isEmpty(email))
                    {
                        txtEmail.setError("Email Required");
                        mailValid=false;
                        return;
                    }
                    if(TextUtils.isEmpty(pw))
                    {
                        txtPw.setError("Password Required");
                        mailValid=false;
                        return;
                    }
                    if(!email.contains("sltc.ac.lk"))
                    {
                        txtEmail.setError("Email Not Valid");
                        Toast.makeText(getApplicationContext(),"Use the email provided by the university",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    //authenticate user

                    progressBarLogin.setVisibility(View.VISIBLE);

                    fAuth.signInWithEmailAndPassword(email,pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(),"login Success!",Toast.LENGTH_SHORT).show();
                                Intent activityNewReg = new Intent(getApplicationContext(), Home.class);
                                activityNewReg.putExtra("KEY",fAuth.getCurrentUser().getEmail().toString());
                                startActivity(activityNewReg);
                                progressBarLogin.setVisibility(View.INVISIBLE);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"User Not Found",Toast.LENGTH_SHORT).show();
                                progressBarLogin.setVisibility(View.INVISIBLE);
                            }
                        }
                    });


                }
            });

        }catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

}