package qsbk.app.im;

import qsbk.app.nearby.ui.ShakeDialogFragment.OnShakedListener;

class bq implements OnShakedListener {
    final /* synthetic */ bp a;

    bq(bp bpVar) {
        this.a = bpVar;
    }

    public void onSuccess(int i, int i2) {
        this.a.a.a(i2, i);
    }
}
