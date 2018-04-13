package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class dv implements OnClickListener {
    final /* synthetic */ CheckInActivity a;

    dv(CheckInActivity checkInActivity) {
        this.a = checkInActivity;
    }

    public void onClick(View view) {
        this.a.f();
    }
}
