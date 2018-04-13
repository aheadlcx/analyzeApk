package cn.v6.sixrooms.room.fragment;

import cn.v6.sixrooms.presenter.SpectatorsPresenter;
import cn.v6.sixrooms.widgets.phone.FullScreenOpenGuardDialog.OnPurchaseGuardListener;

final class f implements OnPurchaseGuardListener {
    final /* synthetic */ FullScreenRoomFragment a;

    f(FullScreenRoomFragment fullScreenRoomFragment) {
        this.a = fullScreenRoomFragment;
    }

    public final void onPurchaseSuccess() {
        SpectatorsPresenter.getInstance().getWrapUserInfo(FullScreenRoomFragment.p(this.a).getRoominfoBean().getId(), FullScreenRoomFragment.f(this.a).getLastUpadateTime());
    }
}
