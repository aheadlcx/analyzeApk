package cn.v6.sixrooms.surfaceanim;

import android.os.Handler;
import android.os.Message;

final class c extends Handler {
    final /* synthetic */ AnimRenderManager a;

    c(AnimRenderManager animRenderManager) {
        this.a = animRenderManager;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.a.h = 1;
                this.a.render();
                return;
            case 2:
                this.a.h = 2;
                this.a.renderStop();
                return;
            case 3:
                this.a.h = 3;
                this.a.renderPause();
                return;
            case 4:
                this.a.renderSizeChanged(message.arg1, message.arg2);
                return;
            default:
                return;
        }
    }
}
