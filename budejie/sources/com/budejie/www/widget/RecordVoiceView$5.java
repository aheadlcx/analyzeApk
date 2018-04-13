package com.budejie.www.widget;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import com.budejie.www.util.ac;

class RecordVoiceView$5 extends Handler {
    final /* synthetic */ RecordVoiceView a;

    RecordVoiceView$5(RecordVoiceView recordVoiceView) {
        this.a = recordVoiceView;
    }

    public void handleMessage(Message message) {
        TextView g;
        switch (message.what) {
            case 1:
                RecordVoiceView.i(this.a).setMax(RecordVoiceView.f(this.a).k());
                g = RecordVoiceView.g(this.a);
                RecordVoiceView.f(this.a);
                g.setText(ac.a(0));
                RecordVoiceView.h(this.a).sendEmptyMessage(2);
                return;
            case 2:
                g = RecordVoiceView.g(this.a);
                RecordVoiceView.f(this.a);
                g.setText(ac.a((long) RecordVoiceView.f(this.a).j()));
                RecordVoiceView.i(this.a).setProgress((float) RecordVoiceView.f(this.a).j());
                RecordVoiceView.h(this.a).sendEmptyMessageDelayed(2, 100);
                return;
            default:
                return;
        }
    }
}
