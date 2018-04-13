package qsbk.app.im;

import qsbk.app.utils.LogUtil;

class cq implements Runnable {
    final /* synthetic */ ConversationActivity a;

    cq(ConversationActivity conversationActivity) {
        this.a = conversationActivity;
    }

    public void run() {
        LogUtil.d("set selection");
        this.a.d.setListSelection(this.a.g.getCount());
    }
}
