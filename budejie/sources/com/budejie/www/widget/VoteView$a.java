package com.budejie.www.widget;

import android.graphics.drawable.ClipDrawable;
import android.os.Message;

class VoteView$a extends Thread {
    ClipDrawable a;
    int b;
    int c = 500;
    int d = 16;
    int e = 100;
    final /* synthetic */ VoteView f;

    public VoteView$a(VoteView voteView, String str, ClipDrawable clipDrawable, int i) {
        this.f = voteView;
        super(str);
        this.a = clipDrawable;
        this.b = i;
        this.e = (this.d * i) / this.c;
    }

    public void run() {
        Object obj = 1;
        int i = 0;
        while (obj != null) {
            if (i < this.b) {
                i += this.e;
            } else {
                obj = null;
            }
            Message message = new Message();
            message.obj = this.a;
            message.arg1 = i;
            VoteView.b(this.f).sendMessage(message);
            try {
                Thread.sleep((long) this.d);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
