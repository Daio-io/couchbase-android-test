package io.daio.couchy.views;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.couchbase.lite.QueryRow;

import java.util.ArrayList;
import java.util.HashMap;

import de.greenrobot.event.EventBus;
import io.daio.couchy.managers.CouchyDatabase;
import io.daio.couchy.models.ThingModel;

public class ThingsListAdapter extends ArrayAdapter {

    CouchyDatabase database;

    public ThingsListAdapter(Context context, int resource) {
        super(context, resource);
        EventBus.getDefault().register(this);
        this.setNotifyOnChange(true);
        database = new CouchyDatabase("test", context);
        ArrayList<QueryRow> allThings = database.getAllDocuments();

        for (QueryRow thing : allThings) {
            this.add(thing.getDocument().getProperty("Test"));
        }
    }

    public void insert(String key, String value) {
        if (key != null && value != null) {
            HashMap<String, Object> thingDoc = new HashMap<>();
            thingDoc.put(key, value);
            database.createDocument(thingDoc);
            this.add(value);
        }
    }

    public void onEvent(String action) {
        if (action.equals("DROP")) {
            this.clear();
            database.deleteAllDocuments();
        }
    }

    public void onEvent(ThingModel thing) {
        this.insert(thing.getKey(), thing.getValue());
    }

}
