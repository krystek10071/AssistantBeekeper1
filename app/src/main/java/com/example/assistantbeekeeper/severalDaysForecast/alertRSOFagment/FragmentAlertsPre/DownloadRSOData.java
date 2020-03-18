package com.example.assistantbeekeeper.severalDaysForecast.alertRSOFagment.FragmentAlertsPre;

import android.os.AsyncTask;
import android.util.Log;

import com.example.assistantbeekeeper.severalDaysForecast.alertRSOFagment.DataModel.AlertsWeatherData;
import com.example.assistantbeekeeper.severalDaysForecast.alertRSOFagment.FragmentActivity.FragmentActivity;
import com.example.assistantbeekeeper.weatherwitget.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;

 class DownloadRSOData {

    private static final String TAG="DownloadRsoData";


     static URL createUrlAdress(String provincy, String category){
         return NetworkUrlAdress.buildRsoAdress(provincy, category);
    }


     static void fetchRSODataFromWebsite(ArrayList<URL> listUrlAdress, FragmentActivity fragmentActivity){

         //noinspection unchecked
         new DownloadRSODetails(fragmentActivity).execute(listUrlAdress);
    }




    public static class DownloadRSODetails extends AsyncTask<ArrayList<URL>, Void, ArrayList<String>> {

        private FragmentActivity fragmentActivity;

        private DownloadRSODetails(FragmentActivity fragmentActivity){
            this.fragmentActivity=fragmentActivity;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         *
         * @param urls adress to WebsSite with Data
         * @return String resultRSOData with RSO messages
         */

        @SafeVarargs
        @Override
        protected final ArrayList<String> doInBackground(ArrayList<URL>... urls) {
           ArrayList<String> resultRsoData=new ArrayList<>();
           ArrayList<URL> urlAdresses=urls[0];
            int lengthUrlAdresses=urlAdresses.size();



            try{
                for(int i=0; i<lengthUrlAdresses; i++){
                    String result;
                    result=NetworkUtils.getResponseFromUrl(urlAdresses.get(i));
                    resultRsoData.add(result);
                }


                //resultRSOData=NetworkUtils.getResponseFromUrl(urlAdress);
                //resultRSOData1=NetworkUtils.getResponseFromUrl(urlAdress2);
            } catch (Exception e) {
                e.printStackTrace();
                e.getMessage();
            }


            for(int i=0; i<lengthUrlAdresses; i++){
                Log.i(TAG, "RSO Data result" + resultRsoData.get(i));
            }



            return resultRsoData;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            FragmentActivity fragmentRso=fragmentActivity;
            ArrayList<AlertsWeatherData> alertsListPom;
            ArrayList<AlertsWeatherData> alertsResult=new ArrayList<>();
            int lenghtResultArrayList=result.size();

            for(int i=0; i<lenghtResultArrayList; i++){
                RSOParserJsonData parserJsonData= new RSOParserJsonData(result.get(i));
                alertsListPom=parserJsonData.ParseJsonToListObj();

                if(!alertsListPom.isEmpty()){
                    alertsResult.addAll(alertsListPom);
                }
            }
            fragmentRso.setRecycleView(alertsResult);

            if(alertsResult.isEmpty()){
                fragmentRso.displayMessage();
            }
        }
    }
}
