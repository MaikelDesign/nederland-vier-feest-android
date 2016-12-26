package nl.fressh.nederlandviertfeest;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import nl.fressh.nederlandviertfeest.Managers.PrefManager;
import nl.fressh.nederlandviertfeest.adapter.CustomAdapterIntro;

/**
 * Created by maikel on 26-12-16.
 */

public class IntroActivity extends AppCompatActivity {

    CustomAdapterIntro adapter;
    ViewPager viewPager;
    Button next;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Checking for first time launch - before calling setContentView()
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        setContentView(R.layout.activity_intro);

        next = (Button)findViewById(R.id.button);
        nextBtn(next);

        viewPager = (ViewPager)findViewById(R.id.view_pager);
        adapter = new CustomAdapterIntro(this);
        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(myOnPageChangeListener);

        getSupportActionBar().hide();

    }

    public void nextBtn(final Button btn) {

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                int pos = viewPager.getCurrentItem();
                viewPager.setCurrentItem(getItem(+1), true);

                // TODO:
                // if pos == 2
                // - set introDone to true
                // - go to app screen
                if (pos == 2) {
                    launchHomeScreen();
                }

            }
        });

    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    ViewPager.OnPageChangeListener myOnPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            System.out.println(position);

            int pos = position;
            switch (pos) {
                case 2:  next.setText(R.string.done);
                    break;
                default: next.setText(R.string.next);
                    break;

            }

        }

    };

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(IntroActivity.this, MainActivity.class));
        finish();
    }

}
