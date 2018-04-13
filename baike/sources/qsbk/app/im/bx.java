package qsbk.app.im;

import qsbk.app.utils.comm.ArrayUtils.Processor;

class bx implements Processor<ChatMsg, Object> {
    final /* synthetic */ bv a;

    bx(bv bvVar) {
        this.a = bvVar;
    }

    public Object process(ChatMsg chatMsg) {
        chatMsg.status = 5;
        return null;
    }
}
