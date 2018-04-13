package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;

class ga implements OnClickListener {
    final /* synthetic */ IMChatingUrlContentDisplayActivity a;

    ga(IMChatingUrlContentDisplayActivity iMChatingUrlContentDisplayActivity) {
        this.a = iMChatingUrlContentDisplayActivity;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
