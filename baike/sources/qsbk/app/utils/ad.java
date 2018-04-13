package qsbk.app.utils;

import android.view.View;
import android.view.View.OnClickListener;

class ad implements OnClickListener {
    final /* synthetic */ ac a;

    ad(ac acVar) {
        this.a = acVar;
    }

    public void onClick(View view) {
        this.a.cancel();
    }
}
