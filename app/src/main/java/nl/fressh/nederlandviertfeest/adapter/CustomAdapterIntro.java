package nl.fressh.nederlandviertfeest.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import nl.fressh.nederlandviertfeest.R;

public class CustomAdapterIntro extends PagerAdapter {


    private int[] imgs = {R.drawable.pic1_web, R.drawable.pic2_web, R.drawable.pic3_web};
    private String[] introTexts = {"Kies een gemeente", "Kies je favoriete kroeg", "Klaar!"};
    private String[] stepInfo = {
            "Zodra de app opgestart is kiest u linksboven uw woonplaats.",
            "Zodra u een overzicht van evenementen ziet kunt u door het sterretje aan te vinken het evenement bewaren.",
            "Super, nu is het tijd om feest te vieren. Veel plezier met de Viert Feest app!"
    };
    private LayoutInflater inflater; //pomper, verbindt stukken aan elkaar en in elkaar
    private Context ctx;

    public CustomAdapterIntro(Context ctx){
        this.ctx = ctx;
    }



    @Override
    public int getCount(){
        return imgs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object){
        return (view ==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position){
        inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.swipe, container, false);//pompt de swipe XML in v
        View v2 = inflater.inflate(R.layout.activity_intro, container, false);
        ImageView img = (ImageView)v.findViewById(R.id.imageView);
        TextView tv = (TextView)v.findViewById(R.id.textView);
        TextView si = (TextView)v.findViewById(R.id.stepInfo);
        Button btn = (Button)v2.findViewById(R.id.button);

        img.setImageResource(imgs[position]);
        tv.setText(introTexts[position]);
        si.setText(stepInfo[position]);

        btn.setText(R.string.done);

        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.invalidate();
    }



}
