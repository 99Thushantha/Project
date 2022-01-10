package com.example.sltcit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Home extends AppCompatActivity {

    private CardView note,video,course,contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        openForms();
    }

    private void openForms()
    {

        note = findViewById(R.id.tileNotes);
        video= findViewById(R.id.tileVideo);
        course=findViewById(R.id.tileCours);
        contact=findViewById(R.id.tileContact);

        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityViewSub = new Intent(getApplicationContext(), ViewSubjects.class);
                startActivity(activityViewSub);

                //Intent activityViewSub = new Intent(getApplicationContext(), TestActivity.class);
                //startActivity(activityViewSub);
            }
        });

        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityViewCourse = new Intent(getApplicationContext(), SelectCourse.class);
                startActivity(activityViewCourse);
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityViewCourse = new Intent(getApplicationContext(), Tutorials.class);
                startActivity(activityViewCourse);
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityViewCourse = new Intent(getApplicationContext(), ContactActivity.class);
                startActivity(activityViewCourse);
            }
        });

    }



}