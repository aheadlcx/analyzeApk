package qsbk.app.live.adapter;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.model.User;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.model.GiftRankData;

class o implements OnClickListener {
    final /* synthetic */ GiftRankData a;
    final /* synthetic */ GiftRankAdapter b;

    o(GiftRankAdapter giftRankAdapter, GiftRankData giftRankData) {
        this.b = giftRankAdapter;
        this.a = giftRankData;
    }

    public void onClick(View view) {
        if (this.a != null && !this.b.e) {
            User user = new User();
            user.id = this.a.p;
            user.origin = this.a.s;
            user.origin_id = this.a.b;
            AppUtils.getInstance().getUserInfoProvider().toUserPage((Activity) this.b.c, user);
        }
    }
}
