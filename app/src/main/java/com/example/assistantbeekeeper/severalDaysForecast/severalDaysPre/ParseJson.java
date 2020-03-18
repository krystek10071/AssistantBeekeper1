package com.example.assistantbeekeeper.severalDaysForecast.severalDaysPre;

import com.example.assistantbeekeeper.severalDaysForecast.modelData.FiveDayWeatherDataClass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

 class ParseJson {

    private String resultData;

      ParseJson(String string){
         this.resultData=string;
     }

    /**
     * @return weatherDataObj List with weather objects
     */
     ArrayList<FiveDayWeatherDataClass> parseJsonToObjectList(){

         ArrayList<FiveDayWeatherDataClass> weatherDataObj=new ArrayList<>();
          if(resultData!=null){
            try {
                int lengthDailyForecast;
                JSONObject jsonObject=new JSONObject(resultData);
                JSONArray dailyForecastArray=jsonObject.getJSONArray("DailyForecasts");
                lengthDailyForecast=dailyForecastArray.length();

                for(int i=0; i<lengthDailyForecast; i++){
                    JSONObject resultObj=dailyForecastArray.getJSONObject(i);
                    long epochDate=resultObj.getLong("EpochDate")*1000;                       //*1000 po to by byla poprawna data
                    int whetherIcon=resultObj.getJSONObject("Day").getInt("Icon");
                    String iconPhrase=resultObj.getJSONObject("Day").getString("IconPhrase");
                    long tDay=Math.round(resultObj.getJSONObject("Temperature").getJSONObject("Maximum").getDouble("Value")) ;
                    long tNight=Math.round(resultObj.getJSONObject("Temperature").getJSONObject("Minimum").getDouble("Value"));
                    double windSpeed=resultObj.getJSONObject("Day").getJSONObject("Wind").getJSONObject("Speed").getDouble("Value");

                    FiveDayWeatherDataClass fiveWeatherData=new FiveDayWeatherDataClass(epochDate, whetherIcon,
                            iconPhrase, tDay, tNight, windSpeed);

                        weatherDataObj.add(fiveWeatherData);
                }
                return weatherDataObj;

            } catch (Exception e) {
                e.printStackTrace();
            }
          }


         return null;
     }
 }
