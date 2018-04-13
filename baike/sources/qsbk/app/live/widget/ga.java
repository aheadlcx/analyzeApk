package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.live.model.LiveGiftMessage;

class ga implements OnClickListener {
    final /* synthetic */ LiveGiftMessage a;
    final /* synthetic */ LargeGiftLayout b;

    ga(LargeGiftLayout largeGiftLayout, LiveGiftMessage liveGiftMessage) {
        this.b = largeGiftLayout;
        this.a = liveGiftMessage;
    }

    public void onClick(View view) {
        if (this.b.i != null && this.a.getUserId() > 0) {
            this.b.i.onAnimAvatarClick(this.a.getConvertedUser());
        }
    }
}
