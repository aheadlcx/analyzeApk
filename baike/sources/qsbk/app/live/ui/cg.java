package qsbk.app.live.ui;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.live.model.LiveRedEnvelopesMessage;

class cg implements OnClickListener {
    final /* synthetic */ LiveRedEnvelopesMessage a;
    final /* synthetic */ LiveBaseActivity b;

    cg(LiveBaseActivity liveBaseActivity, LiveRedEnvelopesMessage liveRedEnvelopesMessage) {
        this.b = liveBaseActivity;
        this.a = liveRedEnvelopesMessage;
    }

    public void onClick(View view) {
        this.b.b(this.a);
    }
}
