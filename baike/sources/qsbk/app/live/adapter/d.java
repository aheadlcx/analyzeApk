package qsbk.app.live.adapter;

import android.view.View;
import android.view.View.OnClickListener;

class d implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ BarrageListAdapter b;

    d(BarrageListAdapter barrageListAdapter, int i) {
        this.b = barrageListAdapter;
        this.a = i;
    }

    public void onClick(View view) {
        if (this.b.c != null) {
            this.b.c.onClick(this.a);
        }
    }
}
