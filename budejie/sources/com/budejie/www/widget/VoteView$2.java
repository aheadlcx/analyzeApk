package com.budejie.www.widget;

import android.graphics.drawable.ClipDrawable;
import android.os.Handler;
import android.os.Message;

class VoteView$2 extends Handler {
    final /* synthetic */ VoteView a;

    VoteView$2(VoteView voteView) {
        this.a = voteView;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message != null) {
            ((ClipDrawable) message.obj).setLevel(message.arg1);
        }
    }
}
