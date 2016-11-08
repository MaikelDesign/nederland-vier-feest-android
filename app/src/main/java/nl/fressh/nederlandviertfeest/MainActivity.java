package nl.fressh.nederlandviertfeest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nl.fressh.nederlandviertfeest.model.EventDates;

public class MainActivity extends AppCompatActivity implements LoadJson.Listener, AdapterView.OnItemClickListener {

    private ListView mListView;

    public static final String URL = "http://app.veldhovenviertfeest.nl/json.php?key=lkj23oSDFLKijf9SD823oijslkhv89238WDFK23923";

    private List<HashMap<String, String>> mAndroidMapList = new ArrayList<>();

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCR = "description";
    private static final String KEY_LOCATION = "location_details.name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);
        new LoadJson(this).execute(URL);
    }

    @Override
    public void onLoaded(List<EventDates> androidList) {

        for (EventDates android : androidList) {

            HashMap<String, String> map = new HashMap<>();

            map.put(KEY_ID, android.getId());
            map.put(KEY_NAME, android.getName());
            map.put(KEY_DESCR, android.getDescription());
            map.put(KEY_LOCATION, android.getLocation());

            mAndroidMapList.add(map);
        }

        loadListView();
    }

    @Override
    public void onError() {

        Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(this, mAndroidMapList.get(i).get(KEY_NAME),Toast.LENGTH_LONG).show();
    }

    private void loadListView() {

        ListAdapter adapter = new SimpleAdapter(MainActivity.this, mAndroidMapList, R.layout.list_item,
                new String[] { KEY_NAME, KEY_DESCR },
                new int[] { R.id.textTitle,R.id.detailList });

        mListView.setAdapter(adapter);

    }
}
