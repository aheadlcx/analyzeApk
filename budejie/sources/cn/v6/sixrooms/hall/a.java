package cn.v6.sixrooms.hall;

import android.content.Intent;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.room.fragment.RoomBaseFragment;

final class a implements a {
    final /* synthetic */ AttentionFragment a;

    a(AttentionFragment attentionFragment) {
        this.a = attentionFragment;
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
