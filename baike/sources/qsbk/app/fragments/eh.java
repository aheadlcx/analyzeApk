package qsbk.app.fragments;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import qsbk.app.utils.Util;

class eh implements TextWatcher {
    final /* synthetic */ LaiseeVoiceSendFragment a;

    eh(LaiseeVoiceSendFragment laiseeVoiceSendFragment) {
        this.a = laiseeVoiceSendFragment;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        boolean d = this.a.d();
        this.a.C.setText(this.a.S > 0.0d ? "￥" + Util.formatMoney(this.a.B) : "￥0.00");
        this.a.o.setEnabled(d);
        if (d) {
            this.a.q.setVisibility(4);
        } else if (!TextUtils.isEmpty(this.a.m.getText().toString()) && !TextUtils.isEmpty(this.a.l.getText().toString())) {
            this.a.q.setText(this.a.E);
            this.a.q.setVisibility(0);
        }
    }
}
