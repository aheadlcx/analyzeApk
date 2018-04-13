package com.budejie.www.activity.view;

import android.os.Handler;
import android.os.Message;

class AudioLayout$1 extends Handler {
    final /* synthetic */ AudioLayout a;

    AudioLayout$1(AudioLayout audioLayout) {
        this.a = audioLayout;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                AudioLayout.a(this.a).setVisibility(8);
                AudioLayout.b(this.a).setVisibility(8);
                AudioLayout.c(this.a).setVisibility(0);
                AudioLayout.c(this.a).a(true);
                return;
            default:
                return;
        }
    }
}
