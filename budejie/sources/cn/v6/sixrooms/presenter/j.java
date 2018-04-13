package cn.v6.sixrooms.presenter;

import android.os.Handler;
import android.os.Message;

final class j extends Handler {
    final /* synthetic */ RedPresenter a;

    j(RedPresenter redPresenter) {
        this.a = redPresenter;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 0:
                RedPresenter.a(this.a);
                return;
            default:
                return;
        }
    }
}
