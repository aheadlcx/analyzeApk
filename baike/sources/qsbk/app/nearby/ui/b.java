package qsbk.app.nearby.ui;

import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.OnWheelChangedListener;

class b implements OnWheelChangedListener {
    final /* synthetic */ HometownDialogFragment a;

    b(HometownDialogFragment hometownDialogFragment) {
        this.a = hometownDialogFragment;
    }

    public void onChanged(AbstractWheel abstractWheel, int i, int i2) {
        if (!this.a.r) {
            this.a.p = i2;
        }
    }
}
