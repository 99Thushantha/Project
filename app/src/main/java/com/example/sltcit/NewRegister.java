package com.example.sltcit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class NewRegister extends AppCompatActivity {

    private Button btnBack,btnRegisterNew;
    private EditText txtRegEmail,txtRegPw,txtRegCPw;
    private CheckBox chkAgree;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_register);
        OpenRegForm();
        SaveNewRegData();
    }

    private void OpenRegForm()
    {
        btnBack=findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityNewReg = new Intent(NewRegister.this,MainActivityLogin.class);
                startActivity(activityNewReg);
            }
        });
    }
    private void SaveNewRegData()
    {
        progressBar=findViewById(R.id.progressBar);
        txtRegEmail=findViewById(R.id.txtRegEmail);
        txtRegPw=findViewById(R.id.txtRegPw);
        txtRegCPw=findViewById(R.id.txtRegCPw);
        chkAgree=findViewById(R.id.chkAgree);
        btnRegisterNew=findViewById(R.id.btnRegisterNew);

        progressBar.setVisibility(View.INVISIBLE);
        fAuth=FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser()!=null)
        {
            Toast.makeText(getApplicationContext(),"Already Registered",Toast.LENGTH_SHORT).show();
            //finish();
        }

        btnRegisterNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=txtRegEmail.getText().toString().trim();
                String pw=txtRegPw.getText().toString().trim();
                String cpw=txtRegCPw.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    txtRegEmail.setError("Email Required");
                    return;
                }
                if(TextUtils.isEmpty(pw))
                {
                    txtRegPw.setError("Password Required");
                    return;
                }
                if(TextUtils.isEmpty(cpw))
                {
                    txtRegCPw.setError("Confirm Password Required");
                    return;
                }
                if(chkAgree.isChecked()==false)
                {
                    Toast.makeText(getApplicationContext(),"Please Agree to T&C",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!pw.equals(cpw))
                {
                    Toast.makeText(getApplicationContext(),"Password Not Matched",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pw.length()<6)
                {
                    Toast.makeText(getApplicationContext(),"Password Too Short (Min:6 Charactor)",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!email.contains("sltc.ac.lk"))
                {
                    txtRegEmail.setError("Email Not Valid");
                    Toast.makeText(getApplicationContext(),"Use the email provided by the university",Toast.LENGTH_SHORT).show();
                    return;
                }
                //else
                //{
                    //save data firebase
                    progressBar.setVisibility(View.VISIBLE);
                    fAuth.createUserWithEmailAndPassword(email,pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(),"Registration Success!",Toast.LENGTH_SHORT).show();
                                Intent activityNewReg = new Intent(getApplicationContext(), NewRegister.class);
                                startActivity(activityNewReg);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Registration Failed!",Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                //}


            }
        });
    }
}