package qsbk.app.fragments;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.TextView;
import qsbk.app.R;

class dt implements TextWatcher {
    final /* synthetic */ LaiseeSendFragment a;

    dt(LaiseeSendFragment laiseeSendFragment) {
        this.a = laiseeSendFragment;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (charSequence.toString().contains(".")) {
            this.a.A = charSequence.length() - charSequence.toString().lastIndexOf(".") >= 4;
        }
    }

    public void afterTextChanged(Editable editable) {
        if (editable != null) {
            if (this.a.A) {
                this.a.c.setText(editable.toString().substring(0, editable.toString().length() - 1));
                this.a.c.setSelection(this.a.c.getText().length());
            }
            if (editable.toString().startsWith(".")) {
                this.a.c.setText("0" + editable);
                this.a.c.setSelection(this.a.c.getText().length());
            }
            int i = (TextUtils.isEmpty(editable.toString()) || this.a.a()) ? 1 : 0;
            int color = i != 0 ? this.a.getResources().getColor(R.color.gray_laisee) : this.a.getResources().getColor(R.color.red_laisee);
            this.a.c.setTextColor(color);
            this.a.b.setTextColor(color);
            this.a.d.setTextColor(color);
            this.a.a.setText(this.a.v);
            TextView textView = this.a.a;
            if (i != 0) {
                color = 4;
            } else {
                color = 0;
            }
            textView.setVisibility(color);
        }
    }
}
