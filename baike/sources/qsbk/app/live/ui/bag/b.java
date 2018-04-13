package qsbk.app.live.ui.bag;

import android.view.View;
import android.view.View.OnClickListener;

class b implements OnClickListener {
    final /* synthetic */ BagAdapter a;

    b(BagAdapter bagAdapter) {
        this.a = bagAdapter;
    }

    public void onClick(View view) {
        if (this.a.c != null) {
            this.a.c.onClick(view);
        }
    }
}
