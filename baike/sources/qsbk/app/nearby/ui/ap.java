package qsbk.app.nearby.ui;

import qsbk.app.nearby.ui.Shake2FanSomeone.FanAsynTask;
import qsbk.app.nearby.ui.Shake2FanSomeone.FanModel;
import qsbk.app.nearby.ui.Shake2FanSomeone.Shake2FanSomeoneListener;
import qsbk.app.nearby.ui.ShakeDialogFragment.OnShakedListener;

final class ap implements OnShakedListener {
    final /* synthetic */ FanModel a;
    final /* synthetic */ OnShakedListener b;
    final /* synthetic */ Shake2FanSomeoneListener c;

    ap(FanModel fanModel, OnShakedListener onShakedListener, Shake2FanSomeoneListener shake2FanSomeoneListener) {
        this.a = fanModel;
        this.b = onShakedListener;
        this.c = shake2FanSomeoneListener;
    }

    public void onSuccess(int i, int i2) {
        this.a.c = i;
        this.a.d = i2;
        if (this.b != null) {
            this.b.onSuccess(i, i2);
        }
        this.c.onLocalSuccess(this.a);
        new FanAsynTask(this.a, this.c).executeOnExecutor(FanAsynTask.SERIAL_EXECUTOR, new Void[0]);
    }
}
