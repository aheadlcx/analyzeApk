package qsbk.app.fragments;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.TextView;
import qsbk.app.R;

class ei implements TextWatcher {
    final /* synthetic */ LaiseeVoiceSendFragment a;

    ei(LaiseeVoiceSendFragment laiseeVoiceSendFragment) {
        this.a = laiseeVoiceSendFragment;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (charSequence.toString().contains(".")) {
            this.a.T = charSequence.length() - charSequence.toString().lastIndexOf(".") >= 4;
        }
    }

    public void afterTextChanged(Editable editable) {
        if (editable != null) {
            if (this.a.T) {
                this.a.m.setText(editable.toString().substring(0, editable.toString().length() - 1));
                this.a.m.setSelection(this.a.m.getText().length());
            }
            if (editable.toString().startsWith(".")) {
                this.a.m.setText("0" + editable);
                this.a.m.setSelection(this.a.m.getText().length());
            }
            int i = (TextUtils.isEmpty(editable.toString()) || this.a.b()) ? 1 : 0;
            int color = i != 0 ? this.a.getResources().getColor(R.color.gray_laisee) : this.a.getResources().getColor(R.color.red_laisee);
            this.a.m.setTextColor(color);
            this.a.n.setTextColor(color);
            this.a.p.setTextColor(color);
            this.a.q.setText(this.a.E);
            TextView textView = this.a.q;
            if (i != 0) {
                color = 4;
            } else {
                color = 0;
            }
            textView.setVisibility(color);
        }
    }
}
