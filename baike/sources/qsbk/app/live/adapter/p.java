package qsbk.app.live.adapter;

import android.view.View;
import android.view.View.OnClickListener;

class p implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ GiftRankRichAdapter b;

    p(GiftRankRichAdapter giftRankRichAdapter, int i) {
        this.b = giftRankRichAdapter;
        this.a = i;
    }

    public void onClick(View view) {
        if (this.b.c != null) {
            this.b.c.onItemClick(view, this.a);
        }
    }
}
