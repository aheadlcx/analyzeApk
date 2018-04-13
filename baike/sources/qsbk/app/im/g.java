package qsbk.app.im;

import qsbk.app.utils.comm.ArrayUtils.EqualeOP;

class g extends EqualeOP<ChatMsg> {
    final /* synthetic */ long a;
    final /* synthetic */ ChatListAdapter b;

    g(ChatListAdapter chatListAdapter, long j) {
        this.b = chatListAdapter;
        this.a = j;
    }

    public boolean test(ChatMsg chatMsg) {
        return chatMsg.dbid == this.a;
    }
}
