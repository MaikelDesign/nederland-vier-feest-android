package nl.fressh.nederlandviertfeest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

        Intent j = new Intent(MainActivity.this, ListDetailActivity.class);
        startActivity(j);
    }

    private void loadListView() {

        ListAdapter adapter = new SimpleAdapter(MainActivity.this, mAndroidMapList, R.layout.list_item,
                new String[] { KEY_NAME, KEY_DESCR },
                new int[] { R.id.textTitle,R.id.detailList });

        mListView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
//            return true;
            Intent about = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(about);

        } else if(id == R.id.action_opdracht1){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
