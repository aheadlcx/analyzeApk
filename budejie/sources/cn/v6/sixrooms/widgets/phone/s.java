package cn.v6.sixrooms.widgets.phone;

import android.view.View;
import android.view.View.OnClickListener;

final class s implements OnClickListener {
    final /* synthetic */ GetVerificationCodeView a;

    s(GetVerificationCodeView getVerificationCodeView) {
        this.a = getVerificationCodeView;
    }

    public final void onClick(View view) {
        if (GetVerificationCodeView.d(this.a) != null) {
            GetVerificationCodeView.d(this.a).clickGetVerificationCodeCallback(GetVerificationCodeView.e(this.a));
        }
    }
}
