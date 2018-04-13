package qsbk.app.im;

import android.graphics.Color;

class jp implements Runnable {
    final /* synthetic */ VoiceUIHelper a;

    jp(VoiceUIHelper voiceUIHelper) {
        this.a = voiceUIHelper;
    }

    public void run() {
        if (this.a.w) {
            this.a.o = this.a.o + 1;
            this.a.q.setText(ChatMsgVoiceData.formatDuration(this.a.o));
            int c = 60 - this.a.o;
            if (c == 0) {
                this.a.b();
                return;
            }
            if (c <= 10) {
                this.a.q.setTextColor(Color.parseColor("#ff3d1d"));
            } else {
                this.a.q.setTextColor(Color.parseColor("#63625e"));
            }
            this.a.e.y.postDelayed(this, 1000);
        }
    }
}
