package qsbk.app.im.datastore;

import qsbk.app.utils.comm.ArrayUtils.Processor;

class v implements Processor<String, String> {
    final /* synthetic */ ChatMsgStore a;

    v(ChatMsgStore chatMsgStore) {
        this.a = chatMsgStore;
    }

    public String process(String str) {
        return "\"" + str + "\"";
    }
}
