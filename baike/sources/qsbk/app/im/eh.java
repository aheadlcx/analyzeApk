package qsbk.app.im;

import android.view.View;
import android.view.View.OnClickListener;

class eh implements OnClickListener {
    final /* synthetic */ ef a;

    eh(ef efVar) {
        this.a = efVar;
    }

    public void onClick(View view) {
        this.a.a.ai.setVisibility(4);
        this.a.a.ai.setOnClickListener(null);
        this.a.a.aK = this.a.a.aJ;
        if (this.a.a.g.e.size() > this.a.a.aK) {
            int size = this.a.a.g.e.size() - this.a.a.aK;
            this.a.a.aK = 0;
            ChatMsg chatMsg = (ChatMsg) this.a.a.g.e.get(size);
            this.a.a.d.smoothScrollToPosition(size);
            this.a.a.g._toFlash = chatMsg;
            return;
        }
        this.a.a.d.smoothScrollToPosition(0);
    }
}
