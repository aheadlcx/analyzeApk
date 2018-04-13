package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class xz implements OnClickListener {
    final /* synthetic */ NewImageViewer a;

    xz(NewImageViewer newImageViewer) {
        this.a = newImageViewer;
    }

    public void onClick(View view) {
        this.a.closeAfterTransition();
    }
}
