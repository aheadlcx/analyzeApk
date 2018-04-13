package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;

class dm implements OnClickListener {
    final /* synthetic */ LaiseeNormalGetFragment a;

    dm(LaiseeNormalGetFragment laiseeNormalGetFragment) {
        this.a = laiseeNormalGetFragment;
    }

    public void onClick(View view) {
        this.a.b();
        this.a.a.setEnabled(false);
        this.a.d();
    }
}
