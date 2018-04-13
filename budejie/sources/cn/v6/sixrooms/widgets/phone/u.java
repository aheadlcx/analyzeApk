package cn.v6.sixrooms.widgets.phone;

import android.view.View;
import android.view.View.OnClickListener;
import cn.v6.sixrooms.R;

final class u implements OnClickListener {
    final /* synthetic */ HideOrDisplayThePasswordView a;

    u(HideOrDisplayThePasswordView hideOrDisplayThePasswordView) {
        this.a = hideOrDisplayThePasswordView;
    }

    public final void onClick(View view) {
        HideOrDisplayThePasswordView.b(this.a).toggle();
        if (HideOrDisplayThePasswordView.b(this.a).isChecked()) {
            HideOrDisplayThePasswordView.b(this.a).setBackgroundDrawable(HideOrDisplayThePasswordView.c(this.a).getResources().getDrawable(R.drawable.password_show));
        } else {
            HideOrDisplayThePasswordView.b(this.a).setBackgroundDrawable(HideOrDisplayThePasswordView.c(this.a).getResources().getDrawable(R.drawable.password_hide));
        }
        if (HideOrDisplayThePasswordView.a(this.a) != null) {
            HideOrDisplayThePasswordView.a(this.a).isShowPassword(HideOrDisplayThePasswordView.b(this.a).isChecked());
        }
    }
}
