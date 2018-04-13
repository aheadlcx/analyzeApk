package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;

class gi implements OnClickListener {
    final /* synthetic */ IMChatingUrlContentDisplayActivity a;

    gi(IMChatingUrlContentDisplayActivity iMChatingUrlContentDisplayActivity) {
        this.a = iMChatingUrlContentDisplayActivity;
    }

    public void onClick(View view) {
        this.a.d.reload();
    }
}
