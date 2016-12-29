package nl.fressh.nederlandviertfeest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import nl.fressh.nederlandviertfeest.adapter.CustomListAdapter;
import nl.fressh.nederlandviertfeest.model.EventsInformation;
import nl.fressh.nederlandviertfeest.model.RoundedNetworkImageView;

import static nl.fressh.nederlandviertfeest.R.string.loading_information;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // Event json url
    private static final String url = "http://app.veldhovenviertfeest.nl/json.php?key=lkj23oSDFLKijf9SD823oijslkhv89238WDFK23923";
    private ProgressDialog pDialog;
    private List<EventsInformation> eventsInformationList = new ArrayList<>();
    private CustomListAdapter adapter;

    private String maxPeriod;
//    BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // MARK - set custom actionBar
        showActionBar();

        ListView listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, eventsInformationList);
        assert listView != null;
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage(getString(loading_information));
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
                                try {
//                                    long timestampB = Long.parseLong(event.getString("timestamp_b")) * 1000;
                                    eventsInformation.setTimeStampB(event.getString("timestamp_b"));
                                } catch (Exception ex) {
                                    eventsInformation.setTimeStampB("");
                                }
                                try {
//                                    long timestampE = Long.parseLong(event.getString("timestamp_e")) * 1000;
                                    eventsInformation.setTimeStampE(event.getString("timestamp_e"));
                                } catch (Exception ex) {
                                    eventsInformation.setTimeStampE("");
                                }
                                eventsInformation.setDescription(event.getString("description"));
                                eventsInformation.setLocationName(event.getJSONObject("location_details").getString("name"));
                                eventsInformation.setAddress(event.getJSONObject("location_details").getString("address"));
                                eventsInformation.setPlace(event.getJSONObject("location_details").getString("place"));
                                try {
                                    eventsInformation.setWebsite(event.getJSONObject("location_details").getString("website"));
                                } catch (Exception ex) {
                                    eventsInformation.setWebsite("");
                                }

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

//        ImageButton addFav = (ImageButton) view.findViewById(R.id.favorite);
//        System.out.println(addFav.getScaleType());
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

    private void showActionBar() {
        LayoutInflater inflator = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_actionbar_layout, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled (false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        ImageButton imageButton = (ImageButton) v.findViewById(R.id.settings);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("settings cliced");
            }
        });

        TextView setCity = (TextView) v.findViewById(R.id.setCity);
        setCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("set city clicked");
            }
        });

        actionBar.setCustomView(v);
    }


//
//    bottomNavigationView.setOnNavigationItemSelectedListener(
//            new BottomNavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.action_favorites:
//
//                    break;
//                case R.id.action_schedules:
//
//                    break;
//                case R.id.action_music:
//
//                    break;
//            }
//            return false;
//        }
//    });

}
