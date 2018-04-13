package qsbk.app.nearby.ui;

import android.support.v4.app.FragmentActivity;
import qsbk.app.nearby.ui.Shake2FanSomeone.FanModel;
import qsbk.app.nearby.ui.Shake2FanSomeone.Shake2FanSomeoneListener;

final class ao implements Shake2FanSomeoneListener {
    final /* synthetic */ Shake2FanSomeoneListener a;
    final /* synthetic */ ShakeDialogFragment b;
    final /* synthetic */ FragmentActivity c;
    final /* synthetic */ int d;

    ao(Shake2FanSomeoneListener shake2FanSomeoneListener, ShakeDialogFragment shakeDialogFragment, FragmentActivity fragmentActivity, int i) {
        this.a = shake2FanSomeoneListener;
        this.b = shakeDialogFragment;
        this.c = fragmentActivity;
        this.d = i;
    }

    public void onServerSuccess(FanModel fanModel) {
        if (this.a != null) {
            this.a.onServerSuccess(fanModel);
        }
    }

    public void onServerFailed(FanModel fanModel, int i, String str) {
        if (this.a != null) {
            this.a.onServerFailed(fanModel, i, str);
        }
        this.b.dismissAllowingStateLoss();
        if (i != -110) {
            return;
        }
        if (this.c != null) {
            Shake2FanSomeone.openInfoCompletedActivity(this.c, this.d);
        } else {
            Shake2FanSomeone.openInfoCompletedActivity(this.b, this.d);
        }
    }

    public void onLocalSuccess(FanModel fanModel) {
        if (this.a != null) {
            this.a.onLocalSuccess(fanModel);
        }
    }
}
