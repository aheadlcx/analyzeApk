package qsbk.app.im;

import qsbk.app.utils.LogUtil;

class ck implements Runnable {
    final /* synthetic */ cj a;

    ck(cj cjVar) {
        this.a = cjVar;
    }

    public void run() {
        LogUtil.d("set selection");
        this.a.a.d.setListSelection(this.a.a.g.getCount());
    }
}
