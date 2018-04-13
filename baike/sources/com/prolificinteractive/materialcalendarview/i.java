package com.prolificinteractive.materialcalendarview;

import android.view.View;
import android.view.View.OnClickListener;

class i implements OnClickListener {
    final /* synthetic */ MaterialCalendarView a;

    i(MaterialCalendarView materialCalendarView) {
        this.a = materialCalendarView;
    }

    public void onClick(View view) {
        if (view == this.a.l) {
            this.a.n.setCurrentItem(this.a.n.getCurrentItem() + 1, true);
        } else if (view == this.a.k) {
            this.a.n.setCurrentItem(this.a.n.getCurrentItem() - 1, true);
        } else if (view == this.a.g) {
            if (this.a.t != null) {
                this.a.t.onClick(this.a.g);
            }
        } else if (view == this.a.i) {
            if (this.a.u != null) {
                this.a.u.onClick(this.a.i);
            }
        } else if ((view == this.a.m || view == this.a.f) && this.a.v != null) {
            this.a.v.onClick(this.a.m);
        }
    }
}
