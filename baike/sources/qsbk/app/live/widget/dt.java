package qsbk.app.live.widget;

import qsbk.app.core.model.GiftData;
import qsbk.app.live.widget.GiftGridView.OnGiftItemClickListener;

class dt implements OnGiftItemClickListener {
    final /* synthetic */ GiftBox a;

    dt(GiftBox giftBox) {
        this.a = giftBox;
    }

    public void onGiftItemClick(int i, GiftData giftData) {
        this.a.i = giftData;
        this.a.c();
        if (this.a.n != null) {
            this.a.n.onGiftItemClick(giftData);
        }
    }
}
