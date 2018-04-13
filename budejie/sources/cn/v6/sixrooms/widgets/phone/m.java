package cn.v6.sixrooms.widgets.phone;

import android.view.View;
import android.view.View.OnClickListener;

final class m implements OnClickListener {
    final /* synthetic */ FullScreenChatPage a;

    m(FullScreenChatPage fullScreenChatPage) {
        this.a = fullScreenChatPage;
    }

    public final void onClick(View view) {
        this.a.f = true;
        if (this.a.g != null) {
            this.a.g.onPrepare();
        }
        this.a.c.smoothScrollToPosition(Integer.MAX_VALUE);
    }
}
