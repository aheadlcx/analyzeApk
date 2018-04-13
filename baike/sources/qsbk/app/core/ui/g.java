package qsbk.app.core.ui;

import qsbk.app.core.utils.ToastUtil;

class g implements Runnable {
    final /* synthetic */ e a;

    g(e eVar) {
        this.a = eVar;
    }

    public void run() {
        ToastUtil.Long("保存失败，请稍后重试");
    }
}
