package qsbk.app.live.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.live.adapter.LiveMessageAdapter.ViewHolder;

class r implements OnClickListener {
    final /* synthetic */ ViewHolder a;
    final /* synthetic */ int b;
    final /* synthetic */ LiveMessageAdapter c;

    r(LiveMessageAdapter liveMessageAdapter, ViewHolder viewHolder, int i) {
        this.c = liveMessageAdapter;
        this.a = viewHolder;
        this.b = i;
    }

    public void onClick(View view) {
        if (this.c.x != null) {
            this.c.x.onItemClick(this.a.itemView, this.b);
        }
    }
}
