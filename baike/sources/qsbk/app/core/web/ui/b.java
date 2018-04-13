package qsbk.app.core.web.ui;

import android.view.View;
import android.view.View.OnClickListener;

class b implements OnClickListener {
    final /* synthetic */ FullScreenWebActivity a;

    b(FullScreenWebActivity fullScreenWebActivity) {
        this.a = fullScreenWebActivity;
    }

    public void onClick(View view) {
        this.a.onBackPressed();
    }
}
