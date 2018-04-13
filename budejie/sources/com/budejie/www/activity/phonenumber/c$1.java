package com.budejie.www.activity.phonenumber;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import com.budejie.www.R;

class c$1 implements TextWatcher {
    final /* synthetic */ c a;

    c$1(c cVar) {
        this.a = cVar;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (!TextUtils.isEmpty(c.a(this.a).getText().toString())) {
            c.b(this.a).setBackgroundResource(R.drawable.next_button);
            c.b(this.a).setClickable(true);
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
    }
}
