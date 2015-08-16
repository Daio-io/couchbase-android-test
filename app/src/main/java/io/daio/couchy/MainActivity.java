package io.daio.couchy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import io.daio.couchy.managers.CouchyDatabase;
import io.daio.couchy.views.ThingsListAdapter;


public class MainActivity extends Activity {
    @Bind(R.id.thingsList) ListView listy;
    @Bind(R.id.thingField) EditText thingsField;

    CouchyDatabase database;
    ThingsListAdapter things;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        database = new CouchyDatabase("test", getApplicationContext());
        things = new ThingsListAdapter(getApplicationContext(),
                R.layout.list_item);
        listy.setAdapter(things);
    }

    @OnClick(R.id.addBtn)
    public void addThing() {
        String thingValue = thingsField.getText().toString();
        HashMap<String, Object> thing = new HashMap<>();

        thing.put("Test", thingValue);

        database.createDocument(thing);

        EventBus.getDefault().post(thingValue);
    }

}
