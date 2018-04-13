package qsbk.app.live.ui.bag;

import qsbk.app.core.model.User;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.model.LiveEnterMessage;
import qsbk.app.live.model.LiveEnterMessageContent;
import qsbk.app.live.model.LiveMarketDataRecord;
import qsbk.app.live.model.LiveUser;
import qsbk.app.live.ui.bag.MarketAdapter.MarketItemClickListener;

class p implements MarketItemClickListener {
    final /* synthetic */ n a;

    p(n nVar) {
        this.a = nVar;
    }

    public void onItemClick(LiveMarketDataRecord liveMarketDataRecord) {
        if (liveMarketDataRecord.o == 1 || liveMarketDataRecord.o == 2) {
            LiveEnterMessage liveEnterMessage = new LiveEnterMessage();
            LiveUser liveUser = new LiveUser();
            User user = AppUtils.getInstance().getUserInfoProvider().getUser();
            if (user != null) {
                liveUser.av = user.headurl;
                liveUser.n = user.name;
                liveUser.ld = (int) liveMarketDataRecord.i;
                liveEnterMessage.m = new LiveEnterMessageContent();
                liveEnterMessage.m.u = liveUser;
                this.a.a.b.addEnterMessage(liveEnterMessage);
            }
        }
    }
}
