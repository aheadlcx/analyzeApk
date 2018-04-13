package cn.v6.sixrooms.ui.phone.input;

import android.os.Handler;
import android.os.Message;

final class l extends Handler {
    final /* synthetic */ PrivateInputDialog a;

    l(PrivateInputDialog privateInputDialog) {
        this.a = privateInputDialog;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                if (this.a.d != null) {
                    this.a.d.hidePrivateChatView();
                    return;
                }
                return;
            case 2:
                if (this.a.d != null) {
                    this.a.d.setSelectionToBottom();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
