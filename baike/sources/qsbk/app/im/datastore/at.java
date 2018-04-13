package qsbk.app.im.datastore;

import qsbk.app.utils.comm.ArrayUtils.Processor;

class at implements Processor<String, String> {
    final /* synthetic */ GroupChatMsgStore a;

    at(GroupChatMsgStore groupChatMsgStore) {
        this.a = groupChatMsgStore;
    }

    public String process(String str) {
        return "\"" + str + "\"";
    }
}
