package com.budejie.www.activity.phonenumber;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import com.budejie.www.R;

class k$2 implements TextWatcher {
    final /* synthetic */ k a;

    k$2(k kVar) {
        this.a = kVar;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (TextUtils.isEmpty(k.a(this.a).getText().toString())) {
            if ("phone_register".equals(k.b(this.a))) {
                k.c(this.a).setBackgroundResource(R.drawable.default_next_button);
                k.c(this.a).setEnabled(false);
            } else if ("third_party".equals(k.b(this.a)) || "MyAccountActivity".equals(k.b(this.a)) || "PostInviteRow".equals(k.b(this.a))) {
                k.c(this.a).setBackgroundResource(R.drawable.default_finish_button);
                k.c(this.a).setEnabled(false);
            }
        } else if ("phone_register".equals(k.b(this.a))) {
            k.c(this.a).setBackgroundResource(R.drawable.next_button);
            k.c(this.a).setEnabled(true);
        } else if ("third_party".equals(k.b(this.a)) || "MyAccountActivity".equals(k.b(this.a)) || "PostInviteRow".equals(k.b(this.a))) {
            k.c(this.a).setBackgroundResource(R.drawable.finish_button);
            k.c(this.a).setEnabled(true);
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
    }
}
