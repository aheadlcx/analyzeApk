package com.umeng.fb;

import com.umeng.fb.model.Conversation.SyncListener;
import com.umeng.fb.model.DevReply;
import com.umeng.fb.model.Reply;
import java.util.List;
import java.util.Locale;
import u.fb.o;

class FeedbackAgent$1 implements SyncListener {
    final /* synthetic */ FeedbackAgent a;

    FeedbackAgent$1(FeedbackAgent feedbackAgent) {
        this.a = feedbackAgent;
    }

    public void onSendUserReply(List<Reply> list) {
    }

    public void onReceiveDevReply(List<DevReply> list) {
        if (list != null && list.size() >= 1) {
            String str = "";
            if (list.size() == 1) {
                String string = FeedbackAgent.a(this.a).getResources().getString(o.c(FeedbackAgent.a(this.a)));
                str = String.format(Locale.US, string, new Object[]{((DevReply) list.get(0)).getContent()});
            } else {
                str = FeedbackAgent.a(this.a).getResources().getString(o.d(FeedbackAgent.a(this.a)));
                str = String.format(Locale.US, str, new Object[]{Integer.valueOf(list.size())});
            }
            try {
                FeedbackAgent.a(this.a, str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
