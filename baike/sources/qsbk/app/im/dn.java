package qsbk.app.im;

import qsbk.app.utils.comm.ArrayUtils.Processor;

class dn implements Processor<ChatMsg, String> {
    final /* synthetic */ dj a;

    dn(dj djVar) {
        this.a = djVar;
    }

    public String process(ChatMsg chatMsg) {
        return chatMsg.msgid;
    }
}
