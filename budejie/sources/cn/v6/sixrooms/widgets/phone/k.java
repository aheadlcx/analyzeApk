package cn.v6.sixrooms.widgets.phone;

import cn.v6.sixrooms.bean.UserInfoBean;
import cn.v6.sixrooms.room.chat.ChatListAdapter.CallBack;

final class k implements CallBack {
    final /* synthetic */ FullScreenChatPage a;

    k(FullScreenChatPage fullScreenChatPage) {
        this.a = fullScreenChatPage;
    }

    public final void onSetClickableSpan(UserInfoBean userInfoBean, String str) {
        if (this.a.h != null) {
            this.a.h.onPublicChatClickable(userInfoBean, str);
        }
    }
}
