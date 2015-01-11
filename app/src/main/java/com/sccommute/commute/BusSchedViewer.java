package com.sccommute.commute;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class BusSchedViewer extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.swipe, container);
        ViewPager pager = (ViewPager) v.findViewById(R.id.pager);
        pager.setAdapter(new BusScheduleAdapter(getChildFragmentManager()));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return v;
    }

    private class BusScheduleAdapter extends FragmentStatePagerAdapter {

        BusScheduleAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new BusScheduleFrag();
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    public static class BusScheduleFrag extends ListFragment {
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            String[] values = new String[]{"Android", "iPhone", "WindowsMobile",
                    "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                    "Linux", "OS/2"};
            setListAdapter(new ScheduleAdapter(getActivity(), new ArrayList<String>(Arrays.asList(values))));
        }
    }
}