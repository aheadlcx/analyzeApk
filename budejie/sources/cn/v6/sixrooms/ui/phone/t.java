package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

final class t implements OnItemClickListener {
    final /* synthetic */ ChatListPopupWindow a;

    t(ChatListPopupWindow chatListPopupWindow) {
        this.a = chatListPopupWindow;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.b.dismiss();
        if (this.a.h != null) {
            this.a.h.onItemClick(adapterView, view, i, j);
        }
    }
}
