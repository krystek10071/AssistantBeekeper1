package com.example.assistantbeekeeper.weatherwitget;

public class CurrentWeatherDataClass extends WeatherDataGeneral{

   //Fields
    private String currentLocation;


    public CurrentWeatherDataClass( long epochDate, String location, int whetherIcon, String iconPhrase, double temperature,
                                   double realFeelTemperature, double windSpeed, int precipitationProbality, String currentLocation) {

        super( epochDate, location, whetherIcon, iconPhrase,
                temperature, realFeelTemperature, windSpeed, precipitationProbality);
        this.currentLocation=currentLocation;

    }

    //Setters && Getters
    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }
}
