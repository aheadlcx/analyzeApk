package qsbk.app.im;

import qsbk.app.utils.comm.ArrayUtils.Processor;

class bz implements Processor<ChatMsg, Long> {
    final /* synthetic */ bv a;

    bz(bv bvVar) {
        this.a = bvVar;
    }

    public Long process(ChatMsg chatMsg) {
        return Long.valueOf(chatMsg.dbid);
    }
}
