package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;

class bf implements OnClickListener {
    final /* synthetic */ CircleTopicsFragment a;

    bf(CircleTopicsFragment circleTopicsFragment) {
        this.a = circleTopicsFragment;
    }

    public void onClick(View view) {
        CircleTopicsFragment.b(this.a).hide();
        CircleTopicsFragment.c(this.a).setSelection(0);
        CircleTopicsFragment.c(this.a).post(new bg(this));
    }
}
