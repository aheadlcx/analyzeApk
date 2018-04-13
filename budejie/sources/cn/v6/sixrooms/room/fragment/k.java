package cn.v6.sixrooms.room.fragment;

import cn.v6.sixrooms.R;

final class k implements Runnable {
    final /* synthetic */ FullScreenRoomFragment a;

    k(FullScreenRoomFragment fullScreenRoomFragment) {
        this.a = fullScreenRoomFragment;
    }

    public final void run() {
        if (FullScreenRoomFragment.N(this.a)) {
            FullScreenRoomFragment.M(this.a).setBackgroundResource(R.drawable.bt_attention_cancle_room_v6_selector);
        } else if (FullScreenRoomFragment.i(this.a)) {
            FullScreenRoomFragment.M(this.a).setBackgroundResource(R.drawable.bt_gril_attention_add_room_v6_selector);
        } else {
            FullScreenRoomFragment.M(this.a).setBackgroundResource(R.drawable.bt_attention_add_room_v6_selector);
        }
        FullScreenRoomFragment.M(this.a).setClickable(true);
    }
}
