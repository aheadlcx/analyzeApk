package qsbk.app.live.ui.family;

import android.view.View;
import android.view.View.OnClickListener;

class bk implements OnClickListener {
    final /* synthetic */ Message a;
    final /* synthetic */ MessageAdapter b;

    bk(MessageAdapter messageAdapter, Message message) {
        this.b = messageAdapter;
        this.a = message;
    }

    public void onClick(View view) {
        this.b.a(this.a);
    }
}
