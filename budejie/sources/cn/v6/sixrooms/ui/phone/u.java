package cn.v6.sixrooms.ui.phone;

import android.widget.PopupWindow.OnDismissListener;

final class u implements OnDismissListener {
    final /* synthetic */ ChatListPopupWindow a;

    u(ChatListPopupWindow chatListPopupWindow) {
        this.a = chatListPopupWindow;
    }

    public final void onDismiss() {
        this.a.f.onHeaderRefreshComplete();
        this.a.f.onFooterRefreshComplete();
        this.a.g = null;
        if (this.a.h != null) {
            this.a.h.onDismiss();
        }
    }
}
