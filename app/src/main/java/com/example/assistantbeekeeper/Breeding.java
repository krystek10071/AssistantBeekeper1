package com.example.assistantbeekeeper;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import com.example.assistantbeekeeper.assistantbeekeepersqllite.MyDbHandler;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class Breeding extends AppCompatActivity {

    //declarations
    ListView eventsListView;
    ArrayList<String> listEvents=new ArrayList<>();                                                             //list for widget eventsListView that stores values with to-do items
    ArrayList<Long> listTimeInMillis=new ArrayList<>();                                                         //list with time in Millis
    ArrayList<String> listDescription=new ArrayList<>();                                                        //list with descriptions of activities for beekeeper
    Button addBreedingButton;
    ArrayAdapter arrayAdapter;
    long timeInMillis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeding);
        final CompactCalendarView compactCalendarView = findViewById(R.id.compact_calendar_view);

    //fields
        final MyDbHandler dbHelper=new MyDbHandler(this);
        final BreedingFunctions breedingFunctions=new BreedingFunctions();
        eventsListView= findViewById(R.id.events_listview);
        addBreedingButton=findViewById(R.id.add_breeding_button);
        arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, listEvents);


        if(listEvents.isEmpty()){

            breedingFunctions.readEvents(dbHelper, listTimeInMillis, listDescription);                            //read data from database and save in listTimeInMillis, listDescription
            breedingFunctions.addBreeding(compactCalendarView, listTimeInMillis, listDescription, listEvents);
            eventsListView.setAdapter(arrayAdapter);
            listTimeInMillis.clear();
            listDescription.clear();
        }



        ////////////////////////////////////Buttons Add Breeding////////////////////////////////////
        //Add BreedingButton
        addBreedingButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                timeInMillis=breedingFunctions.setBreedingDay(Breeding.this);

                breedingFunctions.addTimeMillisToList(listTimeInMillis, listDescription, timeInMillis);                                //add Date in millis and description to Lists

                breedingFunctions.addBreeding(compactCalendarView, listTimeInMillis, listDescription, listEvents);//add breeding to CompactCalendarView
                eventsListView.setAdapter(arrayAdapter);
                breedingFunctions.addToDatabase(dbHelper,listTimeInMillis, listDescription);                      //add to database
                listTimeInMillis.clear();
                listDescription.clear();
            }
        }
        );
    }


}




