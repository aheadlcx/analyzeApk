package qsbk.app.nearby.ui;

import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.OnWheelScrollListener;

class d implements OnWheelScrollListener {
    final /* synthetic */ HometownDialogFragment a;

    d(HometownDialogFragment hometownDialogFragment) {
        this.a = hometownDialogFragment;
    }

    public void onScrollingStarted(AbstractWheel abstractWheel) {
        this.a.p = 0;
        this.a.r = true;
    }

    public void onScrollingFinished(AbstractWheel abstractWheel) {
        this.a.r = false;
        this.a.a(this.a.l, abstractWheel.getCurrentItem());
    }
}
