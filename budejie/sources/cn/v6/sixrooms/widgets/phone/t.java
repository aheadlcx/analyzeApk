package cn.v6.sixrooms.widgets.phone;

import android.view.View;
import android.view.View.OnClickListener;

final class t implements OnClickListener {
    final /* synthetic */ HideOrDisplayThePasswordView a;

    t(HideOrDisplayThePasswordView hideOrDisplayThePasswordView) {
        this.a = hideOrDisplayThePasswordView;
    }

    public final void onClick(View view) {
        if (HideOrDisplayThePasswordView.a(this.a) != null) {
            this.a.hideCleanTag();
            HideOrDisplayThePasswordView.a(this.a).clickCleanButton();
        }
    }
}
