package qsbk.app.im;

import qsbk.app.utils.comm.ArrayUtils.EqualeOP;

class bw extends EqualeOP<ChatMsg> {
    final /* synthetic */ bv a;

    bw(bv bvVar) {
        this.a = bvVar;
    }

    public boolean test(ChatMsg chatMsg) {
        return chatMsg.inout == 1 && chatMsg.status == 4;
    }
}
