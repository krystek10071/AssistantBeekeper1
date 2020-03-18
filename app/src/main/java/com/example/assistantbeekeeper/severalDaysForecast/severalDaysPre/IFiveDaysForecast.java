package com.example.assistantbeekeeper.severalDaysForecast.severalDaysPre;

import com.example.assistantbeekeeper.severalDaysForecast.alertRSOFagment.FragmentActivity.FragmentActivity;
import com.example.assistantbeekeeper.severalDaysForecast.severalDaysActivity.FiveDaysForecastActivity;

public interface IFiveDaysForecast {

    void downloadWeatherData(FiveDaysForecastActivity fiveDaysForecastActivity);


    void refreshRSOData();
}
