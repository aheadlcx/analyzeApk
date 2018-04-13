package qsbk.app.live.widget;

import android.animation.ObjectAnimator;
import android.view.View;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.model.LiveEnterMessage;

class ix implements Runnable {
    final /* synthetic */ LiveEnterMessage a;
    final /* synthetic */ SuperUserEnterAnimLayout b;

    ix(SuperUserEnterAnimLayout superUserEnterAnimLayout, LiveEnterMessage liveEnterMessage) {
        this.b = superUserEnterAnimLayout;
        this.a = liveEnterMessage;
    }

    public void run() {
        this.b.m.setVisibility(0);
        this.b.j.setVisibility(0);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.b.m, View.ALPHA, new float[]{0.0f, 1.0f});
        ofFloat.setDuration(500);
        ofFloat.start();
        AppUtils.getInstance().getImageProvider().loadAvatar(this.b.n, this.a.getUserAvatar(), true);
        CharSequence userName = this.a.getUserName();
        if (userName.length() > 4) {
            userName = userName.substring(0, 4) + "...";
        }
        this.b.o.setText(userName);
    }
}
