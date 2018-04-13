package cn.v6.sixrooms.widgets.phone;

import android.view.View;
import android.view.View.OnClickListener;

final class ay implements OnClickListener {
    final /* synthetic */ UserLoginTitleView a;

    ay(UserLoginTitleView userLoginTitleView) {
        this.a = userLoginTitleView;
    }

    public final void onClick(View view) {
        if (this.a.b != null) {
            this.a.b.back();
        }
    }
}
