package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnClickListener;

final class bg implements OnClickListener {
    final /* synthetic */ MyPropActivity a;

    bg(MyPropActivity myPropActivity) {
        this.a = myPropActivity;
    }

    public final void onClick(View view) {
        this.a.getSlidingMenu().a();
    }
}
