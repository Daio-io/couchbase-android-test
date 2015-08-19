package io.daio.couchy.managers;

import android.content.Context;

public class ThingsDatabase {

    private static CouchyDatabase database;

    public static CouchyDatabase getInstance(Context context) {
        if (database == null) {
            database = new CouchyDatabase("things", context);
            return database;
        }
        return database;
    }

}
