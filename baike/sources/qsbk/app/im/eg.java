package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;

class eg implements OnClickListener {
    final /* synthetic */ ef a;

    eg(ef efVar) {
        this.a = efVar;
    }

    public void onClick(View view) {
        this.a.a.ai.setVisibility(4);
        this.a.a.ai.setOnClickListener(null);
        this.a.a.aM = this.a.a.aL;
        for (int i = 0; i < this.a.a.g.e.size(); i++) {
            ChatMsg chatMsg = (ChatMsg) this.a.a.g.e.get(i);
            if (chatMsg.dbid == this.a.a.aM) {
                this.a.a.aM = 0;
                this.a.a.d.smoothScrollToPosition(i + 1);
                this.a.a.g._toFlash = chatMsg;
                return;
            }
        }
        this.a.a.d.smoothScrollToPosition(0);
    }
}
