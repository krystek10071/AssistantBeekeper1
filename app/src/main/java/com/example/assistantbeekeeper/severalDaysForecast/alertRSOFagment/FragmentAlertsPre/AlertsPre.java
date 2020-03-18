package com.example.assistantbeekeeper.severalDaysForecast.alertRSOFagment.FragmentAlertsPre;


import android.util.Log;

import com.example.assistantbeekeeper.severalDaysForecast.alertRSOFagment.FragmentActivity.FragmentActivity;

import java.net.URL;
import java.util.ArrayList;

public class AlertsPre implements IAlertsPre {
    private static final String TAG="ALERTS_PRE";

    @Override
    public void FethRSOData(FragmentActivity fragmentRsoActivity, ArrayList<String> listRsoParameter) {

        //todo
       ArrayList<URL> listUrl=new ArrayList<>();

        int lenghtListParameter=listRsoParameter.size();

        for (int i=0; i<lenghtListParameter; i++){
            URL urlAdress;
            urlAdress=DownloadRSOData.createUrlAdress("wszystkie", listRsoParameter.get(i));
            listUrl.add(urlAdress);
        }

        if(!listRsoParameter.isEmpty()){
            DownloadRSOData.fetchRSODataFromWebsite(listUrl, fragmentRsoActivity);
        }else
        {
            Log.i(TAG, "List parameter is empty");
        }


    }
}
