package qsbk.app.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import qsbk.app.R;

class qg implements TextWatcher {
    final /* synthetic */ LaiseeChargeActivity a;

    qg(LaiseeChargeActivity laiseeChargeActivity) {
        this.a = laiseeChargeActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (charSequence.toString().contains(".")) {
            this.a.s = charSequence.length() - charSequence.toString().lastIndexOf(".") >= 4;
        }
    }

    public void afterTextChanged(Editable editable) {
        if (editable != null) {
            if (this.a.s) {
                this.a.c.setText(editable.toString().substring(0, editable.toString().length() - 1));
                this.a.c.setSelection(this.a.c.getText().length());
            }
            if (editable.toString().startsWith(".")) {
                this.a.c.setText("0" + editable);
                this.a.c.setSelection(this.a.c.getText().length());
            }
            int i = (TextUtils.isEmpty(editable.toString()) || this.a.f()) ? 1 : 0;
            int color = i != 0 ? this.a.getResources().getColor(R.color.gray_laisee) : this.a.getResources().getColor(R.color.red_laisee);
            this.a.c.setTextColor(color);
            this.a.b.setTextColor(color);
            this.a.d.setTextColor(color);
            if (TextUtils.isEmpty(this.a.l) || i == 0) {
                this.a.a.setVisibility(4);
                return;
            }
            this.a.a.setText(this.a.l);
            this.a.a.setVisibility(0);
        }
    }
}
