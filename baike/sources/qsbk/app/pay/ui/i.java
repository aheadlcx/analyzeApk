package qsbk.app.pay.ui;

import qsbk.app.core.utils.ToastUtil;

class i implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ h b;

    i(h hVar, String str) {
        this.b = hVar;
        this.a = str;
    }

    public void run() {
        ToastUtil.Long(this.a);
    }
}
