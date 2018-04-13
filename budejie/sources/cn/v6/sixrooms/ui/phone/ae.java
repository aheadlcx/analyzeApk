package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.utils.KeyboardListener.OnListener;

final class ae implements OnListener {
    final /* synthetic */ EventActivity a;

    ae(EventActivity eventActivity) {
        this.a = eventActivity;
    }

    public final void keyStatechanged(int i, int i2) {
        if (i == 1 && this.a.userManagerView.getScrollY() != 0) {
            this.a.userManagerView.scrollTo(0, 0);
        }
    }
}
