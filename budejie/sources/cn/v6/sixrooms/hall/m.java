package cn.v6.sixrooms.hall;

import android.view.View;
import android.view.View.OnClickListener;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.room.statistic.StatiscProxy;
import com.c.a.c;

final class m implements OnClickListener {
    final /* synthetic */ c a;
    final /* synthetic */ HostsAdapter b;

    m(HostsAdapter hostsAdapter, c cVar) {
        this.b = hostsAdapter;
        this.a = cVar;
    }

    public final void onClick(View view) {
        if (this.b.c != null) {
            int layoutPosition = this.a.getLayoutPosition();
            if (this.b.b != null && this.b.b.size() != 0) {
                LiveItemBean liveItemBean = (LiveItemBean) this.b.b.get(layoutPosition);
                if (!V6Coop.flag) {
                    V6Coop.flag = true;
                    StatiscProxy.homeIntoRoomListStatistics(this.b.a, liveItemBean.getUid(), liveItemBean.getType(), liveItemBean.getRecid(), layoutPosition);
                    this.b.c.a(liveItemBean);
                }
            }
        }
    }
}
