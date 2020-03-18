package com.example.assistantbeekeeper.severalDaysForecast.severalDaysActivity;


import android.view.Menu;

import com.example.assistantbeekeeper.severalDaysForecast.modelData.FiveDayWeatherDataClass;

import java.util.ArrayList;

public interface IFiveDaysForecastActivity {

    void initComponentView();
    void setData(ArrayList<FiveDayWeatherDataClass> data);
}
