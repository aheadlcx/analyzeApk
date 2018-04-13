package qsbk.app.live.adapter;

import android.view.View;
import android.view.View.OnClickListener;

class y implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ SendRedEnvelopesAdapter b;

    y(SendRedEnvelopesAdapter sendRedEnvelopesAdapter, int i) {
        this.b = sendRedEnvelopesAdapter;
        this.a = i;
    }

    public void onClick(View view) {
        this.b.c = this.a;
        this.b.notifyDataSetChanged();
    }
}
