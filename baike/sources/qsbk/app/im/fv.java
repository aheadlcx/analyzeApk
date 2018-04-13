package qsbk.app.im;

import qsbk.app.utils.LogUtil;

class fv implements Runnable {
    final /* synthetic */ IMChatBaseActivityEx a;

    fv(IMChatBaseActivityEx iMChatBaseActivityEx) {
        this.a = iMChatBaseActivityEx;
    }

    public void run() {
        LogUtil.d("set selection");
        this.a.d.setSelection(this.a.g.getCount());
    }
}
