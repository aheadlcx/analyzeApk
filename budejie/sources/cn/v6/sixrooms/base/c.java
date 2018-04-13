package cn.v6.sixrooms.base;

import cn.v6.sixrooms.utils.ToastUtils;

final class c extends VLBlock {
    final /* synthetic */ String a;
    final /* synthetic */ V6CallbackHandler b;

    c(V6CallbackHandler v6CallbackHandler, String str) {
        this.b = v6CallbackHandler;
        this.a = str;
    }

    protected final void process(boolean z) {
        ToastUtils.showToast(this.a);
    }
}
