package com.budejie.www.activity.label.view;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayout.OnOffsetChangedListener;

public abstract class AppBarStateChangeListener implements OnOffsetChangedListener {
    private AppBarStateChangeListener$State a = AppBarStateChangeListener$State.IDLE;

    public abstract void a(AppBarLayout appBarLayout, AppBarStateChangeListener$State appBarStateChangeListener$State);

    public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i == 0) {
            if (this.a != AppBarStateChangeListener$State.EXPANDED) {
                a(appBarLayout, AppBarStateChangeListener$State.EXPANDED);
            }
            this.a = AppBarStateChangeListener$State.EXPANDED;
        } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
            if (this.a != AppBarStateChangeListener$State.COLLAPSED) {
                a(appBarLayout, AppBarStateChangeListener$State.COLLAPSED);
            }
            this.a = AppBarStateChangeListener$State.COLLAPSED;
        } else {
            if (this.a != AppBarStateChangeListener$State.IDLE) {
                a(appBarLayout, AppBarStateChangeListener$State.IDLE);
            }
            this.a = AppBarStateChangeListener$State.IDLE;
        }
    }
}
