package cn.v6.sixrooms.surfaceanim.service;

import android.os.Handler;
import android.os.Message;
import cn.v6.sixrooms.surfaceanim.util.AnimBeanHelper;

final class a extends Handler {
    final /* synthetic */ AnimService a;

    a(AnimService animService) {
        this.a = animService;
    }

    public final void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case 1:
                this.a.c.addAnimScene(AnimBeanHelper.getAnimBeanFromBundle(message.getData()));
                return;
            default:
                return;
        }
    }
}
