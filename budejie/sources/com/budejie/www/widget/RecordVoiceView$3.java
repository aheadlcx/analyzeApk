package com.budejie.www.widget;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import com.budejie.www.util.aa;
import com.budejie.www.util.ac;

class RecordVoiceView$3 extends Handler {
    final /* synthetic */ RecordVoiceView a;

    RecordVoiceView$3(RecordVoiceView recordVoiceView) {
        this.a = recordVoiceView;
    }

    public void handleMessage(Message message) {
        int intValue;
        switch (message.what) {
            case 22:
                if (RecordVoiceView.a(this.a) == 1) {
                    RecordVoiceView.a(this.a, RecordVoiceView.b(this.a).d());
                    RecordVoiceView.c(this.a);
                    RecordVoiceView.b(this.a, RecordVoiceView.a(this.a));
                    return;
                }
                return;
            case 23:
                intValue = ((Integer) message.obj).intValue();
                RecordVoiceView.c(this.a, intValue);
                RecordVoiceView.d(this.a).setVolume(intValue);
                RecordVoiceView.e(this.a).setVolume(intValue);
                return;
            case 24:
                intValue = ((Integer) message.obj).intValue();
                aa.b("RecordVoiceView", "MSG_REC_TIME_UPDATE time=" + intValue);
                TextView g = RecordVoiceView.g(this.a);
                RecordVoiceView.f(this.a);
                g.setText(ac.a((long) (intValue * 1000)));
                return;
            default:
                return;
        }
    }
}
