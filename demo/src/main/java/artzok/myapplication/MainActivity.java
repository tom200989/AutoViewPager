package artzok.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.art.zok.autoview.AutoViewPager;

public class MainActivity extends AppCompatActivity {

    private int mCount = 10;
    private PagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoViewPager autoViewPager = (AutoViewPager) findViewById(R.id.auto_view_pager);
        autoViewPager.start();

        mAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return mCount;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                TextView view = (TextView) getLayoutInflater().inflate(R.layout.pager_item, container, false);
                view.setText("Pager " + position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        };

        autoViewPager.setPagerAdapter(mAdapter);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mCount= 20;
                mAdapter.notifyDataSetChanged();
            }
        }, 3000);
    }
}
