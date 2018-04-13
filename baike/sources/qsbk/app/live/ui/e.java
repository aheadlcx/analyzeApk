package qsbk.app.live.ui;

import android.view.View;
import qsbk.app.core.model.User;
import qsbk.app.live.adapter.LiveMessageAdapter.OnItemClickLitener;
import qsbk.app.live.model.GiftRankRichData;

class e implements OnItemClickLitener {
    final /* synthetic */ LiveBaseActivity a;

    e(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void onItemClick(View view, int i) {
        if (i < this.a.Q.size()) {
            User user = new User();
            GiftRankRichData giftRankRichData = (GiftRankRichData) this.a.Q.get(i);
            user.id = giftRankRichData.id;
            user.headurl = giftRankRichData.avatar;
            user.origin = giftRankRichData.source;
            this.a.a(user);
        }
    }

    public void onItemLongClick(View view, int i) {
    }
}
