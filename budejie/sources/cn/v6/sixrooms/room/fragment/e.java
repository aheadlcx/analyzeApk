package cn.v6.sixrooms.room.fragment;

import cn.v6.sixrooms.bean.SofaBean;

final class e implements Runnable {
    final /* synthetic */ SofaBean a;
    final /* synthetic */ FullScreenRoomFragment b;

    e(FullScreenRoomFragment fullScreenRoomFragment, SofaBean sofaBean) {
        this.b = fullScreenRoomFragment;
        this.a = sofaBean;
    }

    public final void run() {
        if (FullScreenRoomFragment.K(this.b) != null) {
            FullScreenRoomFragment.K(this.b).updateSofa(this.a);
        }
        if (FullScreenRoomFragment.L(this.b) != null) {
            FullScreenRoomFragment.L(this.b).kickSofa(this.a);
        }
    }
}
