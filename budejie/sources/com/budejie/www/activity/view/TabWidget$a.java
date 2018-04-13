package com.budejie.www.activity.view;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;

class TabWidget$a extends PagerAdapter {
    final /* synthetic */ TabWidget a;

    TabWidget$a(TabWidget tabWidget) {
        this.a = tabWidget;
    }

    public int getCount() {
        return TabWidget.f(this.a).size() > 1 ? Integer.MAX_VALUE : TabWidget.f(this.a).size();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (TabWidget.a(this.a) == 0) {
            return null;
        }
        RelativeLayout relativeLayout = (RelativeLayout) TabWidget.f(this.a).get(i % TabWidget.a(this.a));
        ViewParent parent = relativeLayout.getParent();
        if (parent != null) {
            ((ViewGroup) parent).removeView(relativeLayout);
        }
        viewGroup.addView(relativeLayout);
        return relativeLayout;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (TabWidget.a(this.a) != 0) {
            viewGroup.removeView((View) TabWidget.f(this.a).get(i % TabWidget.a(this.a)));
        }
    }

    public int getItemPosition(Object obj) {
        return -2;
    }
}
