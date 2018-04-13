package cn.v6.sixrooms.room.fragment;

import android.text.TextUtils;
import cn.v6.sixrooms.bean.RedBean;

final class o implements Runnable {
    final /* synthetic */ RedBean a;
    final /* synthetic */ FullScreenRoomFragment b;

    o(FullScreenRoomFragment fullScreenRoomFragment, RedBean redBean) {
        this.b = fullScreenRoomFragment;
        this.a = redBean;
    }

    public final void run() {
        CharSequence currentRed = this.a.getCurrentRed();
        if (TextUtils.isEmpty(currentRed) || !currentRed.matches("[0-9]*")) {
            currentRed = "0";
        }
        FullScreenRoomFragment.R(this.b).setText(currentRed);
    }
}
