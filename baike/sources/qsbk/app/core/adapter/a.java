package qsbk.app.core.adapter;

import android.view.View;
import android.view.View.OnClickListener;

class a implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ ShareAdapter b;

    a(ShareAdapter shareAdapter, int i) {
        this.b = shareAdapter;
        this.a = i;
    }

    public void onClick(View view) {
        if (this.b.a != null) {
            this.b.a.onShareItemClicked(this.a);
        }
    }
}
