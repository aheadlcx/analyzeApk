package qsbk.app.im;

import qsbk.app.utils.LogUtil;

class ea implements Runnable {
    final /* synthetic */ dz a;

    ea(dz dzVar) {
        this.a = dzVar;
    }

    public void run() {
        LogUtil.d("set selection");
        this.a.a.d.setListSelection(this.a.a.g.getCount());
    }
}
