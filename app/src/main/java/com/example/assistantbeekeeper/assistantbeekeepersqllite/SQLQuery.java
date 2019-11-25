package com.example.assistantbeekeeper.assistantbeekeepersqllite;

public class SQLQuery {
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.EventsCalendar.TABLE_NAME + " (" +
                    FeedReaderContract.EventsCalendar._ID + " INTEGER PRIMARY KEY, " +
                    FeedReaderContract.EventsCalendar.COL1 + " TEXT, " +
                    FeedReaderContract.EventsCalendar.COL2 + " TEXT)";


    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.EventsCalendar.TABLE_NAME;

}
