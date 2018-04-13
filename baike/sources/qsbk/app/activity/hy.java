package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class hy implements OnClickListener {
    final /* synthetic */ hw a;

    hy(hw hwVar) {
        this.a = hwVar;
    }

    public void onClick(View view) {
        this.a.cancel();
    }
}
