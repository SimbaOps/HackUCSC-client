package com.sccommute.commute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;


public class Schedule extends ActionBarActivity implements ActionBar.TabListener {

    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("HEHE", "BUGq");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        String[] busNames = new String[]{
                "10", "12", "15", "16", "19", "20"
        };

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return new BusSchedViewer.BusScheduleFrag();
        }

        @Override
        public int getCount() {
            return busNames.length;
        }

        @Override
        public String getPageTitle(int pos) {
            return busNames[pos];
        }
    }

    public static class BusScheduleFrag extends ListFragment {
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            ArrayList<BusSched> list = new ArrayList<BusSched>();
            list.add(new BusSched("6:50am", "7:08am", "7:30am"));
            list.add(new BusSched("7:50am", "8:08am", "8:30am"));
            list.add(new BusSched("8:50am", "9:08am", "9:30am"));
            list.add(new BusSched("9:50am", "10:08am", "10:35am"));
            list.add(new BusSched("10:50am", "11:08am", "11:35am"));
            list.add(new BusSched("11:50am", "12:08pm", "12:35pm"));
            list.add(new BusSched("12:20pm", "12:38pm", "1:05pm"));
            list.add(new BusSched("12:50pm", "1:08pm", "1:30pm"));
            list.add(new BusSched("1:20pm", "1:38pm", "2:05pm"));
            list.add(new BusSched("1:50pm", "2:08pm", "2:30pm"));
            list.add(new BusSched("2:20pm", "2:38pm", "3:05pm"));
            list.add(new BusSched("2:50pm", "3:08pm", "3:30pm"));
            list.add(new BusSched("3:20pm", "3:38pm", "4:05pm"));
            list.add(new BusSched("3:50pm", "4:08pm", "4:30pm"));
            list.add(new BusSched("4:20pm", "4:38pm", "5:05pm"));
            setListAdapter(new ScheduleAdapter(getActivity(), list));
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            super.onListItemClick(l, v, position, id);
            Log.e("HEHE", "BUG");
            LinearLayout bg = (LinearLayout) v.findViewById(R.id.list_item_bg);
            bg.setBackgroundColor(getResources().getColor(R.color.greenwhite));
        }
    }

}
