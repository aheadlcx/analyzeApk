package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;

class er implements OnClickListener {
    final /* synthetic */ ShowcaseDialog a;

    er(ShowcaseDialog showcaseDialog) {
        this.a = showcaseDialog;
    }

    public void onClick(View view) {
        this.a.cancel();
    }
}
