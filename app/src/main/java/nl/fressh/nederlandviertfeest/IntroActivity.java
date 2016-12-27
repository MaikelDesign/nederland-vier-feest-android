package nl.fressh.nederlandviertfeest;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    private ImageView[] dots;


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
            System.out.println(position);

            // MARK - activate right dot
//            for (int i = 0; i < dotsCount; i++) {
//                dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
//            }
//            dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));

            // MARK - change button value on pager
            switch (position) {
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

    private void setPaginationController() {
        dotsCount = adapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            //pager_indicator.addView(dots[i], params);
        }

//        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

}
