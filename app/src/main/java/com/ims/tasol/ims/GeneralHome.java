package com.ims.tasol.ims;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tasol on 24/2/17.
 */

public class GeneralHome extends Fragment {
    int[] tabicons={
            R.drawable.user,
//            R.drawable.history,
            R.drawable.stock
    };
    int[] tabiconsSelected={
            R.drawable.user,
//            R.drawable.history,
            R.drawable.stock
    };
    TabLayout tabLayout;
    ViewPager viewpager;
    Toolbar toolbar;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.general_home_layout,container,false);

        toolbar=(Toolbar)view.findViewById(R.id.toolbar);
        tabLayout = (TabLayout)view.findViewById(R.id.tabs);

        viewpager=(ViewPager)view.findViewById(R.id.viewpager);
        setupViewPager(viewpager);
        tabLayout.setupWithViewPager(viewpager);
        setUpTabIcons();
        return view;

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new UserTab(), "First");
        adapter.addFragment(new StocksTab(), "Second");
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

    private void setUpTabIcons(){
        LayoutInflater inflater =LayoutInflater.from(getActivity());

        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tab_text_color));

        View user = inflater.inflate(R.layout.home_tab_item, null);
        ImageView imguser = (ImageView) user.findViewById(R.id.imgTab);
        imguser.setBackgroundResource(R.drawable.user_tab_selector);
        tabLayout.getTabAt(0).setCustomView(user);

        View stocks = inflater.inflate(R.layout.home_tab_item, null);
        ImageView imgstocks = (ImageView) stocks.findViewById(R.id.imgTab);
        imgstocks.setBackgroundResource(R.drawable.stock_tab_selector);
        tabLayout.getTabAt(1).setCustomView(stocks);

//        View history = inflater.inflate(R.layout.home_tab_item, null);
//        ImageView imghistory = (ImageView) history.findViewById(R.id.imgTab);
//        imghistory.setBackgroundResource(R.drawable.history_tab_selector);
//        tabLayout.getTabAt(2).setCustomView(history);

    }


}
