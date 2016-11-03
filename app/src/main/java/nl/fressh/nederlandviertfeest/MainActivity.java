package nl.fressh.nederlandviertfeest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    // Array of options ---> array adapter ---> ListView

    // List view: {views: da_items.xml}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateListView();

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
