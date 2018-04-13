package qsbk.app.activity;

import qsbk.app.nearby.ui.ShakeDialogFragment.OnShakedListener;

class yv implements OnShakedListener {
    final /* synthetic */ yo a;

    yv(yo yoVar) {
        this.a = yoVar;
    }

    public void onSuccess(int i, int i2) {
        OtherInfoActivity.a(this.a.a, i2, i);
    }
}
