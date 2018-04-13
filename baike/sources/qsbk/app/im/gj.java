package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;

class gj implements OnClickListener {
    final /* synthetic */ IMChatingUrlContentDisplayActivity a;

    gj(IMChatingUrlContentDisplayActivity iMChatingUrlContentDisplayActivity) {
        this.a = iMChatingUrlContentDisplayActivity;
    }

    public void onClick(View view) {
        if (this.a.d.canGoBack()) {
            this.a.d.goBack();
        }
    }
}
