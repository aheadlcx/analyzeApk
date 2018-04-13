package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;

class bk implements OnClickListener {
    final /* synthetic */ CircleTopicsFragment a;

    bk(CircleTopicsFragment circleTopicsFragment) {
        this.a = circleTopicsFragment;
    }

    public void onClick(View view) {
        CircleTopicsFragment.a(this.a, "");
        CircleTopicsFragment.e(this.a).setText(CircleTopicsFragment.m(this.a));
    }
}
