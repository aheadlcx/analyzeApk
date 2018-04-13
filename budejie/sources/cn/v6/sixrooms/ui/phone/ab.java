package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnClickListener;

final class ab implements OnClickListener {
    final /* synthetic */ EventActivity a;

    ab(EventActivity eventActivity) {
        this.a = eventActivity;
    }

    public final void onClick(View view) {
        this.a.a(true);
    }
}
