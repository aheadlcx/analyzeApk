package com.budejie.www.util;

import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;

public class au {
    private static Toast a;

    public static void a(int i) {
        a(BudejieApplication.g.getResources().getString(i));
    }

    public static void a(String str) {
        a(str, -1, 0).show();
    }

    public static Toast a(String str, int i, int i2) {
        if (a == null) {
            a = new Toast(BudejieApplication.g);
        }
        a.setDuration(i2);
        a.setGravity(17, 0, 0);
        RelativeLayout relativeLayout = (RelativeLayout) ((LayoutInflater) BudejieApplication.g.getSystemService("layout_inflater")).inflate(R.layout.toast, null);
        TextView textView = (TextView) relativeLayout.findViewById(R.id.toast);
        textView.setText(str);
        textView.setTextColor(i);
        a.setView(relativeLayout);
        return a;
    }
}
