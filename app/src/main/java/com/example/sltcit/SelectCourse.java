package com.example.sltcit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class SelectCourse extends AppCompatActivity {


    LinearLayout LU,LC,LS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course);

        LU=findViewById(R.id.LUdemy);
        LC=findViewById(R.id.LC);
        LS=findViewById(R.id.LS);

        LU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.udemy.com/"));
                startActivity(browserIntent);
            }
        });

        LC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.coursera.org/"));
                startActivity(browserIntent);
            }
        });

        LS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.skillshare.com/?via=header"));
                startActivity(browserIntent);
            }
        });

    }
}