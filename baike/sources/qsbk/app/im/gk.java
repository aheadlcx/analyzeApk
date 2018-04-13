package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;

class gk implements OnClickListener {
    final /* synthetic */ IMChatingUrlContentDisplayActivity a;

    gk(IMChatingUrlContentDisplayActivity iMChatingUrlContentDisplayActivity) {
        this.a = iMChatingUrlContentDisplayActivity;
    }

    public void onClick(View view) {
        if (this.a.d.canGoForward()) {
            this.a.d.goForward();
        }
    }
}
