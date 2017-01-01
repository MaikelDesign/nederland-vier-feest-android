package nl.fressh.nederlandviertfeest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.transition.ChangeBounds;
import android.support.v7.app.AppCompatActivity;
import android.support.transition.Scene;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import nl.fressh.nederlandviertfeest.model.BlurBuilder;
import nl.fressh.nederlandviertfeest.model.EventsInformation;

public class ListDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    ImageLoader imageLoader = null;
    LatLng position;
    boolean isInInitialScene = true;
    final int transistionDurationInMs = 800;
    ViewGroup msceneRoot;
    Map<String, Transition> mTransitionByName;
    EventsInformation eventsInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_detail);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        // get EventsInformation type
        eventsInformation = (EventsInformation) getIntent().getSerializableExtra("eventInformation");

        setTitle(eventsInformation.getName());

        // convert imageView to NetworkImageView to view images from the web
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) findViewById(R.id.imageView);

        // thumbnail image
        assert thumbNail != null;
        thumbNail.setImageUrl(eventsInformation.getThumbnailUrl(), imageLoader);



        // description
        TextView info = (TextView) findViewById(R.id.info);
        assert info != null;
//        info.setText(eventsInformation.getDescription());

        // get LatLong from address
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        String myLocation = eventsInformation.getAddress() + " " + eventsInformation.getPlace();
        List<android.location.Address> addresses = null;
        try {
            addresses = geocoder.getFromLocationName(myLocation, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert addresses != null;
        android.location.Address address = addresses.get(0);
        double latitude = address.getLatitude();
        double longitude = address.getLongitude();

        position = new LatLng(latitude, longitude);

        // google maps
//        MapFragment map = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
//        map.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        IconGenerator mapsMarker = new IconGenerator(this);
        mapsMarker.setStyle(IconGenerator.STYLE_BLUE);
        Bitmap mapsMarkerBitmap = mapsMarker.makeIcon(eventsInformation.getLocationName());

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(position)      // Sets the center of the map to Mountain View
                .zoom(15)              // Sets the zoom
                .build();              // Creates a CameraPosition from the builder
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        googleMap.addMarker(new MarkerOptions()
                .position(position)
                .icon(BitmapDescriptorFactory.fromBitmap(mapsMarkerBitmap)));
    }

}
