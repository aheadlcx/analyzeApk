package qsbk.app.live.adapter;

import android.view.View;
import android.view.View.OnClickListener;

class v implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ LiveMessageAdapter b;

    v(LiveMessageAdapter liveMessageAdapter, int i) {
        this.b = liveMessageAdapter;
        this.a = i;
    }

    public void onClick(View view) {
        if (this.b.x != null) {
            this.b.x.onItemClick(view, this.a);
        }
    }
}
