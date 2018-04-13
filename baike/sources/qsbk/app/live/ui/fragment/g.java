package qsbk.app.live.ui.fragment;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.model.User;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.model.GiftRankData;

class g implements OnClickListener {
    final /* synthetic */ GiftRankData a;
    final /* synthetic */ GiftRankFragment b;

    g(GiftRankFragment giftRankFragment, GiftRankData giftRankData) {
        this.b = giftRankFragment;
        this.a = giftRankData;
    }

    public void onClick(View view) {
        if (this.a != null && !this.b.j) {
            User user = new User();
            user.id = this.a.p;
            user.origin = this.a.s;
            user.origin_id = this.a.b;
            AppUtils.getInstance().getUserInfoProvider().toUserPage(this.b.getActivity(), user);
        }
    }
}
