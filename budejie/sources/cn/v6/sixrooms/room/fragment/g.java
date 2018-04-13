package cn.v6.sixrooms.room.fragment;

import cn.v6.sixrooms.R;
import cn.v6.sixrooms.utils.LogUtils;

final class g implements Runnable {
    final /* synthetic */ FullScreenRoomFragment a;

    g(FullScreenRoomFragment fullScreenRoomFragment) {
        this.a = fullScreenRoomFragment;
    }

    public final void run() {
        LogUtils.d("FullVerticalRoomFragment", "updateFollow");
        FullScreenRoomFragment.M(this.a).setClickable(true);
        if (FullScreenRoomFragment.N(this.a)) {
            FullScreenRoomFragment.M(this.a).setBackgroundResource(R.drawable.bt_attention_cancle_room_v6_selector);
        } else if (FullScreenRoomFragment.i(this.a)) {
            FullScreenRoomFragment.M(this.a).setBackgroundResource(R.drawable.bt_gril_attention_add_room_v6_selector);
        } else {
            FullScreenRoomFragment.M(this.a).setBackgroundResource(R.drawable.bt_attention_add_room_v6_selector);
        }
    }
}
