package nl.fressh.nederlandviertfeest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import nl.fressh.nederlandviertfeest.adapter.CustomListAdapter;
import nl.fressh.nederlandviertfeest.model.EventsInformation;

// TODO make LoadJson class: add LoadJson.Listener to implements
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<HashMap<String, String>> mAndroidMapList = new ArrayList<>();
    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // Event json url
    private static final String url = "http://app.veldhovenviertfeest.nl/json.php?key=lkj23oSDFLKijf9SD823oijslkhv89238WDFK23923";
    private ProgressDialog pDialog;
    private List<EventsInformation> eventsInformationList = new ArrayList<EventsInformation>();
    private ListView listView;
    private CustomListAdapter adapter;

    private static final String KEY_NAME = "name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, eventsInformationList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        // jsonObjReq
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {
                            JSONArray eventArray = response.getJSONArray("events");
                            for (int i = 0; i < eventArray.length(); i++) {
                                JSONObject event = eventArray.getJSONObject(i);
                                EventsInformation eventsInformation = new EventsInformation();

                                eventsInformation.setName(event.getString("name"));
                                eventsInformation.setThumbnailUrl(event.getJSONArray("images").getString(0));
                                long timestamp = Long.parseLong(event.getString("timestamp_b")) * 1000;
                                eventsInformation.setTimeStampB(getDate(timestamp));
                                eventsInformation.setDescription(event.getString("description"));
                                eventsInformation.setLocationName(event.getJSONObject("location_details").getString("name"));
                                eventsInformation.setAddress(event.getJSONObject("location_details").getString("address"));
                                eventsInformation.setPlace(event.getJSONObject("location_details").getString("place"));

                                // adding event details to eventsInformationList array
                                eventsInformationList.add(eventsInformation);
                                hidePDialog();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int postion, long l) {

        EventsInformation eventInformation = (EventsInformation) adapterView.getAdapter().getItem(postion);
        
        Intent intent = new Intent(MainActivity.this, ListDetailActivity.class);
        intent.putExtra("eventInformation", eventInformation);
        startActivity(intent);
    }


    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    public String getDate(long timeStamp) {

        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date netDate = (new Date(timeStamp));
            return dateFormat.format(netDate);
        } catch (Exception ex) {
            return "xx";
        }
    }
}
