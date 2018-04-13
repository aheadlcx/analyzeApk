package com.budejie.www.activity.phonenumber;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import com.budejie.www.R;

class k$1 implements OnFocusChangeListener {
    final /* synthetic */ k a;

    k$1(k kVar) {
        this.a = kVar;
    }

    public void onFocusChange(View view, boolean z) {
        if (z) {
            k.a(this.a).setHintTextColor(this.a.getResources().getColor(R.color.input_phone_number));
        } else {
            k.a(this.a).setHintTextColor(this.a.getResources().getColor(R.color.input_phone_number_no_focus));
        }
    }
}
