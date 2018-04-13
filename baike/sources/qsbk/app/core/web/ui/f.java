package qsbk.app.core.web.ui;

import android.view.View;
import android.view.View.OnClickListener;

class f implements OnClickListener {
    final /* synthetic */ WebActivity a;

    f(WebActivity webActivity) {
        this.a = webActivity;
    }

    public void onClick(View view) {
        this.a.onBackPressed();
    }
}
