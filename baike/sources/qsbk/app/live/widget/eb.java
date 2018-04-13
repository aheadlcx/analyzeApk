package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.live.widget.GiftLayout.GiftInfo;

class eb implements OnClickListener {
    final /* synthetic */ GiftInfo a;

    eb(GiftInfo giftInfo) {
        this.a = giftInfo;
    }

    public void onClick(View view) {
        if (this.a.a.h != null && this.a.j.getUserId() > 0) {
            this.a.a.h.onAnimAvatarClick(this.a.j.getConvertedUser());
        }
    }
}
