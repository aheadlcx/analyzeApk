package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.live.widget.BarrageLayout.BarrageItem;

class e implements OnClickListener {
    final /* synthetic */ BarrageItem a;

    e(BarrageItem barrageItem) {
        this.a = barrageItem;
    }

    public void onClick(View view) {
        if (this.a.a.d != null && this.a.k.getUserId() > 0) {
            this.a.a.d.onAnimAvatarClick(this.a.k.getConvertedUser());
        }
    }
}
