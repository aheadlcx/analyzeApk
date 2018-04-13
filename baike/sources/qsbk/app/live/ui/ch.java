package qsbk.app.live.ui;

import qsbk.app.core.model.User;
import qsbk.app.live.model.LiveMessage;
import qsbk.app.live.widget.RedEnvelopesDialog.OnSendListener;

class ch implements OnSendListener {
    final /* synthetic */ LiveBaseActivity a;

    ch(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public boolean onSend(String str, long j) {
        this.a.sendLiveMessageAndRefreshUI(LiveMessage.createRobRedEnvelopesMessage(this.a.ax.getOriginId(), str, j));
        return true;
    }

    public void onAvartarClick(User user) {
        this.a.a(user);
    }
}
