package cn.v6.sixrooms.ui.phone.input;

import android.os.Handler;
import android.os.Message;
import cn.v6.sixrooms.room.presenter.InroomPresenter;
import java.util.ArrayList;

final class o extends Handler {
    final /* synthetic */ RoomFullInputDialog a;

    o(RoomFullInputDialog roomFullInputDialog) {
        this.a = roomFullInputDialog;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 8:
                ArrayList arrayList = (ArrayList) message.obj;
                if (this.a.e != null) {
                    this.a.e.setChatListDataAndRefreshAdapter(arrayList, true, true, false, true);
                    return;
                }
                return;
            case 12:
                if (this.a.e != null) {
                    this.a.e.refreshChatList(InroomPresenter.getInstance().getLocalRoomInfo().getRoominfoBean().getId(), (String) message.obj);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
