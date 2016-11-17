package nl.fressh.nederlandviertfeest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import nl.fressh.nederlandviertfeest.model.EventsInformation;

public class ListDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    ImageLoader imageLoader = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_detail);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        // get EventsInformation type
        EventsInformation eventsInformation = (EventsInformation) getIntent().getSerializableExtra("eventInformation");

        setTitle(eventsInformation.getName());

        // convert imageView to NetworkImageView to view images from the web
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) findViewById(R.id.imageView);

        // getting events data

        // thumbnail image
        thumbNail.setImageUrl(eventsInformation.getThumbnailUrl(), imageLoader);

        // description
        TextView info = (TextView)findViewById(R.id.info);
        info.setText(eventsInformation.getDescription());

        // google maps
        MapFragment map = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        map.getMapAsync(this);

        info.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
            }

        });

    }
    
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng position = new LatLng(51.429630, 5.404197);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(position)      // Sets the center of the map to Mountain View
                .zoom(17)                   // Sets the zoom
                .build();                   // Creates a CameraPosition from the builder
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        googleMap.addMarker(new MarkerOptions().position(position));

    }
}
