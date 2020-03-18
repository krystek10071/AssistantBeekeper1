package com.example.assistantbeekeeper.severalDaysForecast.alertRSOFagment.FragmentAlertsPre;

import android.net.Uri;

import java.net.URL;

public class NetworkUrlAdress {

    private final static String TAG="NETWORK URL ADRESS";
    private final static String ALERTS_URL="http://komunikaty.tvp.pl/komunikatyxml/";
    private final static String DOLNOSLASKIE="dolnoslaskie";
    private final static String KUJAWSKO_POMORSKIE="kujawsko-pomorskie";
    private final static String LUBELSKIE="lubelskie";
    private final static String LUBUSKIE="lubuskie";
    private final static String LODZKIE="lodzkie";
    private final static String MALOPOLSKIE="malopolskie";
    private final static String MAZOWIECKIE="mazowieckie";
    private final static String OPOLSKIE="opolskie";
    private final static String PODKARPACKIE="podkarpackie";
    private final static String PODLASKIE="podlaskie";
    private final static String POMORSKIE="pomorskie";
    private final static String SLASKIE="slaskie";
    private final static String SWIETOKRZYSKIE="swietokrzyskie";
    private final static String WARMINSKI_MAZURSKIE="warminsko-mazurkie";
    private final static String WIELKOPOLSKIE="wielkopolskie";
    private final static String ZACHODNIOPOMORSKIE="zachodniopomorskie";


    public static URL buildRsoAdress(String provincy, String category){
        Uri buildUri=Uri.parse(ALERTS_URL).buildUpon().appendPath(provincy)
                .appendPath(category).appendEncodedPath("0?_format=json")
                .build();

        URL urlAdress=null;

        try{
            urlAdress=new URL(buildUri.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return urlAdress;
    }


}
