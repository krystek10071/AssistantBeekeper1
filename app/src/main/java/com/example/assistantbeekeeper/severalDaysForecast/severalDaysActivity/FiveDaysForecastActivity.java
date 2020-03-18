package com.example.assistantbeekeeper.severalDaysForecast.severalDaysActivity;



import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assistantbeekeeper.R;
import com.example.assistantbeekeeper.severalDaysForecast.alertRSOFagment.FragmentActivity.FragmentActivity;
import com.example.assistantbeekeeper.severalDaysForecast.alertRSOFagment.FragmentAlertsPre.AlertsPre;
import com.example.assistantbeekeeper.severalDaysForecast.modelData.FiveDayWeatherDataClass;
import com.example.assistantbeekeeper.severalDaysForecast.severalDaysPre.FiveDaysForecast;
import com.example.assistantbeekeeper.severalDaysForecast.severalDaysPre.WeekDay;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

public class FiveDaysForecastActivity extends AppCompatActivity implements IFiveDaysForecastActivity {

    private static final String TAG="FIVE_FORECAST_ACTIVITY";
    TextView weekday1, weekday2, weekday3, weekday4, weekday5;
    TextView describeWeather1, describeWeather2, describeWeather3, describeWeather4, describeWeather5;
    TextView tDay1, tDay2, tDay3, tDay4, tDay5;
    TextView tNight1, tNight2, tNight3, tNight4, tNight5;
    TextView windSpeed1, windSpeed2, windSpeed3, windSpeed4, windSpeed5;
    ImageView weatherIcon1, weatherIcon2, weatherIcon3, weatherIcon4, weatherIcon5;
    boolean general, meteorological, hydrological, trafficInformation;

    FiveDaysForecast fiveDaysForecast=new FiveDaysForecast();
    AlertsPre alertsPre=new AlertsPre();
    ArrayList<String> rsoParameters=new ArrayList<>();



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_days_forecast);

        general=false;
        meteorological=false;
        hydrological=false;
        trafficInformation=false;

        //init TextView components
        initComponentView();
        //TODO
        //fiveDaysForecast.downloadWeatherData(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.five_days_forecast_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){


        switch (item.getItemId()){
            case R.id.refresh_id_item:
                FragmentActivity fragmentRSOActivity=(FragmentActivity) getSupportFragmentManager().findFragmentById(R.id.fragment_alerts_activity);
                rsoParameters.clear();

                if(fragmentRSOActivity!=null && fragmentRSOActivity.isInLayout()){
                   // fragmentRSOActivity.refreshRSOData(this, general, meteorological, hydrological, trafficInformation);
                    if(general){
                        rsoParameters.add("ogolne");
                    }

                    if(meteorological){
                        rsoParameters.add("meteorologiczne");
                    }

                    if(hydrological){
                        rsoParameters.add("hydrologiczne");
                    }

                    if(trafficInformation){
                        rsoParameters.add("informacje-drogowe");
                    }

                     fragmentRSOActivity.refreshRSOData(this, rsoParameters);
                }
                return true;

            case R.id.item1:
                if(!item.isChecked()){
                    item.setChecked(true);
                    general=true;
                }
                else{
                    item.setChecked(false);
                    general=false;
                }
                break;

            case R.id.item2:
                if(!item.isChecked()){
                    item.setChecked(true);
                    meteorological=true;
                }
                else{
                    item.setChecked(false);
                    meteorological=false;
                }
                break;

            case R.id.item3:
                if(!item.isChecked()){
                    item.setChecked(true);
                    hydrological=true;
                }
                else{
                    item.setChecked(false);
                    hydrological=false;
                }
                break;

            case R.id.item4:
                if(!item.isChecked()){
                    item.setChecked(true);
                    trafficInformation=true;
                }
                else{
                    item.setChecked(false);
                    trafficInformation=false;
                }
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void initComponentView() {
        weekday1=findViewById(R.id.weekDay1);
        weekday2=findViewById(R.id.weekday2);
        weekday3=findViewById(R.id.weekday3);
        weekday4=findViewById(R.id.weekday4);
        weekday5=findViewById(R.id.weekday5);
        describeWeather1=findViewById(R.id.describe_weather_icon1);
        describeWeather2=findViewById(R.id.describe_weather_icon2);
        describeWeather3=findViewById(R.id.describe_weather_icon3);
        describeWeather4=findViewById(R.id.describe_weather_icon4);
        describeWeather5=findViewById(R.id.describe_weather_icon5);
        tDay1=findViewById(R.id.t_day1);
        tDay2=findViewById(R.id.t_day2);
        tDay3=findViewById(R.id.t_day3);
        tDay4=findViewById(R.id.t_day4);
        tDay5=findViewById(R.id.t_day5);
        tNight1=findViewById(R.id.t_night1);
        tNight2=findViewById(R.id.t_night2);
        tNight3=findViewById(R.id.t_night3);
        tNight4=findViewById(R.id.t_night4);
        tNight5=findViewById(R.id.t_night5);
        windSpeed1=findViewById(R.id.wind_speed1);
        windSpeed2=findViewById(R.id.wind_speed2);
        windSpeed3=findViewById(R.id.wind_speed3);
        windSpeed4=findViewById(R.id.wind_speed4);
        windSpeed5=findViewById(R.id.wind_speed5);
        weatherIcon1=findViewById(R.id.weather_icon1);
        weatherIcon2=findViewById(R.id.weather_icon2);
        weatherIcon3=findViewById(R.id.weather_icon3);
        weatherIcon4=findViewById(R.id.weather_icon4);
        weatherIcon5=findViewById(R.id.weather_icon5);
    }


    public void setData(ArrayList<FiveDayWeatherDataClass> data) {

        try {
            weekDaySetText(data);
            setWindSpeed(data);
            setDescribeWeather(data);
            setTdayAndTnight(data);
            setWeatherIcon(data);

        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "Data has not been downloaded");
            Toast.makeText(this, "Nie udało się pobrać danych z serwera", Toast.LENGTH_SHORT).show();
        }

    }

    private void weekDaySetText(ArrayList<FiveDayWeatherDataClass> data){
        weekday1.setText(setWeekday(data,0));
        weekday2.setText(setWeekday(data,1));
        weekday3.setText(setWeekday(data,2));
        weekday4.setText(setWeekday(data,3));
        weekday5.setText(setWeekday(data,4));
    }


    private void setWindSpeed(ArrayList<FiveDayWeatherDataClass> data){
        String s1= data.get(0).getWindSpeed() +"\nkm/h";
        String s2= data.get(1).getWindSpeed() +"\nkm/h";
        String s3= data.get(2).getWindSpeed() +"\nkm/h";
        String s4= data.get(3).getWindSpeed() +"\nkm/h";
        String s5= data.get(4).getWindSpeed() +"\nkm/h";

        windSpeed1.setText(s1);
        windSpeed2.setText(s2);
        windSpeed3.setText(s3);
        windSpeed4.setText(s4);
        windSpeed5.setText(s5);

    }

    private void setDescribeWeather(ArrayList<FiveDayWeatherDataClass> data){
        describeWeather1.setText(data.get(0).getIconPhrase());
        describeWeather2.setText(data.get(1).getIconPhrase());
        describeWeather3.setText(data.get(2).getIconPhrase());
        describeWeather4.setText(data.get(3).getIconPhrase());
        describeWeather5.setText(data.get(4).getIconPhrase());

    }

    private void setTdayAndTnight(ArrayList<FiveDayWeatherDataClass> data){

        String s1=data.get(0).gettDay()+"°";
        String s2=data.get(1).gettDay()+"°";
        String s3=data.get(2).gettDay()+"°";
        String s4=data.get(3).gettDay()+"°";
        String s5=data.get(4).gettDay()+"°";
        String s6=data.get(0).gettNight()+"°";
        String s7=data.get(1).gettNight()+"°";
        String s8=data.get(2).gettNight()+"°";
        String s9=data.get(4).gettNight()+"°";
        String s10=data.get(4).gettNight()+"°";


        tDay1.setText(s1);
        tDay2.setText(s2);
        tDay3.setText(s3);
        tDay4.setText(s4);
        tDay5.setText(s5);
        tNight1.setText(s6);
        tNight2.setText(s7);
        tNight3.setText(s8);
        tNight4.setText(s9);
        tNight5.setText(s10);
    }

    private String setWeekday(ArrayList<FiveDayWeatherDataClass> data, int index){
        String stringDay;
        WeekDay weekDay=new WeekDay();
        stringDay=weekDay.getNameWeekday(data.get(index).getEpochDate());
        return  stringDay;
    }

    private void setWeatherIcon(ArrayList<FiveDayWeatherDataClass> data){
        weatherIcon1.setImageDrawable(getResForWeatherIcon(data, 0));
        weatherIcon2.setImageDrawable(getResForWeatherIcon(data, 1));
        weatherIcon3.setImageDrawable(getResForWeatherIcon(data, 2));
        weatherIcon4.setImageDrawable(getResForWeatherIcon(data, 3));
        weatherIcon5.setImageDrawable(getResForWeatherIcon(data, 4));

    }

    public Drawable getResForWeatherIcon(ArrayList<FiveDayWeatherDataClass> data, int index){
        if(data.get(index).getWheatherIcon()==1 || data.get(index).getWheatherIcon()==2 || data.get(index).getWheatherIcon()==3 ){
            return ResourcesCompat.getDrawable(getResources(),R.drawable.iconfinder_partly_cloudy, null);
        }


        else if(data.get(index).getWheatherIcon()==4 || data.get(index).getWheatherIcon()==5 || data.get(index).getWheatherIcon()==6){
            //mostly cloud
            return ResourcesCompat.getDrawable(getResources(),R.drawable.iconfinder_partly_cloudy, null);

        }

        else if(data.get(index).getWheatherIcon()==7 || data.get(index).getWheatherIcon()==8 || data.get(index).getWheatherIcon()==11){
            //cloud
            return ResourcesCompat.getDrawable(getResources(),R.drawable.iconfinder_overcast, null);
        }

        else if(data.get(index).getWheatherIcon()==12 || data.get(index).getWheatherIcon()==13 || data.get(index).getWheatherIcon()==14)
               {
            //showers
            return ResourcesCompat.getDrawable(getResources(),R.drawable.iconfinder_showers, null);

        }

        else if(data.get(index).getWheatherIcon()==18){
            //rain

            return ResourcesCompat.getDrawable(getResources(),R.drawable.iconfinder_rain_icon, null);
        }

        else if(data.get(index).getWheatherIcon()==25 || data.get(index).getWheatherIcon()==26 || data.get(index).getWheatherIcon()==29){
            //rain && snow
            return ResourcesCompat.getDrawable(getResources(),R.drawable.iconfinder_foggy_3741362, null);
        }

        return null;
    }


}
