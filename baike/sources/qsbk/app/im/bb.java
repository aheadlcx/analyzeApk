package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.im.ChatListAdapter.VoiceShowerMe;

class bb implements OnClickListener {
    final /* synthetic */ ay a;
    final /* synthetic */ VoiceShowerMe b;

    bb(VoiceShowerMe voiceShowerMe, ay ayVar) {
        this.b = voiceShowerMe;
        this.a = ayVar;
    }

    public void onClick(View view) {
        if (!this.b.a.r.a()) {
            this.b.a.r.b(this.a);
        } else if (this.b.a.r.a(this.a)) {
            this.b.a.r.b();
        } else {
            this.b.a.r.b(this.a);
        }
    }
}
