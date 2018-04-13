package qsbk.app.fragments;

import android.widget.ImageView;
import android.widget.TextView;
import qsbk.app.widget.SizeNotifierRelativeLayout$SizeNotifierRelativeLayoutDelegate;

class bh implements SizeNotifierRelativeLayout$SizeNotifierRelativeLayoutDelegate {
    final /* synthetic */ CircleTopicsFragment a;

    bh(CircleTopicsFragment circleTopicsFragment) {
        this.a = circleTopicsFragment;
    }

    public void onSizeChanged(int i) {
        int i2;
        int i3 = 0;
        CircleTopicsFragment.a(this.a).setGravity(i > 0 ? 19 : 17);
        int length = CircleTopicsFragment.e(this.a).getText().length();
        TextView a = CircleTopicsFragment.a(this.a);
        if (length > 0) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        a.setVisibility(i2);
        ImageView f = CircleTopicsFragment.f(this.a);
        if (length <= 0) {
            i3 = 8;
        }
        f.setVisibility(i3);
        if (i == 0) {
            CircleTopicsFragment.e(this.a).clearFocus();
        }
    }
}
