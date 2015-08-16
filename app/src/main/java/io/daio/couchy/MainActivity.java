package io.daio.couchy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import io.daio.couchy.models.ThingModel;
import io.daio.couchy.views.ThingsListAdapter;


public class MainActivity extends Activity {
    @Bind(R.id.thingsList) ListView listy;
    @Bind(R.id.thingField) EditText thingsField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ThingsListAdapter things = new ThingsListAdapter(getApplicationContext(),
                R.layout.list_item);
        listy.setAdapter(things);
    }

    @OnClick(R.id.addBtn)
    public void addThing() {
        String thingValue = thingsField.getText().toString();
        if (!thingValue.isEmpty()) {
            ThingModel thingModel = new ThingModel("Test", thingValue);
            EventBus.getDefault().post(thingModel);
        }
    }

    @OnClick(R.id.dropBtn)
    public void dropData() {
        EventBus.getDefault().post("DROP");
    }

}
