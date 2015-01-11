package com.sccommute.commute;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * Created by simba on 1/11/15.
 */
public class SwitchLinearLayout extends LinearLayout{
    private boolean bSwitch = false;

    public SwitchLinearLayout(Context context) {
        super(context);
    }

    public SwitchLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void switchVal() {
        Log.e("HERE", "HEE");
        bSwitch = !bSwitch;
    }

    public boolean getSwitch() {
        return bSwitch;
    }
}
