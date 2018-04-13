package qsbk.app.im;

import qsbk.app.utils.comm.ArrayUtils.EqualeOP;

class f extends EqualeOP<ChatMsg> {
    final /* synthetic */ ChatListAdapter a;

    f(ChatListAdapter chatListAdapter) {
        this.a = chatListAdapter;
    }

    public boolean test(ChatMsg chatMsg) {
        return chatMsg.showTime;
    }
}
