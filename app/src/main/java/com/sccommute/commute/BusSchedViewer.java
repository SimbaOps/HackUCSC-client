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
    }
}