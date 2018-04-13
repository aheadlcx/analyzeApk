package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnClickListener;

final class au implements OnClickListener {
    final /* synthetic */ FindUsernameActivity a;

    au(FindUsernameActivity findUsernameActivity) {
        this.a = findUsernameActivity;
    }

    public final void onClick(View view) {
        this.a.finishWithAnimation();
    }
}
