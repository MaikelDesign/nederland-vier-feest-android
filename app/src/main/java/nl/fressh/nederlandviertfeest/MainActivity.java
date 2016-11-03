package nl.fressh.nederlandviertfeest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Array of options ---> array adapter ---> ListView

    // List view: {views: da_items.xml}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getJson();

        populateListView();
    }

    public void getJson() {
        String baseURL = "http://app.veldhovenviertfeest.nl/json.php?key=lkj23oSDFLKijf9SD823oijslkhv89238WDFK23923";
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(MainActivity.this,response.substring(0,500),Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"That didn't work!",Toast.LENGTH_LONG).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void populateListView(){

        // create list items
        String[] myItems = {"one", "two", "three", "four", "five"};
//        ImageView image;

        // build adapter
        // new arrayAdapter<String>(context for the activity, layout to use, list of elements)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.da_item, R.id.detaillist, myItems);

        // configure the list view
        ListView List = (ListView) findViewById(R.id.listViewMain); // R stands for resources
        List.setAdapter(adapter);

    }

}
