package com.example.sltcit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;

public class ViewSubjects extends AppCompatActivity {

    ListView listSub;
    String Sub;
    RadioButton note,pp;
    boolean notes=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_subjects);
        note=(RadioButton)findViewById(R.id.radioNote);
        pp=(RadioButton)findViewById(R.id.radioPapers);
        popupList();

    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioNote:
                if (checked)
                    notes=true;
                    pp.setChecked(false);
                    break;
            case R.id.radioPapers:
                if (checked)
                    notes=false;
                    note.setChecked(false);
                    break;
        }
    }
    private void popupList()
    {
        try
        {
            listSub=(ListView) findViewById(R.id.listSub);

            ArrayList<String> arrayList=new ArrayList<>();

            arrayList.add("---------------YEAR-1/SEMESTER-1---------------");
            arrayList.add("CMP1101 Computer Systems (Y-1/S-1)");//1
            arrayList.add("CMP1102 Programming Concepts (Y-1/S-1)");//2
            arrayList.add("FND1101 Mathematics for Computing (Y-1/S-1)");//3
            arrayList.add("PRF1101 Professional Practice (Y-1/S-1)");//4
            arrayList.add("PRF1102 Communication in Tech World (Y-1/S-1)");//5
            arrayList.add("---------------YEAR-1/SEMESTER-2---------------");
            arrayList.add("Business Analysis and Software Design (Y-1/S-2");//7
            arrayList.add("Data Technologies (Y-1/S2)");//8
            arrayList.add("Entrepreneurship and Start-up Culture (Y-1/S2)");//9
            arrayList.add("Internet Technologies (Y-1/S2)");//10
            arrayList.add("Probability Statistics(with programming) (Y-1/S2)");//11
            arrayList.add("---------------YEAR-2/SEMESTER-1---------------");
            arrayList.add("CCO200 Arts for Technology (Y-2/S1)");//13
            arrayList.add("CCS200 Object Oriented Programming (Y-2/S1)");//14
            arrayList.add("CCO200 CCS201 Communication Protocols and Models (Y-2/S1)");//15
            arrayList.add("CCS202 Information Security (Y-2/S1)");//16
            arrayList.add("CMA200 Programming with Vectors and Matrices (Y-2/S1)");//17
            arrayList.add("---------------YEAR-2/SEMESTER-2---------------");
            arrayList.add("Cloud Computing (coursera) (Y-2/S2)");//19
            arrayList.add("Data Structure (Y-2/S2)");//20
            arrayList.add("Operating Systems and Platforms (Y-2/S2)");//21
            arrayList.add("Project Management (Y-2/S2)");//22


            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
            listSub.setAdapter(arrayAdapter);

            listSub.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if(i==1)
                    {
                        if(notes==true)
                        {
                            Sub="Sub1N";
                        }
                        else
                        {
                            Sub="Sub1P";
                        }
                        Intent activityViewSub = new Intent(ViewSubjects.this, ShowNotesPapers.class);
                        activityViewSub.putExtra("SUB", Sub);
                        activityViewSub.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(activityViewSub);
                    }
                    if(i==2)
                    {
                        if(notes==true)
                        {
                            Sub="Sub2N";
                        }
                        else
                        {
                            Sub="Sub2P";
                        }
                        Intent activityViewSub = new Intent(ViewSubjects.this, ShowNotesPapers.class);
                        activityViewSub.putExtra("SUB", Sub);
                        activityViewSub.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(activityViewSub);
                    }

                }
            });

        }
        catch (Exception e)
        {

        }
    }
}