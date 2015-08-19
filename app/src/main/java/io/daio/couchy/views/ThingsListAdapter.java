package io.daio.couchy.views;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import io.daio.couchy.models.ThingModel;

public class ThingsListAdapter extends ArrayAdapter<String> {

    public ThingsListAdapter(Context context, int resource, ArrayList<String> initialList) {
        super(context, resource);
        EventBus.getDefault().register(this);
        this.setNotifyOnChange(true);
        if (initialList != null) {
            this.addAll(initialList);
        }
    }

    public void insert(String key, String value) {
        if (key != null && value != null) {
            this.add(value);
        }
    }

    public void onEvent(String action) {
        if (action.equals("DROP")) {
            this.clear();
        }
    }

    public void onEvent(ThingModel thing) {
        this.insert(thing.getKey(), thing.getValue());
    }

}
