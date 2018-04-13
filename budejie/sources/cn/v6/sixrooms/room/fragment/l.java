package cn.v6.sixrooms.room.fragment;

import android.view.MotionEvent;
import cn.v6.sixrooms.room.view.InterceptRelativeLayout.OnInterceptTouchListener;

final class l implements OnInterceptTouchListener {
    final /* synthetic */ FullScreenRoomFragment a;

    l(FullScreenRoomFragment fullScreenRoomFragment) {
        this.a = fullScreenRoomFragment;
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0 || FullScreenRoomFragment.u(this.a) == null || !FullScreenRoomFragment.u(this.a).isShow()) {
            return false;
        }
        FullScreenRoomFragment.u(this.a).hidePrivateChatView();
        return true;
    }
}
