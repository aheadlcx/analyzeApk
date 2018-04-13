package qsbk.app.im;

import qsbk.app.utils.comm.ArrayUtils.EqualeOP;

class dk extends EqualeOP<ChatMsg> {
    final /* synthetic */ dj a;

    dk(dj djVar) {
        this.a = djVar;
    }

    public boolean test(ChatMsg chatMsg) {
        return chatMsg.inout == 1 && chatMsg.status == 4;
    }
}
