package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class ht implements OnClickListener {
    final /* synthetic */ hs a;

    ht(hs hsVar) {
        this.a = hsVar;
    }

    public void onClick(View view) {
        this.a.cancel();
    }
}
