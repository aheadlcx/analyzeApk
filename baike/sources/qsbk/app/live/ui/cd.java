package qsbk.app.live.ui;

import qsbk.app.core.model.RedEnvelopes;
import qsbk.app.live.model.LiveMessage;
import qsbk.app.live.widget.SendRedEnvelopesDialog.OnSendListener;

class cd implements OnSendListener {
    final /* synthetic */ LiveBaseActivity a;

    cd(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public boolean onSend(String str, RedEnvelopes redEnvelopes) {
        if (this.a.a(redEnvelopes.coin)) {
            this.a.s();
            return false;
        }
        this.a.sendLiveMessageAndRefreshUI(LiveMessage.createSendRedEnvelopesMessage(this.a.ax.getOriginId(), str, redEnvelopes));
        this.a.u();
        return true;
    }
}
