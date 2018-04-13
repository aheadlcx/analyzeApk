package qsbk.app.core.ui;

import qsbk.app.core.utils.ToastUtil;

class f implements Runnable {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public void run() {
        ToastUtil.Long("图片成功保存至：SD卡/Save/ 目录下");
    }
}
