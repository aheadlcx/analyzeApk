package qsbk.app.activity.pay;

import android.os.Handler;
import android.os.Message;

class s extends Handler {
    final /* synthetic */ ItemUnivasualBuyActivity a;

    s(ItemUnivasualBuyActivity itemUnivasualBuyActivity) {
        this.a = itemUnivasualBuyActivity;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        this.a.a(message);
    }
}
