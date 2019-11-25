package com.example.assistantbeekeeper.assistantbeekeepersqllite;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    private FeedReaderContract(){}

    //Table EVEBTS_CALENDAR
    public static class EventsCalendar implements BaseColumns{
        public static final String TABLE_NAME="EVENTS_CALENDAR";
        public static final String COL1="DATE";
        public static final String COL2="ACTION";

    }

    //Table Test_Table
    public static class TestTable implements BaseColumns{
        public static final String TABLE_NAME="TEST_TABLE";
        public static final String COL1="DESCRIPTION" ;
    }





}
