package qsbk.app.live.ui;

import android.text.TextUtils;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.core.utils.websocket.WebSocketHandler.OnMessageListener;
import qsbk.app.live.model.LiveMessage;

class cw extends OnMessageListener {
    final /* synthetic */ String a;
    final /* synthetic */ LiveBaseActivity b;

    cw(LiveBaseActivity liveBaseActivity, String str) {
        this.b = liveBaseActivity;
        this.a = str;
    }

    public void onReceiveMessage(Object obj) {
        this.b.statMessageCountPerSecond();
        this.b.a((LiveMessage) obj);
    }

    public void onConnect() {
        this.b.Q();
        LogUtils.d(LiveBaseActivity.a, "live status: connected to " + this.b.aF);
        this.b.S();
        if (this.b.O() && LiveBaseActivity.B(this.b)) {
            this.b.a(LiveMessage.createConnectMessage(LiveBaseActivity.C(this.b)));
        }
        this.b.aH = 0;
        this.b.b = 1;
        this.b.c = false;
        if (!(LiveBaseActivity.D(this.b) || AppUtils.getInstance().getUserInfoProvider().isLogin() || this.b.e == null || TextUtils.isEmpty(this.b.e.sys_msg))) {
            LiveBaseActivity.c(this.b, true);
            LiveBaseActivity.b(this.b, true);
            this.b.a(LiveMessage.createSystemMessage(this.b.ax.getOriginId(), this.b.e.sys_msg));
        }
        if (!(this.b.e == null || this.b.e.room_status == null || this.b.e.room_status.status != 2)) {
            this.b.a(LiveMessage.createAnchorSuspendMessage(this.b.ax.getOriginId(), this.b.e.room_status.message));
        }
        this.b.U();
    }

    public void onDisconnect(int i, String str) {
        this.b.d("(" + i + ")" + str);
        if (i == 403) {
            NetRequest.getInstance().get(this.a.replaceFirst("ws", "http"), new cx(this, str), "get_error_msg", true);
        } else {
            this.b.T();
        }
    }

    public void onError(Exception exception) {
        this.b.d(exception != null ? exception.getMessage() : "");
        this.b.T();
    }
}
