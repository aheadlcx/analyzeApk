package qsbk.app.im;

import qsbk.app.utils.comm.ArrayUtils.Processor;

class by implements Processor<ChatMsg, String> {
    final /* synthetic */ bv a;

    by(bv bvVar) {
        this.a = bvVar;
    }

    public String process(ChatMsg chatMsg) {
        return chatMsg.msgid;
    }
}
