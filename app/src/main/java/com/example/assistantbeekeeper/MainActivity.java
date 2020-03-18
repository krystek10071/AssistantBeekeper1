package com.example.assistantbeekeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assistantbeekeeper.weatherwitget.CurrentWeatherDataClass;
import com.example.assistantbeekeeper.severalDaysForecast.severalDaysActivity.FiveDaysForecastActivity;
import com.example.assistantbeekeeper.weatherwitget.WeatherWidget;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="MainActivity";

    //UI Elements
     Button breedingButton;
     Button fiveDaysForecast;
     TextView currentLocation;
     TextView currentTemperature;
     TextView describeWeatherIcon;
     TextView windSpeed;
     TextView pop;
     TextView sensibleTemperature;
     ImageView weatherIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        URL weatherOneDayData;
        ArrayList<CurrentWeatherDataClass>  listWeatherOneDay;
        currentLocation=findViewById(R.id.current_location);
        currentTemperature=findViewById(R.id.current_temperature);
        describeWeatherIcon=findViewById(R.id.describe_weather_Icon);
        windSpeed=findViewById(R.id.wind_speed);
        pop=findViewById(R.id.POP);
        sensibleTemperature=findViewById(R.id.sensible_temperature);
        fiveDaysForecast=findViewById(R.id.five_days_forecast_button);
        breedingButton= findViewById(R.id.breedingButton);
        weatherIcon=findViewById(R.id.icon_weather);


        breedingButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(), Breeding.class);
                startActivity(intent);
            }
        });

        fiveDaysForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), FiveDaysForecastActivity.class);
                startActivity(intent);
            }
        });


        //Todo
        weatherOneDayData= WeatherWidget.createUrlAddress();
       //listWeatherOneDay=WeatherWidget.FetchDataWether(weatherOneDayData,this, this);
    }
    
    public void setWeatherIcon(ArrayList<CurrentWeatherDataClass> data, int index){
        weatherIcon.setImageDrawable(getResForWeatherIcon(data, index));
    }

    private Drawable getResForWeatherIcon(ArrayList<CurrentWeatherDataClass> data, int index) {

        if(data.get(index).getWeatherIcon()==1 || data.get(index).getWeatherIcon()==2 || data.get(index).getWeatherIcon()==3 ){
            return ResourcesCompat.getDrawable(getResources(),R.drawable.iconfinder_partly_cloudy, null);
        }


        else if(data.get(index).getWeatherIcon()==4 || data.get(index).getWeatherIcon()==5 || data.get(index).getWeatherIcon()==6){
            //mostly cloud
            return ResourcesCompat.getDrawable(getResources(),R.drawable.iconfinder_partly_cloudy, null);

        }

        else if(data.get(index).getWeatherIcon()==7 || data.get(index).getWeatherIcon()==8 || data.get(index).getWeatherIcon()==11){
            //cloud
            return ResourcesCompat.getDrawable(getResources(),R.drawable.iconfinder_overcast, null);
        }

        else if(data.get(index).getWeatherIcon()==12 || data.get(index).getWeatherIcon()==13 || data.get(index).getWeatherIcon()==14)
        {
            //showers
            return ResourcesCompat.getDrawable(getResources(),R.drawable.iconfinder_showers, null);

        }

        else if(data.get(index).getWeatherIcon()==18){
            //rain

            return ResourcesCompat.getDrawable(getResources(),R.drawable.iconfinder_rain_icon, null);
        }

        else if(data.get(index).getWeatherIcon()==25 || data.get(index).getWeatherIcon()==26 || data.get(index).getWeatherIcon()==29){
            //rain && snow
            return ResourcesCompat.getDrawable(getResources(),R.drawable.iconfinder_foggy_3741362, null);
        }

        return null;
    }


}
