package cn.v6.sixrooms.hall;

import android.content.Intent;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.room.fragment.RoomBaseFragment;

final class q implements a {
    final /* synthetic */ HostsFragment a;

    q(HostsFragment hostsFragment) {
        this.a = hostsFragment;
    }

    public final void a(LiveItemBean liveItemBean) {
        if (liveItemBean != null) {
            Intent intent = new Intent(this.a.getActivity(), RoomActivity.class);
            intent.putExtra("rid", liveItemBean.getRid());
            intent.putExtra(RoomBaseFragment.RUID_KEY, liveItemBean.getUid());
            this.a.getActivity().startActivity(intent);
        }
    }
}
