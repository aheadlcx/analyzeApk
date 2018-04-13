package qsbk.app.fragments;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.TextView;
import qsbk.app.R;

class dr implements TextWatcher {
    final /* synthetic */ LaiseeSendFragment a;

    dr(LaiseeSendFragment laiseeSendFragment) {
        this.a = laiseeSendFragment;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        Object obj = (TextUtils.isEmpty(editable.toString()) || this.a.b()) ? 1 : null;
        int color = obj != null ? this.a.getResources().getColor(R.color.gray_laisee) : this.a.getResources().getColor(R.color.red_laisee);
        this.a.h.setTextColor(color);
        this.a.g.setTextColor(color);
        this.a.i.setTextColor(color);
        this.a.a.setText(this.a.v);
        TextView textView = this.a.a;
        if (obj != null) {
            color = 4;
        } else {
            color = 0;
        }
        textView.setVisibility(color);
    }
}
