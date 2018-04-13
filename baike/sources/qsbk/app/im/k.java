package qsbk.app.im;

import qsbk.app.utils.comm.ArrayUtils.EqualeOP;

class k extends EqualeOP<ChatMsg> {
    final /* synthetic */ ChatListAdapter a;

    k(ChatListAdapter chatListAdapter) {
        this.a = chatListAdapter;
    }

    public boolean test(ChatMsg chatMsg) {
        return chatMsg.type == 102;
    }
}
