package nl.fressh.nederlandviertfeest;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ListDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_detail);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        String dummyText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
        "Ut volutpat interdum interdum. Nulla laoreet lacus diam, vitae " +
                "sodales sapien commodo faucibus. Vestibulum et feugiat enim. Donec " +
                "semper mi et euismod tempor. Sed sodales eleifend mi id varius. Nam " +
                "et ornare enim, sit amet gravida sapien. Quisque gravida et enim vel " +
                "volutpat. Vivamus egestas ut felis a blandit. Vivamus fringilla " +
                "dignissim mollis. Maecenas imperdiet interdum hendrerit. Aliquam" +
                " dictum hendrerit ultrices. Ut vitae vestibulum dolor. Donec auctor ante" +
                " eget libero molestie porta. Nam tempor fringilla ultricies. Nam sem " +
                "lectus, feugiat eget ullamcorper vitae, ornare et sem. Fusce dapibus ipsum" +
                " sed laoreet suscipit.";

        TextView info = (TextView)findViewById(R.id.textView2);
        info.setText(dummyText);

        info.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
            }

        });

    }



}
