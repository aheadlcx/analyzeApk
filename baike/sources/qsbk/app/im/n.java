package qsbk.app.im;

import android.text.TextUtils;
import qsbk.app.utils.comm.ArrayUtils.EqualeOP;

class n extends EqualeOP<ChatMsg> {
    final /* synthetic */ String a;
    final /* synthetic */ ChatListAdapter b;

    n(ChatListAdapter chatListAdapter, String str) {
        this.b = chatListAdapter;
        this.a = str;
    }

    public boolean test(ChatMsg chatMsg) {
        if (TextUtils.isEmpty(chatMsg.msgid)) {
            return false;
        }
        return chatMsg.msgid.equals(this.a);
    }
}
