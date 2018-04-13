package qsbk.app.live.animation;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.live.model.LiveGiftMessage;
import qsbk.app.live.ui.helper.LiveMessageListener;

class b implements OnClickListener {
    final /* synthetic */ LiveGiftMessage a;
    final /* synthetic */ BaseLargeAnimation b;

    b(BaseLargeAnimation baseLargeAnimation, LiveGiftMessage liveGiftMessage) {
        this.b = baseLargeAnimation;
        this.a = liveGiftMessage;
    }

    public void onClick(View view) {
        LiveMessageListener d = this.b.d();
        if (d != null && this.a.getUserId() > 0) {
            d.onAnimAvatarClick(this.a.getConvertedUser());
        }
    }
}
