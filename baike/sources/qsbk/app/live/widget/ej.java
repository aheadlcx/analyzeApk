package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.live.widget.GlobalGiftView.MarqueeItem;

class ej implements OnClickListener {
    final /* synthetic */ MarqueeItem a;

    ej(MarqueeItem marqueeItem) {
        this.a = marqueeItem;
    }

    public void onClick(View view) {
        if (this.a.a.c != null && this.a.e.getUserId() > 0) {
            this.a.a.c.onAnimAvatarClick(this.a.e.getConvertedUser());
        }
    }
}
