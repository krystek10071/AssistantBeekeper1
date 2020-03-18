package com.example.assistantbeekeeper.severalDaysForecast.alertRSOFagment.FragmentAlertsPre;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateParser {
    private static final String TAG="DateParser";




    public String parseData(String patternBeforeConversion ,String patternAfterConversion, String dateInString){

        String resultStringData=null;

        Date date;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(patternBeforeConversion, Locale.getDefault());
        SimpleDateFormat formatForConvertion=new SimpleDateFormat(patternAfterConversion, Locale.getDefault());

        try {
            date=simpleDateFormat.parse(dateInString);
            resultStringData=formatForConvertion.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
            Log.i(TAG, "Error parse Date");
        }

        if(resultStringData!=null){
            return resultStringData;
        }

        return "";
    }



}
