package qsbk.app.widget;

import android.view.View;
import android.view.View.OnClickListener;

class dh implements OnClickListener {
    final /* synthetic */ PtrLayout a;

    dh(PtrLayout ptrLayout) {
        this.a = ptrLayout;
    }

    public void onClick(View view) {
        this.a.loadMore();
    }
}
