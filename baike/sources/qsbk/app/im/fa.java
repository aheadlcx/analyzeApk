package qsbk.app.im;

import qsbk.app.utils.LogUtil;

class fa implements Runnable {
    final /* synthetic */ GroupConversationActivity a;

    fa(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void run() {
        LogUtil.d("set selection");
        this.a.d.setListSelection(this.a.g.getCount());
    }
}
