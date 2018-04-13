package qsbk.app.utils;

import android.view.View;
import android.view.View.OnClickListener;

class u implements OnClickListener {
    final /* synthetic */ s a;

    u(s sVar) {
        this.a = sVar;
    }

    public void onClick(View view) {
        this.a.cancel();
    }
}
