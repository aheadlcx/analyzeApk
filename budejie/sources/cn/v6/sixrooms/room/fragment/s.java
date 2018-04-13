package cn.v6.sixrooms.room.fragment;

import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.animation.RedPackageAnimation;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.utils.DensityUtil;

final class s implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ FullScreenRoomFragment b;

    s(FullScreenRoomFragment fullScreenRoomFragment, int i) {
        this.b = fullScreenRoomFragment;
        this.a = i;
    }

    public final void run() {
        float f = 200.0f;
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.height = FullScreenRoomFragment.f(this.b).getChatHeight(this.b.mRoomType);
        layoutParams.addRule(12, -1);
        if (FullScreenRoomFragment.aa(this.b)) {
            int screenWidth = (DensityUtil.getScreenWidth() * 36) / 75;
            FullScreenRoomFragment.c(this.b).getLayoutParams().height = screenWidth;
            if (this.b.mRoomType == 0) {
                FullScreenRoomFragment.Z(this.b).setBackgroundResource(R.color.transparent_background);
                FullScreenRoomFragment.Y(this.b).setVisibility(4);
                FullScreenRoomFragment.ab(this.b).setBackgroundResource(R.drawable.room_chat_common_backgroud);
                FullScreenRoomFragment.ac(this.b);
                ((RelativeLayout.LayoutParams) FullScreenRoomFragment.ad(this.b).getLayoutParams()).bottomMargin = DensityUtil.dip2px(42.0f);
                FullScreenRoomFragment.ae(this.b);
            }
            if (this.b.mRoomType == 0 || 4 == this.b.mRoomType) {
                float f2;
                int dip2px = DensityUtil.dip2px(44.0f) + screenWidth;
                if (layoutParams.height > dip2px) {
                    dip2px = layoutParams.height;
                }
                LayoutParams layoutParams2 = FullScreenRoomFragment.ab(this.b).getLayoutParams();
                if (this.b.mRoomType != 0) {
                    dip2px = screenWidth;
                }
                layoutParams2.height = dip2px;
                FullScreenRoomFragment.ab(this.b).setVisibility(0);
                layoutParams.bottomMargin = screenWidth;
                RoomActivity f3 = FullScreenRoomFragment.f(this.b);
                int i = layoutParams.height + (-screenWidth);
                if (this.b.mRoomType == 0) {
                    f2 = 200.0f;
                } else {
                    f2 = 160.0f;
                }
                f3.setGiftOffset(i - DensityUtil.dip2px(f2));
                RedPackageAnimation.setOffset(screenWidth);
                if (this.b.mRoomType != 0) {
                    f = 160.0f;
                }
                layoutParams.height = DensityUtil.dip2px(f);
            }
        } else {
            if (this.b.mRoomType == 0) {
                FullScreenRoomFragment.Z(this.b).setBackgroundResource(R.drawable.room_chat_common_backgroud);
                FullScreenRoomFragment.Y(this.b).setVisibility(0);
                FullScreenRoomFragment.ac(this.b);
                RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) FullScreenRoomFragment.ad(this.b).getLayoutParams();
                if (this.b.isAdded()) {
                    layoutParams3.bottomMargin = (int) this.b.getResources().getDimension(R.dimen.room_chat_marginbottom);
                } else {
                    layoutParams3.bottomMargin = (int) FullScreenRoomFragment.f(this.b).getResources().getDimension(R.dimen.room_chat_marginbottom);
                }
                FullScreenRoomFragment.ae(this.b);
            }
            FullScreenRoomFragment.ab(this.b).setVisibility(8);
            FullScreenRoomFragment.f(this.b).setGiftOffset(this.a);
            RedPackageAnimation.setOffset(this.a);
        }
        FullScreenRoomFragment.Z(this.b).setLayoutParams(layoutParams);
    }
}
