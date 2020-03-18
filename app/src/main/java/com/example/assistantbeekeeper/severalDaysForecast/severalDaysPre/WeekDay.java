package com.example.assistantbeekeeper.severalDaysForecast.severalDaysPre;

import java.util.Calendar;

 public class WeekDay {



     public String getNameWeekday(long timeInMillis) {
        String nameWeekday;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        int numberDayOfWeek= calendar.get(Calendar.DAY_OF_WEEK);
        nameWeekday=NameWeekday(numberDayOfWeek);

        return nameWeekday;
    }


    private String NameWeekday(int number){
        switch (number){
            case 2:
                return "pon.";
            case 3:
                return "wt.";
            case 4:
                return "śr.";
            case 5:
                return "czw.";
            case 6:
                return "pt.";
            case 7:
                return "sob";
            case 1:
                return "niedz";
        }
        return null;
    }


}
