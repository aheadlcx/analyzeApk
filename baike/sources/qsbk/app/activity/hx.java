package qsbk.app.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

class hx implements TextWatcher {
    final /* synthetic */ TextView a;
    final /* synthetic */ hw b;

    hx(hw hwVar, TextView textView) {
        this.b = hwVar;
        this.a = textView;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.a.setText(String.valueOf(140 - editable.length()));
    }
}
