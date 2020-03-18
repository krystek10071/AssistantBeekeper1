package com.example.assistantbeekeeper.severalDaysForecast.severalDaysPre;



import android.app.Activity;

import com.example.assistantbeekeeper.severalDaysForecast.alertRSOFagment.FragmentActivity.FragmentActivity;
import com.example.assistantbeekeeper.severalDaysForecast.alertRSOFagment.FragmentAlertsPre.AlertsPre;
import com.example.assistantbeekeeper.severalDaysForecast.severalDaysActivity.FiveDaysForecastActivity;

import java.net.URL;


public class FiveDaysForecast implements IFiveDaysForecast {



    @Override
    public void downloadWeatherData(FiveDaysForecastActivity fiveDaysForecastActivity) {

        //todo
        //create url address
        URL myUrl= DownloadData.createUrlAdress();
        //fetch data from webservice
        //DownloadData.FetchDataFromWebside(myUrl, fiveDaysForecastActivity);

    }

    @Override
    public void refreshRSOData() {

    }


}
