package com.example.assistantbeekeeper.weatherwitget;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.assistantbeekeeper.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class WeatherWidget {

    private static final String TAG="WeatherWidget";

    //build our URL Weather
    public static URL createUrlAddress(){
      URL weatherUrl=NetworkUtils.buildUrlWeatherForOneDay();
      Log.i(TAG, "Weather url: "+ weatherUrl);
      return weatherUrl;
    }

    //download weather details
    public static ArrayList<CurrentWeatherDataClass> FetchDataWether(URL weatherUrl, Activity activity, Context context){
       new DownloadWeatherDetails(activity, context).execute(weatherUrl);
        return null;
    }

    private static class DownloadWeatherDetails extends AsyncTask<URL, Void, String>{

       private ArrayList<CurrentWeatherDataClass> weatherDataClassArrayList=new ArrayList<>();


        //Constructor


        @SuppressLint("StaticFieldLeak")
        Activity activity;
        @SuppressLint("StaticFieldLeak")
        Context context;

        private DownloadWeatherDetails(Activity activity, Context context){

            this.activity=activity;
            this.context=context;
        }


        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {
           URL weatherUrl=urls[0];
           String weatherDetailsResult=null;

           try {
               weatherDetailsResult=NetworkUtils.getResponseFromUrl(weatherUrl);
           } catch (IOException e) {
               e.printStackTrace();
           }

           Log.i(TAG, " Weather details Result: "+ weatherDetailsResult);
            return weatherDetailsResult;
        }

        @Override
        protected void onPostExecute(String weatherDetailsResult) {

       //Parse Json Data
            if(weatherDetailsResult!=null && !weatherDetailsResult.equals("")){
                weatherDataClassArrayList=parseJSON_Data(weatherDetailsResult, weatherDataClassArrayList, activity, context);
            }


            super.onPostExecute(weatherDetailsResult);


        }
    }

    /**
     *
     * @param weatherDetailsResult   this is JSON Text from accuweather data
     * @param weatherDataClassArrayList empty list
     * @param activity  activity from MainActivity
     * @return List with weather object
     */

    private static ArrayList<CurrentWeatherDataClass> parseJSON_Data
            (String weatherDetailsResult, ArrayList<CurrentWeatherDataClass> weatherDataClassArrayList, Activity activity, Context context){

        TextView currentTemperature=activity.findViewById(R.id.current_temperature);
        TextView describeWeatherIcon=activity.findViewById(R.id.describe_weather_Icon);
        TextView windSpeedTextView=activity.findViewById(R.id.wind_speed);
        TextView pop=activity.findViewById(R.id.POP);
        TextView sensibleTemperature=activity.findViewById(R.id.sensible_temperature);



        if(weatherDataClassArrayList!=null){
            weatherDataClassArrayList.clear();
        }

        if(weatherDetailsResult!=null){
            try {
                JSONObject jsonObject=new JSONObject(weatherDetailsResult);
                JSONArray dailyForecastArray=jsonObject.getJSONArray("DailyForecasts");
                JSONObject resultObj=dailyForecastArray.getJSONObject(0);


                        long epochDate=resultObj.getLong("EpochDate");
                        int whetherIcon=resultObj.getJSONObject("Day").getInt("Icon");
                        String iconPhrase=resultObj.getJSONObject("Day").getString("IconPhrase");
                        double temperature=resultObj.getJSONObject("Temperature").getJSONObject("Maximum").getDouble("Value");
                        double realFeelTemperature=resultObj.getJSONObject("RealFeelTemperature").getJSONObject("Maximum").getDouble("Value");
                        double windSpeed=resultObj.getJSONObject("Day").getJSONObject("Wind").getJSONObject("Speed").getDouble("Value");
                        int precipitationProbality=resultObj.getJSONObject("Day").getInt("PrecipitationProbability");


                //set Text View with Main Activity
                        currentTemperature.setText(String.valueOf(temperature));
                        describeWeatherIcon.setText(iconPhrase);
                        windSpeedTextView.setText(String.valueOf(windSpeed));
                        pop.setText(String.valueOf(precipitationProbality));
                        sensibleTemperature.setText(String.valueOf(realFeelTemperature));



                        CurrentWeatherDataClass currentWeatherDataClass=new CurrentWeatherDataClass(epochDate, null, whetherIcon,
                                iconPhrase, temperature, realFeelTemperature, windSpeed, precipitationProbality, null);

                        if (weatherDataClassArrayList != null) {
                            weatherDataClassArrayList.add(currentWeatherDataClass);
                        }




                return weatherDataClassArrayList;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
            return null;

    }

}
