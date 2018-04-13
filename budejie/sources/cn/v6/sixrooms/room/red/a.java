package cn.v6.sixrooms.room.red;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import cn.v6.sixrooms.room.gift.GiftConfigUtil;

final class a extends Handler {
    final /* synthetic */ DragPopupWindowManager a;

    a(DragPopupWindowManager dragPopupWindowManager) {
        this.a = dragPopupWindowManager;
    }

    public final void handleMessage(Message message) {
        if (!this.a.a) {
            Bundle data = message.getData();
            switch (message.what) {
                case 1:
                    this.a.a(data.getStringArray("redid"));
                    return;
                case 2:
                    DragPopupWindowManager.a(this.a, data.getStringArray("id"), data.getStringArray("arrayTime"));
                    return;
                case 3:
                    DragPopupWindowManager.b(this.a, data.getStringArray("id"), data.getStringArray("redid"));
                    return;
                case 4:
                    DragPopupWindowManager.a(this.a, data.getString("redid"), GiftConfigUtil.SUPER_GIRL_GIFT_TAG.equals(data.getString("content")) ? 2 : 1);
                    return;
                case 5:
                    DragPopupWindowManager.a(this.a, data.getString("redid"), data.getString("state"), data.getString("content"));
                    return;
                case 6:
                    DragPopupWindowManager.a(this.a, data.getString("redid"));
                    return;
                default:
                    return;
            }
        }
    }
}
