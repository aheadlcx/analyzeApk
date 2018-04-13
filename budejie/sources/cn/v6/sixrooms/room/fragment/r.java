package cn.v6.sixrooms.room.fragment;

import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.animation.RedPackageAnimation;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.utils.DensityUtil;

final class r implements Runnable {
    final /* synthetic */ FullScreenRoomFragment a;

    r(FullScreenRoomFragment fullScreenRoomFragment) {
        this.a = fullScreenRoomFragment;
    }

    public final void run() {
        float f = 200.0f;
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.height = FullScreenRoomFragment.f(this.a).getChatHeight(this.a.mRoomType);
        layoutParams.addRule(12, -1);
        if (FullScreenRoomFragment.aa(this.a)) {
            int screenWidth = (DensityUtil.getScreenWidth() * 36) / 75;
            FullScreenRoomFragment.c(this.a).getLayoutParams().height = screenWidth;
            if (this.a.mRoomType == 0) {
                FullScreenRoomFragment.Z(this.a).setBackgroundResource(R.color.transparent_background);
                FullScreenRoomFragment.Y(this.a).setVisibility(4);
                FullScreenRoomFragment.ab(this.a).setBackgroundResource(R.drawable.room_chat_common_backgroud);
                FullScreenRoomFragment.ac(this.a);
                ((RelativeLayout.LayoutParams) FullScreenRoomFragment.ad(this.a).getLayoutParams()).bottomMargin = DensityUtil.dip2px(42.0f);
                FullScreenRoomFragment.ae(this.a);
            }
            if (this.a.mRoomType == 0 || 4 == this.a.mRoomType) {
                float f2;
                int dip2px = DensityUtil.dip2px(44.0f) + screenWidth;
                if (layoutParams.height > dip2px) {
                    dip2px = layoutParams.height;
                }
                LayoutParams layoutParams2 = FullScreenRoomFragment.ab(this.a).getLayoutParams();
                if (this.a.mRoomType != 0) {
                    dip2px = screenWidth;
                }
                layoutParams2.height = dip2px;
                FullScreenRoomFragment.ab(this.a).setVisibility(0);
                layoutParams.bottomMargin = screenWidth;
                RoomActivity f3 = FullScreenRoomFragment.f(this.a);
                int i = layoutParams.height + (-screenWidth);
                if (this.a.mRoomType == 0) {
                    f2 = 200.0f;
                } else {
                    f2 = 160.0f;
                }
                f3.setGiftOffset(i - DensityUtil.dip2px(f2));
                RedPackageAnimation.setOffset(screenWidth);
                if (this.a.mRoomType != 0) {
                    f = 160.0f;
                }
                layoutParams.height = DensityUtil.dip2px(f);
            }
        } else {
            if (this.a.mRoomType == 0) {
                FullScreenRoomFragment.Z(this.a).setBackgroundResource(R.drawable.room_chat_common_backgroud);
                FullScreenRoomFragment.Y(this.a).setVisibility(0);
                FullScreenRoomFragment.ac(this.a);
                RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) FullScreenRoomFragment.ad(this.a).getLayoutParams();
                if (this.a.isAdded()) {
                    layoutParams3.bottomMargin = (int) this.a.getResources().getDimension(R.dimen.room_chat_marginbottom);
                } else {
                    layoutParams3.bottomMargin = (int) FullScreenRoomFragment.f(this.a).getResources().getDimension(R.dimen.room_chat_marginbottom);
                }
                FullScreenRoomFragment.ae(this.a);
            }
            FullScreenRoomFragment.ab(this.a).setVisibility(8);
            FullScreenRoomFragment.f(this.a).setGiftOffset(0);
            RedPackageAnimation.setOffset(0);
        }
        FullScreenRoomFragment.Z(this.a).setLayoutParams(layoutParams);
    }
}
