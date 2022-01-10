package com.example.sltcit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class FogotPassword extends AppCompatActivity {

    ProgressBar progressBar;
    TextView txtEmail;
    Button btnSendLink,goBack;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fogot_password);
        ForgotPw();
        goBack();

    }



    private void ForgotPw()
    {
        txtEmail=findViewById(R.id.txtPwResetEmail);
        progressBar=findViewById(R.id.progressBarresetPw);
        btnSendLink=findViewById(R.id.btnSendLink);

        try {
            btnSendLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email=txtEmail.getText().toString().trim();
                    if(TextUtils.isEmpty(email))
                    {
                        txtEmail.setError("Email Required");
                        return;
                    }
                    progressBar.setVisibility(View.VISIBLE);
                    fAuth.getInstance().sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(),"Please Check Your Email!",Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.INVISIBLE);
                                        Intent activity = new Intent(getApplicationContext(), MainActivityLogin.class);
                                        startActivity(activity);
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(),"Invalid Email",Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.INVISIBLE);
                                    }
                                }
                            });
                }
            });
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
        }


    }
    private void goBack()
    {
        goBack=findViewById(R.id.btnBack);
         goBack.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent activity = new Intent(getApplicationContext(), MainActivityLogin.class);
                 startActivity(activity);
             }
         });
    }

}