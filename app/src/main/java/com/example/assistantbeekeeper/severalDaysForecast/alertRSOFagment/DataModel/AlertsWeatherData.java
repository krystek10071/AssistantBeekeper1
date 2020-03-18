package com.example.assistantbeekeeper.severalDaysForecast.alertRSOFagment.DataModel;

public class AlertsWeatherData {

    private String title;
    private String provincy;
    private String dateStart;
    private String dateEnd;
    private String descriptioon;

    public String getTitle() {
        return title;
    }

    public AlertsWeatherData(String title, String provincy, String dateStart, String dateEnd, String descriptioon) {
        this.title = title;
        this.provincy = provincy;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.descriptioon = descriptioon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProvincy() {
        return provincy;
    }

    public void setProvincy(String provincy) {
        this.provincy = provincy;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDescriptioon() {
        return descriptioon;
    }

    public void setDescriptioon(String descriptioon) {
        this.descriptioon = descriptioon;
    }
}
