package nl.fressh.nederlandviertfeest.adapter;

/**
 * Created by Ruben on 16-11-16.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import nl.fressh.nederlandviertfeest.model.EventsInformation;
import nl.fressh.nederlandviertfeest.AppController;
import nl.fressh.nederlandviertfeest.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CustomListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<EventsInformation> eventsItems;
    private ImageLoader imageLoader = null; //AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<EventsInformation> eventsItems) {
        this.activity = activity;
        this.eventsItems = eventsItems;
    }

    @Override
    public int getCount() {
        return eventsItems.size();
    }

    @Override
    public Object getItem(int location) {
        return eventsItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView location = (TextView) convertView.findViewById(R.id.location);
        TextView timeStamp = (TextView) convertView.findViewById(R.id.timeStamp);

        // getting events data for the row
        EventsInformation m = eventsItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        // name
        name.setText(m.getName());

        // Location name
        location.setText(String.valueOf(m.getLocationName()));

        // Event date
        long timeStampB = Long.parseLong(m.getTimeStampB()) * 1000;
        timeStamp.setText(getDate(timeStampB));

        return convertView;
    }

    public String getDate(long timeStamp) {

        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
            Date netDate = (new Date(timeStamp));
            return dateFormat.format(netDate);
        } catch (Exception ex) {
            return "xx";
        }
    }

}