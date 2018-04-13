package qsbk.app.live.widget;

import android.view.View;
import qsbk.app.core.model.GiftData;
import qsbk.app.live.R;
import qsbk.app.live.widget.GiftGridView.OnGiftItemClickListener;

class ec implements OnGiftItemClickListener {
    final /* synthetic */ GiftViewPager a;

    ec(GiftViewPager giftViewPager) {
        this.a = giftViewPager;
    }

    public void onGiftItemClick(int i, GiftData giftData) {
        for (int i2 = 0; i2 < this.a.e.size(); i2++) {
            if (i2 != this.a.getCurrentItem()) {
                ((GiftGridView) ((View) this.a.e.get(i2)).findViewById(R.id.live_grid)).clearGiftCheck();
            }
        }
    }
}
