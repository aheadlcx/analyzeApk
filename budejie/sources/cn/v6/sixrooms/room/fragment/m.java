package cn.v6.sixrooms.room.fragment;

import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.FansBean;
import cn.v6.sixrooms.room.presenter.FansPresenter.FirstFansCallBack;
import cn.v6.sixrooms.utils.DensityUtil;

final class m implements FirstFansCallBack {
    final /* synthetic */ FullScreenRoomFragment a;

    m(FullScreenRoomFragment fullScreenRoomFragment) {
        this.a = fullScreenRoomFragment;
    }

    public final void firstFans(FansBean fansBean) {
        FullScreenRoomFragment.P(this.a).setVisibility(0);
        FullScreenRoomFragment.Q(this.a).getLayoutParams().width = DensityUtil.dip2px(25.0f);
        FullScreenRoomFragment.Q(this.a).getLayoutParams().height = DensityUtil.dip2px(25.0f);
        FullScreenRoomFragment.Q(this.a).setImageURI(fansBean.getPicuser());
        FullScreenRoomFragment.f(this.a).mFistFansUid = fansBean.getUid();
    }

    public final void noFans() {
        FullScreenRoomFragment.P(this.a).setVisibility(4);
        FullScreenRoomFragment.Q(this.a).getLayoutParams().width = -2;
        FullScreenRoomFragment.Q(this.a).getLayoutParams().height = -2;
        if (FullScreenRoomFragment.i(this.a)) {
            FullScreenRoomFragment.Q(this.a).setImageResource(R.drawable.gril_nomal_crown);
        } else {
            FullScreenRoomFragment.Q(this.a).setImageResource(R.drawable.fans_crown_big_icon);
        }
    }
}
