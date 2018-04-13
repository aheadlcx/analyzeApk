package cn.v6.sixrooms.room.fragment;

import cn.v6.sixrooms.bean.UpdateGiftNumBean;

final class d implements Runnable {
    final /* synthetic */ UpdateGiftNumBean a;
    final /* synthetic */ FullScreenRoomFragment b;

    d(FullScreenRoomFragment fullScreenRoomFragment, UpdateGiftNumBean updateGiftNumBean) {
        this.b = fullScreenRoomFragment;
        this.a = updateGiftNumBean;
    }

    public final void run() {
        FullScreenRoomFragment.a(this.b, this.a.getGiftNumBeans());
        if (FullScreenRoomFragment.J(this.b) != null) {
            FullScreenRoomFragment.J(this.b).updateStockGift();
        }
    }
}
