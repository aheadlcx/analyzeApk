package com.budejie.www.widget;

import com.budejie.www.f.b;

class RecordVoiceView$4 implements b {
    final /* synthetic */ RecordVoiceView a;

    RecordVoiceView$4(RecordVoiceView recordVoiceView) {
        this.a = recordVoiceView;
    }

    public void a() {
        RecordVoiceView.h(this.a).sendEmptyMessage(1);
    }

    public void b() {
    }

    public void a(int i) {
        RecordVoiceView.h(this.a).removeMessages(2);
        if (RecordVoiceView.a(this.a) == 3) {
            RecordVoiceView.d(this.a, 2);
            RecordVoiceView.b(this.a, RecordVoiceView.a(this.a));
        }
    }
}
