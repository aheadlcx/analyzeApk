package qsbk.app.live.widget;

import android.view.View;
import android.view.View.OnClickListener;

class hq implements OnClickListener {
    final /* synthetic */ ProTopRankView a;

    hq(ProTopRankView proTopRankView) {
        this.a = proTopRankView;
    }

    public void onClick(View view) {
        if (this.a.k != null && this.a.f.getUserId() > 0) {
            this.a.k.onAnimAvatarClick(this.a.f.getConvertedUser());
        }
    }
}
