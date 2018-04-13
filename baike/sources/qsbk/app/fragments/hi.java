package qsbk.app.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.baidu.mobstat.Config;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.im.IMChatMsgSource;
import qsbk.app.im.OfficialInfoActivity;
import qsbk.app.nearby.api.NearbyUser;
import qsbk.app.utils.ListUtil;
import qsbk.app.utils.UserClickDelegate;

class hi implements OnItemClickListener {
    final /* synthetic */ NearbyUsersFragment a;

    hi(NearbyUsersFragment nearbyUsersFragment) {
        this.a = nearbyUsersFragment;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int headerCount = ListUtil.getHeaderCount(this.a.c);
        if (i >= headerCount) {
            Object item = this.a.a.getItem(i - headerCount);
            if (item instanceof NearbyUser) {
                NearbyUser nearbyUser = (NearbyUser) item;
                if (UserClickDelegate.isOfficialAccount(nearbyUser.userId)) {
                    OfficialInfoActivity.launch(view.getContext(), nearbyUser.userId, nearbyUser.userName, nearbyUser.userIcon);
                    return;
                }
                MyInfoActivity.launch(view.getContext(), nearbyUser.userId, MyInfoActivity.FANS_ORIGINS[2], new IMChatMsgSource(1, nearbyUser.userId, nearbyUser.mDistance + Config.TRACE_TODAY_VISIT_SPLIT + nearbyUser.haunt));
            }
        }
    }
}
