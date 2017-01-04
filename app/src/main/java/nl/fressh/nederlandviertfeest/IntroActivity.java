package nl.fressh.nederlandviertfeest;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import nl.fressh.nederlandviertfeest.managers.PrefManager;
import nl.fressh.nederlandviertfeest.adapter.CustomAdapterIntro;

/**
 * Created by maikel on 26-12-16.
 */

public class IntroActivity extends AppCompatActivity {

    CustomAdapterIntro adapter;
    ViewPager viewPager;
    Button next;
    private PrefManager prefManager;
    private int dotsCount;
    private LinearLayout pager_indicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // Checking for first time launch - before calling setContentView()
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        setContentView(R.layout.activity_intro);

        // MARK - next button
        next = (Button)findViewById(R.id.button);
        nextBtn(next);

        // MARK - add viewpager
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        adapter = new CustomAdapterIntro(this);
        pager_indicator = (LinearLayout) viewPager.findViewById(R.id.viewPagerCountDots);
        viewPager.setAdapter(adapter);
//        setPaginationController();

        viewPager.setOnPageChangeListener(myOnPageChangeListener);

        getSupportActionBar().hide();

        setupPagination();

    }


    public void nextBtn(final Button btn) {

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                int pos = viewPager.getCurrentItem();
                viewPager.setCurrentItem(getItem(+1), true);

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

            // MARK - activate right dot (pagination)
            LinearLayout ll = (LinearLayout) findViewById(R.id.viewPagerCountDots);
            int childCount = ll.getChildCount();
            for (int i = 0; i < childCount; i++) {

                // set every child to nonselected item dot
                View a = ll.getChildAt(i);
                a.setBackground(getResources().getDrawable(R.drawable.nonselecteditem_dot));

                // if child number equals current position > switch backgrounds to selected dot
                if (i == position) {
                    View b = ll.getChildAt(i);
                    b.setBackground(getResources().getDrawable(R.drawable.selecteditem_dot));
                }
            }

            // MARK - change button value on pager
            switch (position) {
                case 0:
                    break;
                case 1:
                    break;
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

    private void setupPagination() {

        dotsCount = adapter.getCount();
        LinearLayout ll = (LinearLayout) findViewById(R.id.viewPagerCountDots);

        for (int i = 0; i < dotsCount; i++) {
            Button dot = new Button(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(50,50);
            params.setMarginEnd(30);
            dot.setLayoutParams(params);
            dot.setId(i);
            System.out.println("dotId: " + dot.getId());
            if (i == 0) {
                dot.setBackground(getResources().getDrawable(R.drawable.selecteditem_dot));
            } else {
                dot.setBackground(getResources().getDrawable(R.drawable.nonselecteditem_dot));
            }
            ll.addView(dot);
        }

    }


}
