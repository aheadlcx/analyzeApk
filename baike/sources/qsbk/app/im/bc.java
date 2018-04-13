package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.im.ChatListAdapter.VoiceShowerOther;

class bc implements OnClickListener {
    final /* synthetic */ ay a;
    final /* synthetic */ VoiceShowerOther b;

    bc(VoiceShowerOther voiceShowerOther, ay ayVar) {
        this.b = voiceShowerOther;
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
