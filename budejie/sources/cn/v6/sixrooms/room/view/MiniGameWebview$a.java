package cn.v6.sixrooms.room.view;

import android.content.Context;
import android.webkit.JavascriptInterface;
import cn.v6.sixrooms.event.EventManager;
import cn.v6.sixrooms.room.game.DefaultWebviewJavascript;
import cn.v6.sixrooms.room.game.MiniGameMsgEvent;
import cn.v6.sixrooms.utils.LogUtils;

final class MiniGameWebview$a extends DefaultWebviewJavascript {
    final /* synthetic */ MiniGameWebview a;

    public MiniGameWebview$a(MiniGameWebview miniGameWebview, Context context) {
        this.a = miniGameWebview;
        super(context);
    }

    @JavascriptInterface
    public final void appMiniGameChatMsg(String str) {
        MiniGameMsgEvent miniGameMsgEvent = new MiniGameMsgEvent();
        miniGameMsgEvent.setMsg(str);
        EventManager.getDefault().nodifyObservers(miniGameMsgEvent, "miniGameMsgEvent");
    }

    @JavascriptInterface
    public final void appPayMsg() {
        MiniGameWebview.access$100(this.a).show6CoinNotEnoughDialog();
    }

    @JavascriptInterface
    public final String getRoomUid() {
        LogUtils.e(MiniGameWebview.access$200(), MiniGameWebview.access$300(this.a) + "----------------");
        return MiniGameWebview.access$300(this.a);
    }
}
