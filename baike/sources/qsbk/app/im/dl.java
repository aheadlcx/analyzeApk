package qsbk.app.im;

import qsbk.app.utils.comm.ArrayUtils.Processor;

class dl implements Processor<ChatMsg, Object> {
    final /* synthetic */ dj a;

    dl(dj djVar) {
        this.a = djVar;
    }

    public Object process(ChatMsg chatMsg) {
        chatMsg.status = 5;
        return null;
    }
}
