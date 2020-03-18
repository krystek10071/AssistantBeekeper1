package com.example.assistantbeekeeper.weatherwitget;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private static final String TAG="NETWORK";

    private final static String WHEATHER_5_DAY_URL=
            "http://dataservice.accuweather.com/forecasts/v1/daily/5day/274231";                    //weather data for five day

    private final static String WHEATHER_1_DAY_URL=
            "http://dataservice.accuweather.com/forecasts/v1/daily/1day/267375";                    //weather data for one day

    private final static String API_KEY="bDGjT2yEX1J5CGJAC7lpPImJztQAmwcp";

    private final static String PARAM_KEY="apikey";
    private final static String PARAM_LANGUAGE="language";
    private final static String PARAM_DETAILS="details";
    private final static String PARAM_METRIC="metric";


    public static URL buildUrlWeatheForFiveDay(){
        Uri builUri=Uri.parse(WHEATHER_5_DAY_URL).buildUpon()
                .appendQueryParameter(PARAM_KEY, API_KEY).appendQueryParameter(PARAM_LANGUAGE, "pl")
                .appendQueryParameter(PARAM_DETAILS, "true")
                .appendQueryParameter(PARAM_METRIC, "true")
                .build();

        URL urlAddress=null;

        try{
            urlAddress=new URL(builUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "build Url Wather"+ urlAddress);
        return  urlAddress;
    }

        public static URL buildUrlWeatherForOneDay(){
        Uri buildUri=Uri.parse(WHEATHER_1_DAY_URL).buildUpon()
                .appendQueryParameter(PARAM_KEY, API_KEY).appendQueryParameter(PARAM_LANGUAGE, "pl")
                .appendQueryParameter(PARAM_DETAILS, "true")
                .appendQueryParameter(PARAM_METRIC, "true")
                .build();

        URL urlAddress=null;

        try {
            urlAddress=new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return urlAddress;
    }




     public static String getResponseFromUrl(URL url)throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        if(httpURLConnection.getResponseCode()==200){
            Log.i("CONECTION_ATEMP", "Conection_Successfull");
        }else
            Log.i("CONECTION_ATEMP", "Error_ Conection");


        try {
            InputStream responseBody = httpURLConnection.getInputStream();
            Scanner scanner = new Scanner(responseBody);
            scanner.useDelimiter("\\A");                                                    //??????

            if (scanner.hasNext()) {
                return scanner.next();
            } else {
                return null;
            }


        }finally {
            httpURLConnection.disconnect();
        }

    }
}
