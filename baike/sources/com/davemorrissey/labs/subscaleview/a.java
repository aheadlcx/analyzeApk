package com.davemorrissey.labs.subscaleview;

import android.os.Handler.Callback;
import android.os.Message;

class a implements Callback {
    final /* synthetic */ SubsamplingScaleImageView a;

    a(SubsamplingScaleImageView subsamplingScaleImageView) {
        this.a = subsamplingScaleImageView;
    }

    public boolean handleMessage(Message message) {
        if (message.what == 1 && this.a.ao != null) {
            this.a.U = 0;
            super.setOnLongClickListener(this.a.ao);
            this.a.performLongClick();
            super.setOnLongClickListener(null);
        }
        return true;
    }
}
