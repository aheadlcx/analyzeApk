package qsbk.app.activity;

import android.view.View;
import android.view.View.OnFocusChangeListener;

class n implements OnFocusChangeListener {
    final /* synthetic */ ActionBarLoginActivity a;

    n(ActionBarLoginActivity actionBarLoginActivity) {
        this.a = actionBarLoginActivity;
    }

    public void onFocusChange(View view, boolean z) {
        ActionBarLoginActivity.n(this.a);
    }
}
