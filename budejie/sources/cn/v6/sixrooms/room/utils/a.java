package cn.v6.sixrooms.room.utils;

import cn.v6.sixrooms.base.VLAsyncHandler;
import cn.v6.sixrooms.utils.LogUtils;

final class a extends VLAsyncHandler<String> {
    final /* synthetic */ AppSclickManager a;

    a(AppSclickManager appSclickManager) {
        this.a = appSclickManager;
        super(null, 0);
    }

    protected final void handler(boolean z) {
        if (z) {
            LogUtils.i("AppSclickManager", "result=" + ((String) getParam()));
        }
    }
}
