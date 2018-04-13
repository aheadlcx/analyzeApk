package com.budejie.www.activity.video;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;

public class g extends FragmentPagerAdapter {
    ArrayList<Fragment> a;

    public g(FragmentManager fragmentManager, ArrayList<Fragment> arrayList) {
        super(fragmentManager);
        this.a = arrayList;
    }

    public int getCount() {
        return this.a.size();
    }

    public Fragment getItem(int i) {
        return (Fragment) this.a.get(i);
    }
}
