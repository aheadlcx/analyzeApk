package cn.v6.sixrooms.room.fragment;

import android.view.View;
import android.view.View.OnLayoutChangeListener;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.utils.LogUtils;

final class w implements OnLayoutChangeListener {
    final /* synthetic */ FullScreenRoomFragment a;

    w(FullScreenRoomFragment fullScreenRoomFragment) {
        this.a = fullScreenRoomFragment;
    }

    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (i8 != 0 && i4 != 0 && i8 - i4 > 0 && i8 - i4 < FullScreenRoomFragment.v(this.a)) {
            LogUtils.e("onLayoutChange===", "监听到虚拟键弹出，不做任何操作");
            if (!FullScreenRoomFragment.w(this.a)) {
                FullScreenRoomFragment.x(this.a);
                FullScreenRoomFragment.b(this.a, false);
            }
            if (FullScreenRoomFragment.y(this.a)) {
                FullScreenRoomFragment.a(this.a, 0);
                FullScreenRoomFragment.z(this.a);
            }
            if (this.a.isAdded() && this.a.mRoomType == 4) {
                FullScreenRoomFragment.f(this.a).reSetPlayerHeight();
            }
        } else if (i8 != 0 && i4 != 0 && i8 - i4 < 0 && i4 - i8 < FullScreenRoomFragment.v(this.a)) {
            LogUtils.e("onLayoutChange===", "监听虚拟键收起232");
            LogUtils.e("onLayoutChange===", RoomActivity.VIDEOTYPE_UNKNOWN);
            if (!FullScreenRoomFragment.w(this.a)) {
                FullScreenRoomFragment.x(this.a);
                FullScreenRoomFragment.b(this.a, true);
            }
            if (FullScreenRoomFragment.y(this.a)) {
                FullScreenRoomFragment.a(this.a, Math.abs(i8 - i4));
                LogUtils.e("onLayoutChange===", "virtualBarheight=" + FullScreenRoomFragment.A(this.a));
                FullScreenRoomFragment.z(this.a);
            }
            LogUtils.e("onLayoutChange===", "0");
            if (this.a.isAdded() && this.a.mRoomType == 4) {
                LogUtils.e("onLayoutChange===", "1");
                FullScreenRoomFragment.f(this.a).reSetPlayerHeight();
            }
        }
    }
}
