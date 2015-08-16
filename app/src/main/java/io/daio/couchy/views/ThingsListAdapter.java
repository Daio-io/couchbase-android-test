package io.daio.couchy.views;

import android.content.Context;
import android.widget.ArrayAdapter;
import de.greenrobot.event.EventBus;

/**
 * Created by willid98 on 31/05/2015.
 */
public class ThingsListAdapter extends ArrayAdapter {

    public ThingsListAdapter(Context context, int resource) {
        super(context, resource);
        EventBus.getDefault().register(this);
    }

    public void onEvent(String thing) {
        this.add(thing);
        this.notifyDataSetChanged();
    }

}
