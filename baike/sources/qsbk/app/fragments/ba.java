package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnFocusChangeListener;

class ba implements OnFocusChangeListener {
    final /* synthetic */ CircleTopicsFragment a;

    ba(CircleTopicsFragment circleTopicsFragment) {
        this.a = circleTopicsFragment;
    }

    public void onFocusChange(View view, boolean z) {
        CircleTopicsFragment.a(this.a).setGravity(z ? 19 : 17);
    }
}
