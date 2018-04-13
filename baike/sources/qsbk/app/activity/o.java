package qsbk.app.activity;

import android.view.View;
import android.view.View.OnFocusChangeListener;

class o implements OnFocusChangeListener {
    final /* synthetic */ ActionBarLoginActivity a;

    o(ActionBarLoginActivity actionBarLoginActivity) {
        this.a = actionBarLoginActivity;
    }

    public void onFocusChange(View view, boolean z) {
        if (z) {
            ActionBarLoginActivity.n(this.a);
        }
    }
}
