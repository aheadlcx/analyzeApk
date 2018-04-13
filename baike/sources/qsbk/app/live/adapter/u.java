package qsbk.app.live.adapter;

import android.view.View;
import android.view.View.OnLongClickListener;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.model.LiveMessage;

class u implements OnLongClickListener {
    final /* synthetic */ LiveMessage a;
    final /* synthetic */ LiveMessageAdapter b;

    u(LiveMessageAdapter liveMessageAdapter, LiveMessage liveMessage) {
        this.b = liveMessageAdapter;
        this.a = liveMessage;
    }

    public boolean onLongClick(View view) {
        AppUtils.copyToClipboard(this.b.a, this.a.getContent());
        return true;
    }
}
