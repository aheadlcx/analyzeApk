package qsbk.app.live.debug;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.live.model.LiveGift;
import qsbk.app.live.model.LiveGiftMessage;
import qsbk.app.live.model.LiveGiftMessageContent;
import qsbk.app.live.model.LiveUser;

class j implements OnClickListener {
    final /* synthetic */ LiveGiftAnimDebugActivity a;

    j(LiveGiftAnimDebugActivity liveGiftAnimDebugActivity) {
        this.a = liveGiftAnimDebugActivity;
    }

    public void onClick(View view) {
        LiveGiftMessageContent liveGiftMessageContent = new LiveGiftMessageContent();
        liveGiftMessageContent.m = "送了一个$";
        LiveGift liveGift = new LiveGift();
        liveGift.i = 14;
        liveGift.n = "圣诞礼物";
        LiveUser liveUser = new LiveUser();
        liveUser.av = "http://avatar.app-remix.com/c3e8340964501f34.jpg?imageMogr2/format/jpg/thumbnail/300x300/auto-orient";
        liveUser.i = 20;
        liveUser.n = "罗洪";
        LiveGiftMessage liveGiftMessage = new LiveGiftMessage();
        liveGiftMessage.i = 20;
        liveGiftMessage.p = 6;
        liveGiftMessage.m = liveGiftMessageContent;
        liveGiftMessage.m.g = liveGift;
        liveGiftMessage.m.u = liveUser;
        this.a.b.addGift(liveGiftMessage);
    }
}
