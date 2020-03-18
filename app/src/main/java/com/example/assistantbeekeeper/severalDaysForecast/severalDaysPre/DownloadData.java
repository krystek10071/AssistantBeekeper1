package com.example.assistantbeekeeper.severalDaysForecast.severalDaysPre;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.example.assistantbeekeeper.severalDaysForecast.severalDaysActivity.FiveDaysForecastActivity;
import com.example.assistantbeekeeper.severalDaysForecast.modelData.FiveDayWeatherDataClass;
import com.example.assistantbeekeeper.weatherwitget.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;

 class DownloadData {

    private static final String Tag="DownloadData";

     static URL createUrlAdress(){
        URL weatherUrl= NetworkUtils.buildUrlWeatheForFiveDay();
        Log.i(Tag, "Wheather utils"+ weatherUrl);
        return weatherUrl;
    }


     static void FetchDataFromWebside(URL url, FiveDaysForecastActivity fiveDaysForecastActivity){
        new DownloadWeatherDetails(fiveDaysForecastActivity).execute(url);
   }


   private static class DownloadWeatherDetails extends AsyncTask<URL, Void, String>{

         @SuppressLint("StaticFieldLeak")
         FiveDaysForecastActivity fiveDaysForecastActivity;

         DownloadWeatherDetails (FiveDaysForecastActivity fiveDaysForecastActivity){
             this.fiveDaysForecastActivity=fiveDaysForecastActivity;
         }

        @Override
        protected void  onPreExecute(){
            super.onPreExecute();
        }


       //fetch data from server Weather for fiveDaysWeather
       @Override
       protected String doInBackground(URL... urls) {
            URL weatherurl=urls[0];
            String weatherDetailResult=null;

            try{
                weatherDetailResult=NetworkUtils.getResponseFromUrl(weatherurl);

            } catch (Exception e) {
                e.printStackTrace();
            }
           Log.i(Tag, " Weather details Result: "+ weatherDetailResult);

           return weatherDetailResult;
       }

       @Override
       protected void onPostExecute(String weatherDetailResult){


             FiveDaysForecastActivity fiveDaysForecast=fiveDaysForecastActivity;
            ArrayList<FiveDayWeatherDataClass> fiveDayWeatherData;
            ParseJson parseJson=new ParseJson(weatherDetailResult);
            fiveDayWeatherData=parseJson.parseJsonToObjectList();



            //send data to FiveDaysForecastActivity
           fiveDaysForecast.setData(fiveDayWeatherData);
       }
   }





}
