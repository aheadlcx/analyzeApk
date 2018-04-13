package qsbk.app.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.mobstat.Config;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.fragments.BaseNearByUserFragment.NearbyAdapter;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.im.OfficialInfoActivity;
import qsbk.app.nearby.api.NearbyUser;
import qsbk.app.utils.UserClickDelegate;

class k implements OnClickListener {
    final /* synthetic */ NearbyUser a;
    final /* synthetic */ NearbyAdapter b;

    k(NearbyAdapter nearbyAdapter, NearbyUser nearbyUser) {
        this.b = nearbyAdapter;
        this.a = nearbyUser;
    }

    public void onClick(View view) {
        if (UserClickDelegate.isOfficialAccount(this.a.userId)) {
            OfficialInfoActivity.launch(this.b.k, this.a.userId, this.a.userName, this.a.userIcon);
            return;
        }
        MyInfoActivity.launch(this.b.k, this.a.userId, MyInfoActivity.FANS_ORIGINS[2], new IMChatMsgSource(1, this.a.userId, this.a.mDistance + Config.TRACE_TODAY_VISIT_SPLIT + this.a.haunt));
    }
}
