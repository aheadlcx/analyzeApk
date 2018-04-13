package qsbk.app.nearby.ui;

import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.OnWheelChangedListener;

class c implements OnWheelChangedListener {
    final /* synthetic */ HometownDialogFragment a;

    c(HometownDialogFragment hometownDialogFragment) {
        this.a = hometownDialogFragment;
    }

    public void onChanged(AbstractWheel abstractWheel, int i, int i2) {
        if (!this.a.r) {
            this.a.a(this.a.l, i2);
        }
    }
}
