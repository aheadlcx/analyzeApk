package qsbk.app.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

class agp implements TextWatcher {
    final /* synthetic */ WithdrawActivity a;

    agp(WithdrawActivity withdrawActivity) {
        this.a = withdrawActivity;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        int i4 = 0;
        if (charSequence.toString().contains(".")) {
            boolean z;
            int length = charSequence.length() - charSequence.toString().lastIndexOf(".");
            WithdrawActivity withdrawActivity = this.a;
            if (length >= 4) {
                z = true;
            } else {
                z = false;
            }
            withdrawActivity.t = z;
        }
        View view = this.a.c;
        if (charSequence.toString().length() <= 0) {
            i4 = 8;
        }
        view.setVisibility(i4);
    }

    public void afterTextChanged(Editable editable) {
        if (this.a.t) {
            this.a.b.setText(editable.toString().substring(0, editable.toString().length() - 1));
            this.a.b.setSelection(this.a.b.getText().length());
        }
        if (editable.toString().startsWith(".")) {
            this.a.b.setText("0" + editable);
            this.a.b.setSelection(this.a.b.getText().length());
        }
        this.a.checkInputValid();
    }
}
