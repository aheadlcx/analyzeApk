package com.umeng.fb.model;

import android.os.Handler;
import android.os.Message;
import com.umeng.fb.model.Reply.STATUS;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import u.fb.h;

class Conversation$1 implements Runnable {
    final /* synthetic */ Conversation a;
    private final /* synthetic */ Handler b;

    Conversation$1(Conversation conversation, Handler handler) {
        this.a = conversation;
        this.b = handler;
    }

    public void run() {
        List<Reply> arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        Date date = null;
        String str = "";
        for (Entry value : Conversation.a(this.a).entrySet()) {
            Reply reply = (Reply) value.getValue();
            if ((reply instanceof UserReply) || (reply instanceof UserTitleReply)) {
                if (reply.i == STATUS.NOT_SENT) {
                    arrayList.add(reply);
                }
            } else if ((reply instanceof DevReply) && (r2 == null || r2.compareTo(reply.getDatetime()) < 0)) {
                date = reply.getDatetime();
                str = reply.c;
            }
        }
        arrayList2.add(Conversation.c(this.a));
        for (Reply reply2 : arrayList) {
            boolean a = new h(Conversation.d(this.a)).a(reply2);
            if (a) {
                Message obtain = Message.obtain();
                obtain.what = 2;
                obtain.obj = reply2;
                obtain.arg1 = a ? 1 : 0;
                this.b.sendMessage(obtain);
            }
        }
        List a2 = new h(Conversation.d(this.a)).a(arrayList2, str, Conversation.e(this.a));
        Message obtain2 = Message.obtain();
        obtain2.what = 1;
        Conversation$MessageWrapper conversation$MessageWrapper = new Conversation$MessageWrapper();
        conversation$MessageWrapper.b = a2;
        conversation$MessageWrapper.a = arrayList;
        obtain2.obj = conversation$MessageWrapper;
        this.b.sendMessage(obtain2);
    }
}
