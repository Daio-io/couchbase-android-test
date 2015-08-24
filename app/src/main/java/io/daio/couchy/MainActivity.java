package io.daio.couchy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import com.couchbase.lite.QueryRow;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import io.daio.couchy.managers.CouchyDatabase;
import io.daio.couchy.managers.ThingsDatabase;
import io.daio.couchy.models.ThingModel;
import io.daio.couchy.views.ThingsListAdapter;


public class MainActivity extends Activity {
    private CouchyDatabase database;
    @Bind(R.id.thingsList)
    ListView listy;
    @Bind(R.id.thingField)
    EditText thingsField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        ThingsListAdapter things = new ThingsListAdapter(getApplicationContext(),
                R.layout.list_item, getThingsList());

        listy.setAdapter(things);
    }

    @OnClick(R.id.addBtn)
    public void addThing() {
        String thingValue = thingsField.getText().toString();
        if (!thingValue.isEmpty()) {
            HashMap<String, Object> thingDoc = new HashMap<>();
            thingDoc.put("Thing", thingValue);
            ThingModel thingModel = new ThingModel("Thing", thingValue);

            database.createDocument(thingDoc);
            EventBus.getDefault().post(thingModel);
        }
    }

    @OnClick(R.id.dropBtn)
    public void dropData() {
        database.deleteAllDocuments();
        EventBus.getDefault().post("DROP");
    }

    private ArrayList<String> getThingsList() {
        database = ThingsDatabase.getInstance(getApplicationContext());

        ArrayList<QueryRow> allThings = database.getAllDocuments();
        ArrayList<String> thingsList = new ArrayList<>();

        for (QueryRow thing : allThings) {
            thingsList.add(thing.getDocument().getProperty("Thing").toString());
        }
        return thingsList;
    }

}
