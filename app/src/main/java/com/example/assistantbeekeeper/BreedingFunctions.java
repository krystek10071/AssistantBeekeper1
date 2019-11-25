package com.example.assistantbeekeeper;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.widget.DatePicker;

import com.example.assistantbeekeeper.assistantbeekeepersqllite.FeedReaderContract;
import com.example.assistantbeekeeper.assistantbeekeepersqllite.MyDbHandler;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class BreedingFunctions {
    private Calendar calendar;

    protected void addTimeMillisToList(ArrayList<Long> listTimeInMillis, ArrayList<String> description, long timeInMillis){
        //time1...time4 this is time for individual activities
        long time=timeInMillis;                                                                                 //set the current time
        long constTime=86400000;                                                                                //milisecunds in one day
        long time1=time+constTime;
        long time2=time+(12*constTime);
        long time3=time+(13*constTime);
        long time4=time+(14*constTime);
        listTimeInMillis.add(time1);
        listTimeInMillis.add(time2);
        listTimeInMillis.add(time3);
        listTimeInMillis.add(time4);
        description.add("Przenies larwy do miseczek matecznikowych");
        description.add("Umiesc je w klateczka pomiedzy z plastrami z czerwiem");
        description.add("Wygryzienie sie matek");
        description.add("Znakowanie matek i umieszczenie ich z rodziną wychowującą");
    }

    protected   void addBreeding(CompactCalendarView compactCalendarView, ArrayList<Long> timeInMillis, ArrayList<String> description, ArrayList<String> listEvents ){
    //lenght lists
        int count=description.size();
        for(int i=0; i<count; i++){
            addEvent(compactCalendarView, timeInMillis.get(i), description.get(i), listEvents);
        }

    }


    //add event to compactCalendarView && eventsList

    private void addEvent (CompactCalendarView compactCalendarView, long timeInMillis, Object data, ArrayList<String> listEvents){
        Date helpDate=new Date();
        Event ev1=new Event(Color.RED, timeInMillis,data);
        compactCalendarView.addEvent(ev1);
        helpDate.setTime(timeInMillis);
        listEvents.add(helpDate.toString() + data);

    }

    //Add Events to Database

    protected void addToDatabase(MyDbHandler dbHelper, ArrayList<Long> timeInMillis, ArrayList<String> description){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();

        int count=description.size();
        for(int i=0; i<count; i++){
            values.put(FeedReaderContract.EventsCalendar.COL1, timeInMillis.get(i));
            values.put(FeedReaderContract.EventsCalendar.COL2, description.get(i));
            long newRowID=db.insert(FeedReaderContract.EventsCalendar.TABLE_NAME, null, values);
            Log.i("WPIS DO BAZY", Long.toString(newRowID));
        }

    }
    //read events from database and save in timeInMillis and description

    protected void readEvents(MyDbHandler dbHelper, ArrayList<Long> timeInMillis, ArrayList<String> description){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=db.query(
                FeedReaderContract.EventsCalendar.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()){
            Log.i("WYPIS Z BAZY", Long.toString(cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract.EventsCalendar.COL1))));
            long pom1=cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract.EventsCalendar.COL1));
            timeInMillis.add(pom1);
            String pom2=cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.EventsCalendar.COL2));
            description.add(pom2);
        }
        cursor.close();
    }


    public long setBreedingDay(Context context){
        DatePickerDialog datePickerDialog;
        calendar= Calendar.getInstance();
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int month=calendar.get(Calendar.MONTH);
        int year=calendar.get(Calendar.YEAR);
        long setDateInMillis;                                                                       //set date in milliseconds


        //set breeding day with DatePickerDialog
        datePickerDialog=new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                calendar.set(mYear, mMonth, mDay);
                calendar.set(Calendar.MINUTE,0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.HOUR_OF_DAY,0);
                long timeinmillis=calendar.getTimeInMillis();
               // Log.i("WYBOR DATY", Long.toString(timeinmillis));


            }
        }, day, month, year);
        datePickerDialog.show();

        setDateInMillis=calendar.getTimeInMillis();
        Log.i("WYBOR DATY", Long.toString(setDateInMillis));
        return setDateInMillis;
    }


}

