package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class abb implements OnClickListener {
    final /* synthetic */ ResetPwdActivity a;

    abb(ResetPwdActivity resetPwdActivity) {
        this.a = resetPwdActivity;
    }

    public void onClick(View view) {
        this.a.c.startCountDown(60000, 1000);
        this.a.c.setEnabled(false);
        this.a.k();
    }
}
