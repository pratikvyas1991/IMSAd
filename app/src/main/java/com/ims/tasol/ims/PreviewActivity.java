package com.ims.tasol.ims;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tasol on 24/2/17.
 */

public class PreviewActivity extends AppCompatActivity {
    Toolbar toolPreview;
    ViewPager viewPagerPreview;
    TabLayout tabLayoutPreview;
    LinearLayout parentLayout;
    Button btnPreview;
    TextView tvDate,tvTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_activity);
        toolPreview = (Toolbar) findViewById(R.id.toolPreview);
        toolPreview.setTitle("Preview");
        setSupportActionBar(toolPreview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolPreview.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tabLayoutPreview=(TabLayout)findViewById(R.id.tabLayoutPreview);
        viewPagerPreview=(ViewPager)findViewById(R.id.viewPagerPreview);

        setupViewPager(viewPagerPreview);
        tabLayoutPreview.setupWithViewPager(viewPagerPreview);
        setUpTabIcons();
    }

    private void setUpTabIcons(){
        LayoutInflater inflater =LayoutInflater.from(PreviewActivity.this);

        tabLayoutPreview.setSelectedTabIndicatorColor(getResources().getColor(R.color.tab_text_color));

        View veergati = inflater.inflate(R.layout.veergati_tab_item, null);
        tabLayoutPreview.getTabAt(0).setCustomView(veergati);

        View gauravT = inflater.inflate(R.layout.gaurav_trading_tab_item, null);
        tabLayoutPreview.getTabAt(1).setCustomView(gauravT);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new VeergatiPreview(), "First");
        adapter.addFragment(new GauravTradingPreview()
                , "Second");
//        adapter.addFragment(new HistoryTab(), "Third");
        viewPager.setAdapter(adapter);
    }
    public class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList= new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentTitleList.size();
        }
        public void addFragment(Fragment fragment, String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public void foo(){
    }
}
