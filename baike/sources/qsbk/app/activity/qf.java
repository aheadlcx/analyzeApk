package qsbk.app.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import qsbk.app.R;
import qsbk.app.utils.Util;

class qf implements TextWatcher {
    final /* synthetic */ LaiseeChargeActivity a;

    qf(LaiseeChargeActivity laiseeChargeActivity) {
        this.a = laiseeChargeActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        boolean g = this.a.g();
        this.a.e.setText("￥" + Util.formatMoney(this.a.i));
        this.a.f.setEnabled(g);
        int color = this.a.getResources().getColor(R.color.gray_laisee);
        if (!(g || TextUtils.isEmpty(this.a.c.getText().toString()))) {
            color = this.a.getResources().getColor(R.color.red_laisee);
            if (TextUtils.isEmpty(this.a.l)) {
                this.a.a.setVisibility(4);
            } else {
                this.a.a.setText(this.a.l);
                this.a.a.setVisibility(0);
            }
        }
        this.a.c.setTextColor(color);
        this.a.b.setTextColor(color);
        this.a.d.setTextColor(color);
    }
}
