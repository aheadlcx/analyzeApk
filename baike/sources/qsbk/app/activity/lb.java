package qsbk.app.activity;

import android.view.View;
import android.view.View.OnFocusChangeListener;

class lb implements OnFocusChangeListener {
    final /* synthetic */ FillUserDataActivity a;

    lb(FillUserDataActivity fillUserDataActivity) {
        this.a = fillUserDataActivity;
    }

    public void onFocusChange(View view, boolean z) {
        this.a.f();
    }
}
