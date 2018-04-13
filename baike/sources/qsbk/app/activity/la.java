package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class la implements OnClickListener {
    final /* synthetic */ FillUserDataActivity a;

    la(FillUserDataActivity fillUserDataActivity) {
        this.a = fillUserDataActivity;
    }

    public void onClick(View view) {
        this.a.c.setCursorVisible(true);
        this.a.f();
    }
}
