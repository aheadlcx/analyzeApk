package qsbk.app.live.ui;

import android.view.View;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.adapter.LiveMessageAdapter.OnItemClickLitener;
import qsbk.app.live.model.LiveMessage;
import qsbk.app.live.model.LiveRemindMessage;

class ck implements OnItemClickLitener {
    final /* synthetic */ LiveBaseActivity a;

    ck(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void onItemClick(View view, int i) {
        if (i < this.a.P.size() && !((LiveMessage) this.a.P.get(i)).isRemindMessage()) {
            this.a.a(((LiveMessage) this.a.P.get(i)).getConvertedUser());
        } else if (i < this.a.P.size() && ((LiveMessage) this.a.P.get(i)).isRemindMessage()) {
            switch (((LiveRemindMessage) this.a.P.get(i)).getType()) {
                case 1:
                    LiveBaseActivity.a(this.a);
                    break;
                case 2:
                    this.a.f();
                    break;
                case 3:
                    this.a.p();
                    break;
            }
            this.a.sendLiveMessageAndRefreshUI(LiveMessage.createFollowHintMessage(AppUtils.getInstance().getUserInfoProvider().getUserId(), ((LiveRemindMessage) this.a.P.get(i)).getType()));
            this.a.P.remove(i);
            this.a.O.notifyDataSetChanged();
        }
    }

    public void onItemLongClick(View view, int i) {
    }
}
