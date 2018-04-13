package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.fragments.BaseNearByUserFragment.NearbyAdapter;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.model.LiveRoom;

class l implements OnClickListener {
    final /* synthetic */ LiveRoom a;
    final /* synthetic */ NearbyAdapter b;

    l(NearbyAdapter nearbyAdapter, LiveRoom liveRoom) {
        this.b = nearbyAdapter;
        this.a = liveRoom;
    }

    public void onClick(View view) {
        if (this.a.isLiveBegin()) {
            AppUtils.getInstance().getUserInfoProvider().toLive(this.b.a.getActivity(), this.a.author.id, this.a.author.id, this.a.author.origin);
        } else if (this.a.author != null) {
            MyInfoActivity.launch(this.b.a.getActivity(), this.a.author.platform_id + "", MyInfoActivity.FANS_ORIGINS[4], new IMChatMsgSource(9, this.a.author.platform_id + "", "来自直播"));
        }
    }
}
