package cn.v6.sixrooms.room.presenter;

import android.os.Handler;
import android.os.Message;
import cn.v6.sixrooms.bean.RoommsgBean;
import java.util.List;

final class i extends Handler {
    final /* synthetic */ PrivateChatPresenter a;

    i(PrivateChatPresenter privateChatPresenter) {
        this.a = privateChatPresenter;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 256:
                RoommsgBean roommsgBean = (RoommsgBean) message.obj;
                if (roommsgBean != null) {
                    if (this.a.d.size() >= 200) {
                        this.a.d.remove(0);
                    }
                    this.a.d.add(roommsgBean);
                    if (!(this.a.e || this.a.c == null)) {
                        this.a.c.setNewMsgViewShow();
                    }
                    this.a.b.notifyDataSetChanged();
                    return;
                }
                return;
            case 257:
                List list = (List) message.obj;
                if (list != null) {
                    if (this.a.d.size() >= 200) {
                        for (int i = 0; i < list.size(); i++) {
                            this.a.d.remove(0);
                        }
                    }
                    this.a.d.addAll(list);
                    this.a.b.notifyDataSetChanged();
                    return;
                }
                return;
            case 258:
                this.a.d.clear();
                this.a.b.notifyDataSetChanged();
                return;
            case 259:
                if (this.a.d.size() > 1) {
                    this.a.b.setSelection(this.a.d.size());
                    return;
                }
                return;
            default:
                return;
        }
    }
}
