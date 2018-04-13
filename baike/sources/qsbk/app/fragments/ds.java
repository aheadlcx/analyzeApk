package qsbk.app.fragments;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import qsbk.app.R;
import qsbk.app.utils.Util;

class ds implements TextWatcher {
    final /* synthetic */ LaiseeSendFragment a;

    ds(LaiseeSendFragment laiseeSendFragment) {
        this.a = laiseeSendFragment;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        boolean c = this.a.c();
        this.a.l.setText("￥" + Util.formatMoney(this.a.p));
        this.a.m.setEnabled(c);
        int color = this.a.getResources().getColor(R.color.gray_laisee);
        if (!(c || TextUtils.isEmpty(this.a.c.getText().toString()) || TextUtils.isEmpty(this.a.g.getText().toString()))) {
            color = this.a.getResources().getColor(R.color.red_laisee);
            this.a.a.setText(this.a.v);
            this.a.a.setVisibility(0);
        }
        this.a.c.setTextColor(color);
        this.a.b.setTextColor(color);
        this.a.d.setTextColor(color);
    }
}
