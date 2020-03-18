package com.example.assistantbeekeeper.severalDaysForecast.modelData;


public class FiveDayWeatherDataClass  {

    private long epochDate;
    private int weatherIcon;
    private String iconPhrase;
    private long tDay;
    private long tNight;
    private double windSpeed;

    public FiveDayWeatherDataClass(long epochDate, int weatherIcon, String iconPhrase, long tDay, long tNight, double windSpeed) {
        this.epochDate = epochDate;
        this.weatherIcon = weatherIcon;
        this.iconPhrase = iconPhrase;
        this.tDay = tDay;
        this.tNight = tNight;
        this.windSpeed = windSpeed;
    }

    public long getEpochDate() {
        return epochDate;
    }

    public void setEpochDate(long epochDate) {
        this.epochDate = epochDate;
    }

    public int getWheatherIcon() {
        return weatherIcon;
    }

    public void setWheatherIcon(int wheatherIcon) {
        this.weatherIcon = wheatherIcon;
    }

    public String getIconPhrase() {
        return iconPhrase;
    }

    public void setIconPhrase(String iconPhrase) {
        this.iconPhrase = iconPhrase;
    }

    public long gettDay() {
        return tDay;
    }

    public void settDay(int tDay) {
        this.tDay = tDay;
    }

    public long gettNight() {
        return tNight;
    }

    public void settNight(int tNight) {
        this.tNight = tNight;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
}
