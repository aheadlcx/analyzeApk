package com.budejie.www.widget;

import android.os.Handler;
import android.os.Message;
import com.budejie.www.R;

class f$1 extends Handler {
    final /* synthetic */ f a;

    f$1(f fVar) {
        this.a = fVar;
    }

    public void handleMessage(Message message) {
        try {
            this.a.dismiss();
            f.a(this.a).setVisibility(8);
            f.b(this.a).setVisibility(0);
            f.c(this.a).setText(R.string.send_comment_dialog_uploading);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
