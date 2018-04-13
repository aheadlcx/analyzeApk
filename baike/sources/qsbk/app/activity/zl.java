package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class zl implements OnClickListener {
    final /* synthetic */ PayPasswordSetActivity a;

    zl(PayPasswordSetActivity payPasswordSetActivity) {
        this.a = payPasswordSetActivity;
    }

    public void onClick(View view) {
        this.a.a.startCountDown(60000, 1000);
        this.a.a.setEnabled(false);
        this.a.g();
    }
}
