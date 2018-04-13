package qsbk.app.activity;

import android.text.Editable;
import android.text.TextWatcher;

class act implements TextWatcher {
    final /* synthetic */ SingleEditTextBaseActivity a;

    act(SingleEditTextBaseActivity singleEditTextBaseActivity) {
        this.a = singleEditTextBaseActivity;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        int length = editable.length();
        if (this.a.maxLength() != 0 && this.a.maxLength() > 10) {
            if (length < this.a.maxLength() - 10) {
                this.a.d.setVisibility(8);
                return;
            }
            this.a.d.setVisibility(0);
            this.a.d.setText("" + (this.a.maxLength() - length));
        }
    }
}
