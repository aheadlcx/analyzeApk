package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnClickListener;

final class p implements OnClickListener {
    final /* synthetic */ ChangeUserVisibilityActivity a;

    p(ChangeUserVisibilityActivity changeUserVisibilityActivity) {
        this.a = changeUserVisibilityActivity;
    }

    public final void onClick(View view) {
        this.a.getSlidingMenu().a();
    }
}
