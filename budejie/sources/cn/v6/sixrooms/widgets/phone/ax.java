package cn.v6.sixrooms.widgets.phone;

import cn.v6.sixrooms.widgets.phone.FullScreenOpenGuardDialog.OnPurchaseGuardListener;

final class ax implements OnPurchaseGuardListener {
    final /* synthetic */ SpectatorsPop a;

    ax(SpectatorsPop spectatorsPop) {
        this.a = spectatorsPop;
    }

    public final void onPurchaseSuccess() {
        SpectatorsPop.a(this.a).getWrapUserInfo(this.a.mWrapRoomInfo.getRoominfoBean().getId(), this.a.mBaseRoomActivity.getLastUpadateTime());
    }
}
