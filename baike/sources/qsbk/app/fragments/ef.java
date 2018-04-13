package qsbk.app.fragments;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.TextView;
import qsbk.app.R;

class ef implements TextWatcher {
    final /* synthetic */ LaiseeVoiceSendFragment a;

    ef(LaiseeVoiceSendFragment laiseeVoiceSendFragment) {
        this.a = laiseeVoiceSendFragment;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        Object obj = (TextUtils.isEmpty(editable.toString()) || this.a.c()) ? 1 : null;
        int color = obj != null ? this.a.getResources().getColor(R.color.gray_laisee) : this.a.getResources().getColor(R.color.red_laisee);
        this.a.r.setTextColor(color);
        this.a.l.setTextColor(color);
        this.a.s.setTextColor(color);
        this.a.q.setText(this.a.E);
        TextView textView = this.a.q;
        if (obj != null) {
            color = 4;
        } else {
            color = 0;
        }
        textView.setVisibility(color);
    }
}
