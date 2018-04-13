package com.umeng.fb.model;

import android.os.Handler;
import android.os.Message;
import com.umeng.fb.model.Conversation.SyncListener;
import com.umeng.fb.model.Reply.STATUS;
import java.util.Iterator;
import java.util.List;

class Conversation$SyncHandler extends Handler {
    static final int b = 1;
    static final int c = 2;
    SyncListener a;
    final /* synthetic */ Conversation d;

    public Conversation$SyncHandler(Conversation conversation, SyncListener syncListener) {
        this.d = conversation;
        this.a = syncListener;
    }

    public void handleMessage(Message message) {
        Object obj = 1;
        Reply reply;
        if (message.what == 2) {
            reply = (Reply) message.obj;
            if (message.arg1 != 1) {
                obj = null;
            }
            if (obj != null) {
                reply.i = STATUS.SENT;
            }
        } else if (message.what == 1) {
            Conversation$MessageWrapper conversation$MessageWrapper = (Conversation$MessageWrapper) message.obj;
            List list = conversation$MessageWrapper.b;
            List list2 = conversation$MessageWrapper.a;
            if (list != null) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    reply = (Reply) it.next();
                    if (Conversation.a(this.d).containsKey(reply.c)) {
                        it.remove();
                    } else {
                        Conversation.a(this.d).put(reply.c, reply);
                    }
                }
            }
            Conversation.b(this.d);
            if (this.a != null) {
                this.a.onReceiveDevReply(list);
                this.a.onSendUserReply(list2);
            }
        }
    }
}
