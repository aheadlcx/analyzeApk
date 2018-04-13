package cn.v6.sixrooms.ui.phone;

import android.os.Handler;
import android.os.Message;

final class o extends Handler {
    final /* synthetic */ ChangeUserVisibilityActivity a;

    o(ChangeUserVisibilityActivity changeUserVisibilityActivity) {
        this.a = changeUserVisibilityActivity;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 0:
                this.a.initData();
                this.a.initView();
                this.a.initListener();
                return;
            default:
                return;
        }
    }
}
