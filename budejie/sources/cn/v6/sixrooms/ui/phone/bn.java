package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.widgets.phone.HideOrDisplayThePasswordView.OnHideOrDisplayListener;
import com.budejie.www.R$styleable;

final class bn implements OnHideOrDisplayListener {
    final /* synthetic */ RegisterActivity a;

    bn(RegisterActivity registerActivity) {
        this.a = registerActivity;
    }

    public final void clickCleanButton() {
        this.a.c.setText("");
    }

    public final void isShowPassword(boolean z) {
        if (z) {
            this.a.c.setInputType(R$styleable.Theme_Custom_phone_verify_btn_bg);
        } else {
            this.a.c.setInputType(129);
        }
        this.a.c.setSelection(this.a.c.length());
        this.a.b.clearFocus();
        this.a.c.requestFocus();
    }
}
